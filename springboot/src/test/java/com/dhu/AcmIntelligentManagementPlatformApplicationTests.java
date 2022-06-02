package com.dhu;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dhu.component.WebSocketServer;
import com.dhu.Service.NewsService;
import com.dhu.Service.OJService;
import com.dhu.Service.UserService;
import com.dhu.pojo.User;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.List;

@SpringBootTest
public class AcmIntelligentManagementPlatformApplicationTests {
    @Autowired
    JavaMailSenderImpl mailSender;
    @Autowired
    private OJService ojService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;
    @Test
    public void testUser(){
        for(User user : userService.list()){
            System.out.println(user.getUsername());
        }
    }
    @Test
    public void testToken(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyaWQiOiIxNTI3ODQyMjkwMDY3Mjk2MjU3IiwidXNlcm5hbWUiOiIxMjMifQ.RvS_wX5s3isWTeqs8IL8_HREIlFxFLMHfdKH7UBH3pQ";
        DecodedJWT decodedJWT = JWT.decode(token);
        String userid = decodedJWT.getClaim("userid").asString();
        String username = decodedJWT.getClaim("username").asString();
        System.out.println(userid + " " + username);
    }
}
