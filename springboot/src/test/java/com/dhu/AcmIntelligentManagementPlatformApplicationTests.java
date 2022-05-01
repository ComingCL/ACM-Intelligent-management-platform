package com.dhu;

import com.dhu.mapper.UserMapper;
import com.dhu.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AcmIntelligentManagementPlatformApplicationTests {
    @Autowired
    private UserMapper userMapper;
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
}
