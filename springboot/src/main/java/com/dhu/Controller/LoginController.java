package com.dhu.Controller;

import com.dhu.Service.UserService;
import com.dhu.config.Result;
import com.dhu.pojo.User;
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
            return Result.error("error", "用户名或密码错误");
        }
        return Result.success(user);
    }
}
