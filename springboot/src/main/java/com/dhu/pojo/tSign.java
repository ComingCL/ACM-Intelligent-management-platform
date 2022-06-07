package com.dhu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: ComingLiu
 * @Date: 2022/6/1 13:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_sign")
@ApiModel("所有签到信息")
public class tSign {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @ApiModelProperty("签到id, 唯一")
    private String id;
    @ApiModelProperty("签到原因, 使用JsonFormat格式化时间")
    private String activity;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd hh:mm:ss",
            timezone = "GMT+8"
    )
    @ApiModelProperty("签到时间")
    private Date time;
    @ApiModelProperty("签到人员名字")
    private String person;
    @ApiModelProperty("对应管理员发送的签到id")
    private String sid;
    @ApiModelProperty("签到人员id")
    private String uid;
}
