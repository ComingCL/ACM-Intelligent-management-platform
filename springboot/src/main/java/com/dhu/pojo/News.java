package com.dhu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

/**
 * @Author: ComingLiu
 * @Date: 2022/5/10 19:45
 */

@ApiModel("新闻实体类")
@Data
@TableName("news")
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @ApiModelProperty("新闻id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    Long id;
    @ApiModelProperty("时间")
    String time;
    @ApiModelProperty("新闻名")
    String name;
    @ApiModelProperty("内容")
    String content;
    @ApiModelProperty("发送人")
    String sender;
}
