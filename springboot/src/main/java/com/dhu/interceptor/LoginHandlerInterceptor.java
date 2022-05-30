package com.dhu.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.PathMatcher;
import java.util.List;

/**
 * @Author: ComingLiu
 * @Date: 2022/5/1 12:17
 */
@Component // ioc容器中的组件
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
//        拿到token, 解析token, 如果能解析就暂时认为他可以登录, 其实客户端可以伪造
//        跟缓存对比, 如果缓存存在(redis)
        if(ObjectUtils.isEmpty(token)){
            noToken(response);
            return false;
        }
        DecodedJWT decodedJWT = JWT.decode(token);
        String userid = decodedJWT.getClaim("userid").asString();
//        如果查询redis中不存在对应的登录信息
        if(userid == null){
            noToken(response);
            return false;
        }
        return true;
    }

    private void noToken(HttpServletResponse response) throws IOException {
        response.setContentType("text/application;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write("用户未登录");
        out.flush();
        out.close();
    }
}
