package com.dhu.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhu.Service.GradeService;
import com.dhu.config.Result;
import com.dhu.mapper.GradeMapper;
import com.dhu.pojo.Grade;
import com.dhu.utils.ExcelTool;
import com.dhu.utils.FileUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @Author: ComingLiu
 * @Date: 2022/5/19 23:54
 */
@Controller
@RequestMapping(value = "/Grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;
    @Autowired
    private GradeMapper gradeMapper;

    @ApiOperation("插入一条数据")
    @PostMapping("/insert")
    @ResponseBody
    public Result<?> Insert(@ApiParam("比赛名称") String name, @ApiParam("比赛成员") String member,
                            @ApiParam("比赛时间") @DateTimeFormat(pattern = "yyyy-MM-dd") Date time,
                            @ApiParam("奖项名称") String awards){
        return gradeService.insert(name, member, time, awards);
    }
    @ApiOperation("文件导入数据")
    @PostMapping("/insertByDocument")
    @ResponseBody
    public Result<?> insertByDocument(MultipartFile file) throws Exception {
        if(file == null) return Result.error("导入失败, 文件为空");
        String postfix = ExcelTool.getPostfix(file.getOriginalFilename());
        if(!"xlsx".equals(postfix) && !"xls".equals(postfix)){
            return Result.error("导入失败, 请选择.xls或.xlsx格式");
        }
        return gradeService.importProject(file);
    }
    @ApiOperation(value = "文档模板下载", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @GetMapping("/downloadTheTemplate")
    @ResponseBody
    public void downloadTheTemplate(@ApiIgnore HttpServletRequest request,@ApiIgnore HttpServletResponse response){
        String filename = "template.xls";
        FileUtil.downloadFiles(request, response, filename);
    }
    @ApiOperation("根据编号删除数据, 如果成功会返回删除数据的条数, 否则返回错误原因")
    @PostMapping("/deleteByID")
    @ResponseBody
    public Result<?> deleteByID(@RequestParam(value = "ids[]") List<String> ids){
        for(String id : ids){
            if(gradeService.getById(id) == null){
                return Result.error("尝试删除一个不存在的数据");
            }
        }
        return gradeService.deleteByIds(ids);
    }
    @ApiOperation("根据编号进行批量修改, 如果成功返回成功, 否则返回错误原因")
    @PostMapping("/modifyById")
    @ResponseBody
    public Result<?> modifyById(@ApiParam("主键") String id,
                                @ApiParam("修改后的比赛名称") String name,
                                @ApiParam("修改后的成员") String members,
                                @ApiParam("修改后的时间") @DateTimeFormat(pattern = "yyyy-MM-dd") Date time,
                                @ApiParam("修改后的奖项") String awards){
        return gradeService.modifyById(id, name, members, time, awards);
    }
    @ApiOperation("获得全部比赛成绩, 分页显示, 每页二十条")
    @GetMapping("/getAllGrades")
    @ResponseBody
    public Result<?> get_All_Grades(int currentPage) {
        int pageNumber = 20;
        Page<Grade> page = new Page<>(currentPage, pageNumber);
        gradeMapper.selectPage(page, null);
        return Result.success(page.getRecords());
    }
    @ApiOperation("根据比赛名字查询比赛")
    @GetMapping("/getByName")
    @ResponseBody
    public Result<?> getByName(String name){
        QueryWrapper<Grade> wrapper = new QueryWrapper<>();
        wrapper.like("name", name);
        List<Grade> list = gradeMapper.selectList(wrapper);
        return Result.success(list);
    }
    @ApiOperation("获得比赛成绩条数")
    @GetMapping("/getTheNumberOfGrades")
    @ResponseBody
    public Result<?> get_the_number_of_grades(){
        return Result.success(gradeService.count());
    }
}
