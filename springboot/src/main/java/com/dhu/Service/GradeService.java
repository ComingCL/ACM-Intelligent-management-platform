package com.dhu.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhu.config.Result;
import com.dhu.pojo.Grade;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @Author: ComingLiu
 * @Date: 2022/5/20 0:01
 */
public interface GradeService extends IService<Grade> {
    Result<?> insert(String name, String members, Date time, String awards);
    Result<?> deleteByIds(List<String> ids);
    Result<?> importProject(MultipartFile file) throws Exception;
    Result<?> modifyById(String id, String name, String members, Date time, String awards);
}
