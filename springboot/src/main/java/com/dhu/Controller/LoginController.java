package com.dhu.Controller;

import com.dhu.Service.UserService;
import com.dhu.config.Result;
import com.dhu.pojo.User;
import com.dhu.utils.TokenUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: ComingLiu
 * @Date: 2022/4/18 14:49
 */
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
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
            @ApiIgnore HttpServletRequest request,
            @ApiIgnore HttpServletResponse response){
        User user = userService.selectUser(username);
        if(user != null && user.getPassword().equals(password)){
//            String jwtString = TokenUtil.generateToken(user);
//            return Result.success(jwtString);
            request.getSession().setAttribute("User", user);
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
            @ApiIgnore HttpServletRequest request){
        String code = (String) request.getSession().getAttribute(email);
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
    public Result<?> logout(@ApiIgnore HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("User");
        if(user == null){
            return Result.error("当前无用户");
        }
        request.getSession().setAttribute("User", null);
        return Result.success("用户已退出登录");
    }
}
