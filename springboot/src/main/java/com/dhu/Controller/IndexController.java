package com.dhu.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Author: ComingLiu
 * Date: 2022/4/27 10:18
 */
@RestController
public class IndexController {
    @GetMapping("/")
    @ResponseBody
    public String index(){
        return "index";
    }
    @GetMapping("welcome")
    public String welcome(){
        return "welcome";
    }
    @GetMapping("main")
    public String main(){
        return "main";
    }
}
