package com.dhu.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: ComingLiu
 * Date: 2022/4/26 20:34
 */

// 返回给前台数据的包装类
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private String code; // 成功还是失败
    private String msg;// 如果失败, 失败码是多少, 通过失败码确定错误是什么
    private T data;// 数据

    public Result (T data){
        this.data = data;
    }
    public static Result<?> success(){
        Result<?> result = new Result<>();
        result.setCode("1");
        result.setMsg("成功");
        return result;
    }

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>(data);
        result.setCode("1");
        result.setMsg("成功");
        return result;
    }

    public static Result<?> error(String msg){
        Result<?> result = new Result<>();
        result.setCode("0");
        result.setMsg(msg);
        return result;
    }
}
