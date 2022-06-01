package com.dhu.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhu.config.Result;
import com.dhu.pojo.User;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: ComingLiu
 * Date: 2022/4/27 10:03
 */
public interface UserService extends IService<User> {
    Result<?> userInsert(String username, String password, String email);
    User selectUser(String username);
    User selectEmail(String email);
    void modifyLuoguId(String uid, String id);
    List<User> getByRatingOrder();
}
