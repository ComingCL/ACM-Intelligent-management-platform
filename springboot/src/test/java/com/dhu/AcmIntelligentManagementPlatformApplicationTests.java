package com.dhu;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhu.Service.NewsService;
import com.dhu.mapper.NewsMapper;
import com.dhu.mapper.UserMapper;
import com.dhu.pojo.News;
import com.dhu.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
class AcmIntelligentManagementPlatformApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NewsMapper newsMapper;
    @Test
    void contextLoads() {
//        参数是一个wapper, 条件构造器
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }
    @Test
    void testInsert(){
        User user = new User();
        user.setUsername("张三");
        user.setAge(23);
        user.setEmail("zhangsan@guigu.com");
        int result = userMapper.insert(user);
        System.out.println(result);
    }
    @Test
    public void TestTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println(localDateTime.format(formatter));
    }
    @Test
    public void testPage(){
        Page<News> page = new Page<>(1, 2);
        newsMapper.selectPage(page, null);
        List<News> list = page.getRecords();
        for(News news : list){
            System.out.println(news.getContent());
        }
//        System.out.println(page.getPages());
//        System.out.println(page.getTotal());
//        System.out.println(page.hasNext());
//        System.out.println(page.hasPrevious());

    }
}
