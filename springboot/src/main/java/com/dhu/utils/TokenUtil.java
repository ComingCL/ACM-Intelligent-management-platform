package com.dhu.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dhu.pojo.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Author: ComingLiu
 * @Date: 2022/5/30 17:26
 */
@Component
public class TokenUtil {
    /**
     * 生成token
     */
    public static String generateToken(User user){
        String token = "";
        token = JWT.create()
                .withClaim("userid", user.getId())
                .withClaim("username", user.getUsername())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
    /**
     * 获取指定token中的某个属性值
     */
    public static String get(String token, String key){
        List<String> list = JWT.decode(token).getAudience();
        return JWT.decode(token).getAudience().get(0);
    }
}
