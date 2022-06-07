package com.dhu;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhu.component.WebSocketServer;
import com.dhu.Service.NewsService;
import com.dhu.Service.OJService;
import com.dhu.Service.UserService;
import com.dhu.mapper.UserMapper;
import com.dhu.mapper.tSignAdminMapper;
import com.dhu.pojo.User;
import com.dhu.pojo.tSignAdmin;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
//提供真实的web环境, 提供websocket支持等
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcmIntelligentManagementPlatformApplicationTests {
    @Autowired
    JavaMailSenderImpl mailSender;
    @Autowired
    private OJService ojService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WebSocketServer server;
    @Autowired
    private tSignAdminMapper mapper;
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
    @Test
    public void testSQL(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ismember", 1);
        List<User> users = userMapper.selectList(queryWrapper);
        for(User user : users){
            System.out.println(user.getUsername());
        }
    }
    @Test
    public void testWebSocket(){
        server.sendMessageToTeams("1235");
    }
    @Test
    public void testTSignAdminMapper() throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<tSignAdmin> list = mapper.getSignId(simpleDateFormat.parse("1234-12-12 11:11:11"));
        for(tSignAdmin admin : list){
            System.out.println(admin.getActivity());
        }
    }
}
