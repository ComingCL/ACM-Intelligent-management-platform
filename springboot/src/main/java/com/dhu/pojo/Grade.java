package com.dhu.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: ComingLiu
 * @Date: 2022/5/19 23:55
 */
@ApiModel("成绩管理")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("grade")
public class Grade {
    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Excel(name = "序号", width = 10)
    private String id;
    @ApiModelProperty("比赛名称")
//    needMerge就是比较当前和上一行是否一样, 如果一样就合并单元格
    @Excel(name = "比赛名称", width = 50, needMerge = true)
    private String name;
    @ApiModelProperty("小组成员")
    @Excel(name = "小组成员", width = 50, needMerge = true)
    private String members;
    @ApiModelProperty("比赛时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE_TIME)
    @Excel(name = "比赛时间", width = 50, format = "yyyy-MM-dd")
    private Date time;
//    这里可以考虑把图片链接放上去
    @ApiModelProperty("奖项名称")
    @Excel(name = "奖项名称", width = 50, needMerge = true)
    private String awards;
}
