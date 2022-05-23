package com.dhu;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhu.Service.MailService;
import com.dhu.Service.NewsService;
import com.dhu.mapper.NewsMapper;
import com.dhu.mapper.UserMapper;
import com.dhu.pojo.News;
import com.dhu.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
    private NewsService newsService;
    /*
    @Test
    void testPython(){
        Process proc;
        try{
//            这里python解释器位置也要写好
            proc = Runtime.getRuntime().exec("C:\\Users\\13280\\PycharmProjects\\pythonProject\\venv\\Scripts\\python.exe \"C:\\Users\\13280\\Desktop\\ACM Intelligent management platform\\springboot\\src\\main\\java\\com\\dhu\\python\\hdu.py\"");
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "gbk"));
            String line;
            while((line = in.readLine()) != null){
                System.out.println(line);
            }
            in.close();
            int res = proc.waitFor();
//            0表示正常否则不正常
//            System.out.println(res);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
     */
    /*
    @Test
    public void testNews(){
        System.out.println(newsService.count());
    }
     */
}
