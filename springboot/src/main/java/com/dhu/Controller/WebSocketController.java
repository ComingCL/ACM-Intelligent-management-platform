package com.dhu.Controller;

import com.alibaba.fastjson.JSON;
import com.dhu.component.WebSocketServer;
import com.dhu.config.Result;
import com.dhu.pojo.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ComingLiu
 * @Date: 2022/6/2 22:31
 */
@Controller
@RequestMapping("/WebSocket")
public class WebSocketController {
    @Autowired
    private WebSocketServer server;

    @GetMapping("/sendMessageToFront")
    @ApiOperation("发送消息给另一个用户")
    @ResponseBody
    public Result<?> sendMessage(@ApiIgnore HttpServletRequest request,
                                 @ApiIgnore Session session,
                                 @RequestParam("message") String message,
                                 @RequestParam("uid") String uid){
        User user = (User) request.getSession().getAttribute("User");
        Map<String, Object> map = new HashMap<>();
        map.put("to", user.getId());
        map.put("text", message);
        server.onMessage(JSON.toJSONString(map), session, uid);
        return Result.success(message);
    }
}
