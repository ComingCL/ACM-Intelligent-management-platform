package com.dhu.Controller;

import com.dhu.Service.OJService;
import com.dhu.config.Result;
import com.dhu.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

/**
 * @Author: ComingLiu
 * @Date: 2022/5/29 20:13
 */
@Controller
@RequestMapping("/OJ")
public class OJController {
    @Autowired
    private OJService ojService;

    @ApiOperation("绑定洛谷账户, 这里如果已经绑定了, 可以重新绑定, 没有关系")
    @PostMapping("/blindingLuogu")
    @ResponseBody
    public Result<?> blindingLuogu(@ApiParam("输入洛谷用户编号, 必须是数字") @RequestParam("id") String id,
                                   @ApiIgnore HttpSession session){
        User user = (User) session.getAttribute("User");
        if(user == null){
            return Result.error("用户未登录");
        }
        return ojService.getLuoguInformation(id);
    }
}
