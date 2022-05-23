package com.dhu.Controller;

import com.dhu.Service.MailService;
import com.dhu.Service.UserService;
import com.dhu.config.Result;
import com.dhu.mapper.UserMapper;
import com.dhu.pojo.User;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
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
    @Autowired
    private MailService mailService;
    @ApiOperation("修改密码")
    @PostMapping(value = "/editUser_password")
    @ResponseBody
    public Result<?> edit_password(
            @RequestParam("password") String password,
            @ApiIgnore HttpSession session){
        User user = (User)session.getAttribute("User");
        if(user == null) return Result.error("用户为空");
        user.setPassword(password);
        userService.updateById(user);
        return Result.success(user);
    }
    @ApiOperation("修改用户名")
    @PostMapping(value = "/editUser_username")
    @ResponseBody
    public Result<?> editusername(
            @RequestParam("username") String username,
            @ApiIgnore HttpSession session){
        User user = (User) session.getAttribute("User");
        if(user == null) return Result.error("用户为空");
        User selectUser = userService.selectUser(username);
        if(selectUser != null){
            return Result.error("用户名已存在");
        }
        user.setUsername(username);
        userService.updateById(user);
        return Result.success(user);
    }
    @ApiOperation("修改年龄")
    @PostMapping(value = "/editUser_age")
    @ResponseBody
    public Result<?> edit_age(
            @RequestParam("age") Integer age,
            @ApiIgnore HttpSession session){
        User user = (User)session.getAttribute("User");
        if(user == null) return Result.error("用户为空");
        user.setAge(age);
        userService.updateById(user);
        return Result.success(user);
    }
    @ApiOperation("修改绑定邮箱, 开发中...")
    @PostMapping(value = "/edit_email")
    @ResponseBody
    public Result<?> edit_email(
            @RequestParam("email") String email,
            @ApiIgnore HttpSession session){
//        User user = (User)session.getAttribute("User");
//        if(user == null) return Result.error("error", "用户为空");
//        String checkCode = userService.getModifyEmailCheckCode();
//        String message = "您正在修改您的绑定邮箱, 验证码为: " + checkCode;
//        try{
//            mailService.sendMail(email, "邮箱验证码", message);
//        }catch (Exception e){
//            return Result.error("error", e.getMessage());
//        }
//        String code = (String) session.getAttribute(email);
//        if(!code.equals(checkCode)) return Result.error("error", "验证码错误");
//        user.setEmail(email);
//        userService.updateById(user);
//        return Result.success(user);
        return null;
    }
    @ApiOperation("修改昵称")
    @PostMapping(value = "/edit_nickname")
    @ResponseBody
    public Result<?> edit_nickname(
            @RequestParam("nick_name") String nick_name,
            @ApiIgnore HttpSession session){
        User user = (User)session.getAttribute("User");
        if(user == null) return Result.error("用户为空");
        user.setNickName(nick_name);
        userService.updateById(user);
        return Result.success(user);
    }
    @ApiOperation("修改学号")
    @PostMapping(value = "/edit_number")
    @ResponseBody
    public Result<?> edit_number(
            @RequestParam("number") String number,
            @ApiIgnore HttpSession session
    ){
        User user = (User)session.getAttribute("User");
        if(user == null) return Result.error("用户为空");
        user.setNumber(number);
        userService.updateById(user);
        return Result.success(user);
    }
    @ApiOperation("修改用户头像")
    @PostMapping(value = "edit_image")
    @ResponseBody
    public Result<?> edit_image(
            @RequestParam("image") MultipartFile image,
            @ApiIgnore HttpSession session) throws IOException, SQLException {
        User user = (User)session.getAttribute("User");
        if(user == null) return Result.error("用户为空");
        Blob blob = new SerialBlob(image.getBytes());
        user.setImage(blob);
        userService.updateById(user);
        return Result.success(user);
    }
    @ApiOperation("获取所有用户")
    @GetMapping(value = "/getUsers")
    @ResponseBody
    public Result<?> list(){
        return Result.success(this.userService.list());
    }
}
