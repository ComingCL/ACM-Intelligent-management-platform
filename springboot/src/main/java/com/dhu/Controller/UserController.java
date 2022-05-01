package com.dhu.Controller;

import com.dhu.Service.UserService;
import com.dhu.config.Result;
import com.dhu.mapper.UserMapper;
import com.dhu.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Author: ComingLiu
 * Date: 2022/4/26 19:46
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping(value = "/register")
    public Result<?> save(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            Model model){
        userService.save(new User(username, null, email, null, password, null, 1));
        return Result.success();
    }
    @PostMapping(value = "/editUser_password")
    @ResponseBody
    public String edit_password(
            @RequestParam("password") String password,
            @ApiIgnore HttpSession session){
        List<User> userList = userService.list();
        User user = (User)session.getAttribute("User");
        user.setPassword(password);
        userService.updateById(user);
        return "success";
    }
    @GetMapping
    public List<?> list(){
        return this.userService.list();
    }
}
