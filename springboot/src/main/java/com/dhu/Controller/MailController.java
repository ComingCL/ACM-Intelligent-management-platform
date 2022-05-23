package com.dhu.Controller;

import com.dhu.Service.MailService;
import com.dhu.config.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * Author: ComingLiu
 * Date: 2022/5/12 9:40
 */
@Controller
@RequestMapping("/Mail")
public class MailController {
    @Autowired
    private MailService mailService;

    @ApiOperation("获取邮箱验证码")
    @GetMapping("/getCheckCode")
    @ResponseBody
    public Result<?> getCheckCode(String email, @ApiIgnore HttpSession session){
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "您的邮箱验证码为: " + checkCode;
        try{
            mailService.sendMail(email, "注册验证码", message);
        }catch (Exception e){
            return Result.error(e.getMessage());
        }
//        把验证码放到域对象中
        session.setAttribute(email, checkCode);
        return Result.success(checkCode);
    }
}
