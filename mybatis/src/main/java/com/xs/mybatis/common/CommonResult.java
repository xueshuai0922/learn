package com.xs.mybatis.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author xueshuai
 * @date 2021/4/10 20:58
 * @description
 */
@Data
@AllArgsConstructor
public class CommonResult {

    private Object data;
    private int code;
    private String message;

    public static CommonResult success(Object data){
        return  new CommonResult(data,200,"成功");
    }
}
