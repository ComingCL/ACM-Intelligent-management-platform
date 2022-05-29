package com.dhu;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhu.Service.MailService;
import com.dhu.Service.NewsService;
import com.dhu.Service.OJService;
import com.dhu.Service.UserService;
import com.dhu.mapper.NewsMapper;
import com.dhu.mapper.UserMapper;
import com.dhu.pojo.News;
import com.dhu.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
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
    public void testOJ(){
        ojService.getLuoguInformation("2");
    }

    public static void main(String[] args) {
        SpringApplication.run(AcmIntelligentManagementPlatformApplication.class, args);
    }
}
