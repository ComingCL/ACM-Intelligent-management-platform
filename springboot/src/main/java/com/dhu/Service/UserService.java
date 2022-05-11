package com.dhu.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhu.config.Result;
import com.dhu.pojo.User;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/**
 * Author: ComingLiu
 * Date: 2022/4/27 10:03
 */
public interface UserService extends IService<User> {
    Result<?> userInsert(String username, String password, String email);
    Result<?> getcode(String sender, JavaMailSenderImpl mailSender, String receiver);
    User selectUser(String username);
}
