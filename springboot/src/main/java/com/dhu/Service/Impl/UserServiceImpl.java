package com.dhu.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhu.Service.UserService;
import com.dhu.config.Result;
import com.dhu.mapper.UserMapper;
import com.dhu.pojo.User;
import com.dhu.utils.VerCodeGenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Author: ComingLiu
 * Date: 2022/4/27 10:32
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    private String code;// 验证码
    private Date sendTime;// 发送时间
    @Override
    public Result<?> userInsert(String username, String password, String email){
        User user = getById(username);
        if(user != null) return Result.error("error", "用户已存在");
        user = new User(null, username, null, email, null, password, null, 1);
        save(user);
        return Result.success(user);
    }
    @Override
    public Result<?> getcode(String sender, JavaMailSenderImpl mailSender, String receiver){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("验证码");
        code = VerCodeGenerateUtil.generateVerCode();
        sendTime = new Date();
        message.setText("尊敬的用户,您好:\n"
                        + "\n本次请求的邮件验证码为:" + code + ",本验证码5分钟内有效，请及时输入。（请勿泄露此验证码）\n"
                        + "\n如非本人操作，请忽略该邮件。\n(这是一封自动发送的邮件，请不要直接回复）");
        message.setFrom(sender);
        message.setTo(receiver);
        mailSender.send(message);
        return new Result<>("邮箱验证码发送完成");
    }
    public User selectUser(String username){
        return userMapper.selectUser(username);
    }
}
