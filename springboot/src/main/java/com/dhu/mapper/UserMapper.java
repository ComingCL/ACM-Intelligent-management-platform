package com.dhu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhu.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Author: ComingLiu
 * Date: 2022/4/18 13:43
 */
@Repository // 代表持久层
public interface UserMapper extends BaseMapper<User> {
    User selectUser(@Param("username") String username);
    User selectEmail(@Param("email") String email);
}
