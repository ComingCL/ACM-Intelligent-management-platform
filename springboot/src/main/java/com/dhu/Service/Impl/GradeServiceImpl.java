package com.dhu.Service.Impl;


import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhu.Service.GradeService;
import com.dhu.config.Result;
import com.dhu.mapper.GradeMapper;
import com.dhu.pojo.Grade;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: ComingLiu
 * @Date: 2022/5/20 0:01
 */
@Service
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {
    @Override
    public Result<?> insert(String name, String members, Date time, String awards) {
        Grade grade = new Grade(null, name, members, time, awards);
        save(grade);
        return Result.success(grade);
    }

    @Override
    public Result<?> deleteByIds(List<String> ids) {
        try{
            removeByIds(ids);
        }catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.success(ids.size());
    }

    @Override
    public Result<?> importProject(MultipartFile file) throws Exception{
//        解析文档
        final ImportParams importParams = new ImportParams();
        importParams.setHeadRows(1);

        final List<Grade> gradeList = ExcelImportUtil.importExcel(
                file.getInputStream(),
                Grade.class,
                importParams
        );
        for(Grade grade : gradeList){
            grade.setId(null);
        }
        try{
            saveBatch(gradeList);
        }catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.success();
    }

    @Override
    public Result<?> modifyById(String id, String name, String members, Date time, String awards) {
        try{
            updateById(new Grade(id, name, members, time, awards));
        }catch (Exception e){
            return Result.error(e.getMessage());
        }
        return Result.success();
    }
}
