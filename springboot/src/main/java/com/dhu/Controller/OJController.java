package com.dhu.Controller;

import com.dhu.Service.OJService;
import com.dhu.Service.UserService;
import com.dhu.config.Result;
import com.dhu.pojo.User;
import com.dhu.utils.TwoTuple;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: ComingLiu
 * @Date: 2022/5/29 20:13
 */
@Controller
@RequestMapping("/OJ")
public class OJController {
    @Autowired
    private UserService userService;
    @Autowired
    private OJService ojService;

    @ApiOperation("绑定洛谷账户, 这里如果已经绑定了, 可以重新绑定, 没有关系")
    @PostMapping("/blindingLuogu")
    @ResponseBody
    public Result<?> blindingLuogu(@ApiParam("输入洛谷用户编号, 必须是数字") @RequestParam("id") String id,
                                   @ApiIgnore HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("User");
        if(user == null){
            return Result.error("用户未登录");
        }
        TwoTuple<HashMap<String, Integer>, Integer> twoTuple = ojService.getLuoguInformation(request, id);
//        传递rating
        user.setRating(twoTuple.second);
        userService.updateById(user);
//        传递题目情况
        userService.modifyLuoguId(user.getId(), id);
        return Result.success(twoTuple.first);
    }
}
