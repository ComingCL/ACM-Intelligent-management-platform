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
public class LoginController {
//    默认请求error
    @Autowired
    private UserService userService;

    @GetMapping(value = "/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }

    @PostMapping(value = "/user")
    @ResponseBody
    public User user(@ApiParam("用户") User user) {
        return user;
    }

    @GetMapping(value = "/login")
    @ResponseBody
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @ApiIgnore Model model,
            @ApiIgnore HttpSession session){
        ModelAndView mv = new ModelAndView();
        User user = userService.getById(username);
        if(user != null && user.getPassword().equals(password)){
            session.setAttribute("User", user);
        }else{
            return "error";
        }
//        else mv.setViewName("error");
        return "index";
    }
}
