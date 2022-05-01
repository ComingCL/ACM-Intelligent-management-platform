package com.dhu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 * Author: ComingLiu
 * Date: 2022/4/18 13:41
 */

@ApiModel("用户实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor//@Validated //数据校验 jsr303校验
@TableName("user")
public class User {
    @ApiModelProperty("用户名")
    @TableId("username")
    private String username;
    @ApiModelProperty("用户年龄")
    private Integer age;
    @ApiModelProperty("用户邮箱")
    private String email;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("学号")
    private String number;
    @ApiModelProperty("用户类型") // 0表示管理员, 1表示普通用户
    private Integer power;
}
