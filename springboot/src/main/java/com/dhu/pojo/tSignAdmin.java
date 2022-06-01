package com.dhu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: ComingLiu
 * @Date: 2022/6/1 13:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_sign_admin")
public class tSignAdmin {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    private String activity;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    private Date endTime;
    private String initiator;
    private String initiatorId;
}
