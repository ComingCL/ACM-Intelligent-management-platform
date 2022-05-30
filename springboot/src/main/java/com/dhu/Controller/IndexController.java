package com.dhu.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Author: ComingLiu
 * Date: 2022/4/27 10:18
 */
@Controller
@RequestMapping("/Index")
public class IndexController {
    @GetMapping("/main")
    @ResponseBody
    public String index(){
        return "index";
    }
}
