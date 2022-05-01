package com.dhu.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.PathMatcher;
import java.util.List;

/**
 * Author: ComingLiu
 * Date: 2022/5/1 12:17
 */
// 登录拦截器
public class LoginHandlerInterceptor implements HandlerInterceptor {
    private List<String> urls;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        登录成功之后应该有用户的session
        Object loginUser = request.getSession().getAttribute("loginUser");
//        不需要登录就可以访问的路径--白名单
//        String requestURI = request.getRequestURI();
//        PathMatcher matcher = new AntPathMatcher();
//        for(String ignoreUrl : urls){
//
//        }
//        if(loginUser == null){
//            request.setAttribute("msg", "没有权限, 请先登录");
//            request.getRequestDispatcher("/index.html").forward(request, response);
//            return false;
//        }
        return true;
    }
}
