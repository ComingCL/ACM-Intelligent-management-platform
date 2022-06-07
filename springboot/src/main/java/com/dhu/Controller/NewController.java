package com.dhu.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhu.Service.NewsService;
import com.dhu.config.Result;
import com.dhu.mapper.NewsMapper;
import com.dhu.pojo.News;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Author: ComingLiu
 * Date: 2022/5/10 19:40
 */
@Controller
@RequestMapping("/News")
public class NewController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsMapper newsMapper;
    @PostMapping("/release")
    @ApiOperation("新闻发布功能")
    @ResponseBody
    public Result<?> release(
            @ApiParam("新闻名")
            @RequestParam("name") String name,
            @ApiParam("新闻内容")
            @RequestParam("content") String content,
            @ApiParam("发送人")
            @RequestParam("sender") String sender){
        //        获取当前时间
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
//        News news = new News(null, localDateTime.format(formatter), name, content, sender);
        String time = localDateTime.format(formatter);
        newsService.release(time, name, content, sender);
        return Result.success(new News(null, time, name, content, sender));
    }
    @ApiOperation("新闻分页显示, 每页十条")
    @GetMapping(value = "/getAll-news")
    @ResponseBody
    public Result<?> getAll_news(Integer currentPage){
        int pageNumber = 10;// 每页显示20条数据
        Page<News> page = new Page<>(currentPage, pageNumber);
        newsMapper.selectPage(page, null);
        return Result.success(page.getRecords());
    }

    @ApiOperation("获取新闻总条数")
    @GetMapping(value = "/get-the-number-of-news-items")
    @ResponseBody
    public Result<?> get_the_number_of_news_items(){
        return Result.success(newsService.count());
    }
}
