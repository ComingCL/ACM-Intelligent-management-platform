package com.dhu.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhu.Service.UserService;
import com.dhu.config.Result;
import com.dhu.mapper.UserMapper;
import com.dhu.pojo.User;
import org.springframework.stereotype.Service;

import java.awt.geom.QuadCurve2D;

/**
 * Author: ComingLiu
 * Date: 2022/4/27 10:32
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public Result<?> userInsert(String username, String password, String email){
//        User user = new User();
        return null;
    }
}
