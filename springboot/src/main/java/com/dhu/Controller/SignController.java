package com.dhu.Controller;

import com.dhu.Service.tSignAdminService;
import com.dhu.Service.tSignService;
import com.dhu.config.Result;
import com.dhu.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: ComingLiu
 * @Date: 2022/6/1 2:26
 */
@Controller
@RequestMapping("/Sign")
public class SignController {
    @Autowired
    private tSignAdminService tsignAdminService;
    @Autowired
    private tSignService tsignService;
    @ApiOperation("队员根据签到id签到")
    @PostMapping("/userSign")
    @ResponseBody
    public Result<?> userSign(@ApiIgnore HttpServletRequest request,
                             @ApiParam("签到的id") @RequestParam("sid") String sid){
        User user = (User) request.getSession().getAttribute("User");
        int result = tsignService.userSign(user.getUsername(), user.getId(), sid);
        if(result == 0) return Result.error("系统内部错误, 请联系技术人员");
        if(result == 1) return Result.success();
        if(result == 2) return Result.error("已经签到过了");
        if(result == 3) return Result.error("签到已过期");
        return Result.error("这不可能!");
    }
    @ApiOperation("根据系统时间, 查看当前活动的签到信息, 只有队员有签到")
    @GetMapping("/getSignId")
    @ResponseBody
    public Result<?> getSignId(@ApiIgnore HttpServletRequest request){
        List<String> list = new ArrayList<>();
        User user = (User) request.getSession().getAttribute("User");
        if(user.getIsmember() != null && user.getIsmember() != 1) return Result.success(list);
        return Result.success(tsignAdminService.getSignId());
    }
}
