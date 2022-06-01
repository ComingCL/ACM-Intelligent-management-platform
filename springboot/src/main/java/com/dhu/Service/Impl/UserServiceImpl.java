package com.dhu.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
        if(user != null) return Result.error("用户已存在");
        user = new User(null, username, null, email, null, password, null, 1, 1, null, null, null, null);
        save(user);
        return Result.success(user);
    }
    @Override
    public User selectUser(String username){
        return userMapper.selectUser(username);
    }

    @Override
    public User selectEmail(String email) {
        return userMapper.selectEmail(email);
    }

    @Override
    public void modifyLuoguId(String uid, String id) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();
        updateWrapper.eq("id", uid);
        updateWrapper.set("luogu_id", id);
        baseMapper.update(null, updateWrapper);
    }
    /**
     *
     * @return 根据rating给用户排序, 暂且如此设计, 实际上应按照用户的活动情况, 对于长时间未活动的用户不予排名
     */
    @Override
    public List<User> getByRatingOrder() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("rating").isNotNull("rating").last("limit 10");
        return list(wrapper);
    }
}
