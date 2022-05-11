package com.dhu.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhu.Service.NewsService;
import com.dhu.config.Result;
import com.dhu.mapper.NewsMapper;
import com.dhu.pojo.News;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Author: ComingLiu
 * Date: 2022/5/11 0:12
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {
    @Override
    public Result<?> release(String time, String name, String content, String sender) {
        News news = new News(null, time, name, content, sender);
        save(news);
        return Result.success();
    }
}
