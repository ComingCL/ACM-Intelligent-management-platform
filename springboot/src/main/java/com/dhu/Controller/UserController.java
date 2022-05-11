package com.dhu.Controller;

import com.dhu.Service.UserService;
import com.dhu.config.Result;
import com.dhu.mapper.UserMapper;
import com.dhu.pojo.User;
import io.swagger.annotations.ApiModelProperty;
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
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping(value = "/register")
    @ResponseBody
    public Result<?> save(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email){
        return userService.userInsert(username, password, email);
    }
    @ApiModelProperty("修改密码")
    @PostMapping(value = "/editUser_password")
    @ResponseBody
    public Result<?> edit_password(
            @RequestParam("password") String password,
            @ApiIgnore HttpSession session){
        User user = (User)session.getAttribute("User");
        if(user == null) return Result.error("error", "用户为空");
        user.setPassword(password);
        userService.updateById(user);
        return Result.success(user);
    }
    @ApiModelProperty("修改用户名")
    @PostMapping(value = "/editUser_username")
    @ResponseBody
    public Result<?> editusername(
            @RequestParam("username") String username,
            @ApiIgnore HttpSession session){
        User user = (User) session.getAttribute("User");
        if(user == null) return Result.error("error", "用户为空");
        User selectUser = userService.selectUser(username);
        if(selectUser != null){
            return Result.error("error", "用户名已存在");
        }
        user.setUsername(username);
        userService.updateById(user);
        return Result.success(user);
    }
    @ApiModelProperty("修改年龄")
    public Result<?> edit_age(
            @RequestParam("age") Integer age,
            @ApiIgnore HttpSession session){
        User user = (User)session.getAttribute("User");
        if(user == null) return Result.error("error", "用户为空");
        user.setAge(age);
        userService.updateById(user);
        return Result.success(user);
    }
    @ApiModelProperty("修改绑定邮箱, 开发中..")
    public Result<?> edit_email(

    ){
        return null;
    }
    @ApiModelProperty("修改昵称")
    public Result<?> edit_nickname(
            @RequestParam("nick_name") String nick_name,
            @ApiIgnore HttpSession session
    ){
        User user = (User)session.getAttribute("User");
        if(user == null) return Result.error("error", "用户为空");
        user.setNickName(nick_name);
        userService.updateById(user);
        return Result.success(user);
    }
    @ApiModelProperty("修改学号")
    public Result<?> edit_number(
            @RequestParam("number") String number,
            @ApiIgnore HttpSession session
    ){
        User user = (User)session.getAttribute("User");
        if(user == null) return Result.error("error", "用户为空");
        user.setNumber(number);
        userService.updateById(user);
        return Result.success(user);
    }
    @GetMapping(value = "/getUsers")
    @ResponseBody
    public Result<?> list(){
        return Result.success(this.userService.list());
    }


}
