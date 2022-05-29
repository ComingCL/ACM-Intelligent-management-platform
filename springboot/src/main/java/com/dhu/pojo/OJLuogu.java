package com.dhu.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ComingLiu
 * @Date: 2022/5/29 20:19
 */
@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("oj_luogu")
public class OJLuogu {
    private String id;
    private String name;
    private String algorithm;
    private String difficulty;
}
