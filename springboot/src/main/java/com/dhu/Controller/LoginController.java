package com.dhu.Controller;

import com.dhu.Service.UserService;
import com.dhu.config.Result;
import com.dhu.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

/**
 * Author: ComingLiu
 * Date: 2022/4/18 14:49
 */
@Controller
@RequestMapping("/Login")
public class LoginController {
//    默认请求error
    @Autowired
    private UserService userService;

    @ApiOperation("登录")
    @GetMapping(value = "/login")
    @ResponseBody
    public Result<?> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @ApiIgnore Model model,
            @ApiIgnore HttpSession session){
        User user = userService.selectUser(username);
        if(user != null && user.getPassword().equals(password)){
            session.setAttribute("User", user);
        }else{
            return Result.error("用户名或密码错误");
        }
        return Result.success(user);
    }

    @ApiOperation("注册")
    @PostMapping(value = "/register")
    @ResponseBody
    public Result<?> save(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            @RequestParam("checkCode") String checkCode,
            @ApiIgnore HttpSession session){
        String code = (String) session.getAttribute(email);
        User user = userService.selectUser(username);
        if(user != null){
            return Result.error("用户名已被占用");
        }
        user = userService.selectEmail(email);
        if(user != null){
            return Result.error("此邮箱已被注册");
        }
        if(code == null || !code.equals(checkCode)){
            return Result.error("验证码错误");
        }

        return userService.userInsert(username, password, email);
    }
    @ApiOperation("退出登录")
    @ResponseBody
    @PostMapping("/logout")
    public Result<?> logout(@ApiIgnore HttpSession session){
        User user = (User) session.getAttribute("User");
        if(user == null){
            return Result.error("当前无用户");
        }
        session.setAttribute("User", null);
        return Result.success("用户已退出登录");
    }
}
