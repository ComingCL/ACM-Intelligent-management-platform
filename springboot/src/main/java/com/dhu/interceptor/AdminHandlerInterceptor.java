package com.dhu.interceptor;

import com.dhu.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: ComingLiu
 * @Date: 2022/6/2 0:33
 */
@Component
public class AdminHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("User");
        if(user.getPower() != 0){
            notAdmin(response);
            return false;
        }
        return true;
    }
    private void notAdmin(HttpServletResponse response) throws IOException {
        response.setContentType("text/application;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write("此用户非管理员");
        out.flush();
        out.close();
    }
}
