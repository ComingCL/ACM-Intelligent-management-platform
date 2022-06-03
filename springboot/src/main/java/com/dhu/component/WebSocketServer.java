package com.dhu.component;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhu.Service.UserService;
import com.dhu.mapper.UserMapper;
import com.dhu.pojo.User;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: ComingLiu
 * @Date: 2022/6/2 15:37
 */
@ServerEndpoint("/send/{uid}")
@Component
public class WebSocketServer {
    @Autowired
    private UserMapper userMapper;

    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    /**
     * 记录当前在线连接数
     */
    private static final ConcurrentHashMap<String, Session> sessionMap = new ConcurrentHashMap<>();

    /**
     * 建立连接成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("uid") String uid){
        sessionMap.put(uid, session);
        log.info("有新用户加入, userid={}, 当前在线人数为: {}", uid, sessionMap.size());
//        JSONObject result = new JSONObject();
//        JSONArray array = new JSONArray();
//        result.set("users", array);
//        for(Object key : sessionMap.keySet()){
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.set("uid", key);
//            array.add(jsonObject);
//        }
//        sendAllMessage(JSONUtil.toJsonStr(result));
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session, @PathParam("uid") String uid){
        sessionMap.remove(uid);
        log.info("有一连接关闭, 移除uid={}的用户session, 当前在线人数为: {}", uid, sessionMap.size());
    }
    /**
     * 收到客户端消息之后调用的方法
     * 后台收到客户端发送过来的消息
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("uid") String uid){
        log.info("服务端收到用户uid={}的消息:{}", uid, message);
//        解析发送的报文
        JSONObject obj = JSONUtil.parseObj(message);
        String toUid = obj.getStr("to");
        String text = obj.getStr("text");
        Session toSession = sessionMap.get(toUid);
        if(toSession != null){
//            一种json格式的数据结构
            JSONObject jsonObject = new JSONObject();
            jsonObject.set("from", uid);
            jsonObject.set("text", text);
            this.sendMessage(jsonObject.toString(), toSession);
            log.info("发送给用户uid={}, 消息: {}", toUid, jsonObject.toString());
        }else{
            log.info("发送失败, 未找到用户uid={}的session", toUid);
        }
    }
    @OnError
    public void onError(Session session, Throwable error){
        log.error("发生错误");
        error.printStackTrace();
    }
    /**
     * 服务端发送消息给客户端
     */
    public void sendMessage(String message, Session toSession){
        try{
            log.info("服务端给客户端[{}]发送消息{}", toSession.getId(), message);
        }catch (Exception e){
            log.error("服务端给客户端发送消息失败", e);
        }
    }
    /**
     * 服务端发送消息给所有队员
     */
    public void sendMessageToTeams(String message){
        HashSet<String> hashSet = new HashSet<>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ismember", 1);
        List<User> list = userMapper.selectList(queryWrapper);
        for(User user : list) {
            hashSet.add(user.getId());
            System.out.println(user.getId());
        }
        try{
            for(String uid : sessionMap.keySet()){
                if(hashSet.contains(uid)){
                    log.info("服务端给用户[{}]发送消息", uid);
                    sessionMap.get(uid).getBasicRemote().sendText(message);
                }
            }
        } catch (IOException e) {
            log.error("服务端给客户端发送消息失败", e);
        }
    }
    /**
     * 服务端发送消息给所有客户端
     */
    public void sendAllMessage(String message){
        try{
            for(Session session : sessionMap.values()){
                log.info("服务端给客户端[{}]发送消息", session.getId());
                session.getBasicRemote().sendText(message);
            }
        }catch (Exception e){
            log.error("服务端给客户端发送消息失败", e);
        }
    }

    /**
     * 获取当前在线人数
     * @return 返回值为在线人员id列表
     */
    public List<String> getOpenUsers(){
        List<String> list = new ArrayList<>();
        for(Session session : sessionMap.values()){
            list.add(session.getId());
        }
        return list;
    }
}
