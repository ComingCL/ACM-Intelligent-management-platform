package com.dhu.Controller;

import com.dhu.Service.tSignAdminService;
import com.dhu.Service.tSignService;
import com.dhu.component.WebSocketServer;
import com.dhu.config.Result;
import com.dhu.pojo.User;
import com.dhu.pojo.tSignAdmin;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: ComingLiu
 * @Date: 2022/6/2 0:49
 */
@Controller
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    private tSignAdminService tsignAdminService;

    @ApiOperation("管理员添加签到信息, 系统自动获取发起人名称, 集训队员收到需要签到的要求," +
            " 此处需设置起始时间不晚于当前系统时间")
    @PostMapping("/adminAdd")
    @ResponseBody
    public Result<?> adminAdd(@ApiIgnore HttpServletRequest request,
                              @ApiParam("活动名称") @RequestParam("activity") String activity,
                              @ApiParam("开始时间(yyyy-MM-dd hh:mm:ss)") @RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss") String start,
                              @ApiParam("结束时间(yyyy-MM-dd hh:mm:ss)") @RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss") String end) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date startTime = format.parse(String.valueOf(start));
        Date endTime = format.parse(String.valueOf(end));
        User admin = (User) request.getSession().getAttribute("User");
        boolean ok = tsignAdminService.adminAdd(new tSignAdmin(null, activity, startTime, endTime, admin.getUsername(), admin.getId()));

        if(!ok) return Result.error("添加失败, 请联系技术人员");
        tsignAdminService.sendMessage("有新的签到");
        return Result.success();
    }
    @ApiOperation("显示当前管理员发布的签到数量")
    @GetMapping("/number")
    @ResponseBody
    public Result<?> number(@ApiIgnore HttpServletRequest request){
        User admin = (User) request.getSession().getAttribute("User");
        return Result.success(tsignAdminService.getNumber(admin.getId()));
    }
    @ApiOperation("显示当前管理员已发布的签到, 指定每页显示数据条数, 分页查询")
    @GetMapping("/display")
    @ResponseBody
    public Result<?> display(@ApiIgnore HttpServletRequest request,
                             @ApiParam("设置每页显示的数据条数") @RequestParam("number") Integer number,
                             @ApiParam("当前页数") @RequestParam("currentPage") Integer currentPage){
        User admin = (User) request.getSession().getAttribute("User");
        return Result.success(tsignAdminService.getAll(admin.getId(), number, currentPage));
    }
    @ApiOperation("管理员根据id, 删除已发布的签到, 返回值表示删除数据条数")
    @PostMapping("/delete")
    @ResponseBody
    public Result<?> delete(@ApiIgnore HttpServletRequest request,
                            @ApiParam("已发布签到的id") @RequestParam("id") String id){
        User admin = (User) request.getSession().getAttribute("User");
        return Result.success(tsignAdminService.deleteSign(admin.getId(), id));
    }
    @ApiOperation("管理员根据id, 修改已经发布的签到")
    @PostMapping("/modify")
    @ResponseBody
    public Result<?> modify(@ApiIgnore HttpServletRequest request,
                            @ApiParam("已发布签到的id") @RequestParam("id") String id,
                            @ApiParam("活动名称") @RequestParam("activity") String activity,
                            @ApiParam("开始时间(yyyy-MM-dd hh:mm:ss)") @RequestParam("startTime")@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss") String start,
                            @ApiParam("结束时间(yyyy-MM-dd hh:mm:ss)") @RequestParam("endTime")@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss") String end) throws ParseException {
        User admin = (User) request.getSession().getAttribute("User");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date startTime = format.parse(String.valueOf(start));
        Date endTime = format.parse(String.valueOf(end));
        boolean ok = tsignAdminService.modify(admin.getId(), id, activity, startTime, endTime);
        if(ok) return Result.success();
        return Result.error("修改失败, 请联系系统管理员");
    }
}
