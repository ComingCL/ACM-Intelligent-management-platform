package com.dhu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ComingLiu
 * @Date: 2022/4/18 13:41
 */

@ApiModel("用户实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor//@Validated //数据校验 jsr303校验
@TableName("user")
public class User {
    @ApiModelProperty("用户id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    @ApiModelProperty("用户名")
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
    @ApiModelProperty("是否激活")
    private Integer activation;
    @ApiModelProperty("头像")
    private Object image;
    @ApiModelProperty("洛谷id")
    private String luoguId;
    @ApiModelProperty("是否集训队队员")
    private Integer ismember;
    @ApiModelProperty("评分")
    private Integer rating;
}
