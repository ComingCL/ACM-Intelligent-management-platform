package com.dhu.Controller;

import com.dhu.component.WebSocketServer;
import com.dhu.config.Result;
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
import java.util.List;

/**
 * @Author: ComingLiu
 * @Date: 2022/6/2 22:31
 */
@Controller
@RequestMapping("/WebSocket")
public class WebSocketController {
    @Autowired
    private WebSocketServer server;

    @ApiOperation("查看当前在线用户")
    @GetMapping("/getOpenUserIds")
    @ResponseBody
    public Result<?> getOpenUserIds(){
        return Result.success(server.getOpenUsers());
    }

    @GetMapping("/sendMessageToFront")
    @ApiOperation("发送消息给另一个用户, 开发中...")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message", value = "发送消息内容", dataType = "String"),
            @ApiImplicitParam(name = "uid", value = "用户id", dataType = "String")})
    @ResponseBody
    public Result<?> sendMessage(@ApiIgnore Session session,
                                 @RequestParam("message") String message,
                                 @RequestParam("uid") String uid) throws IOException {
        server.onMessage(message, session, uid);
        System.out.println("发送的消息是" + message + " " + uid);
        return Result.success(message);
    }
}
