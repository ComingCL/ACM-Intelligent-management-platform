package com.dhu.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhu.config.Result;
import com.dhu.pojo.News;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

/**
 * Author: ComingLiu
 * Date: 2022/5/11 0:09
 */
public interface NewsService extends IService<News> {
    Result<?> release(String time, String name, String content, String sender);
}
