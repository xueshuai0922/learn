package com.xs.shiro.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.ParameterizedType;

/**
 * @author xueshuai
 * @date 2021/1/8 13:15
 * @description 返回结果类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult {
    private String code;
    private String message;
    private Object data;


    public static CommonResult success(Object data) {
        return new CommonResult("200", "成功", data);
    }

    public static CommonResult success(String message, Object data) {
        return new CommonResult("200", message, data);
    }

    public static CommonResult success(String message) {
        return new CommonResult("200", message, null);
    }

    public static CommonResult success() {
        return new CommonResult("200", "执行成功", null);
    }


    public static CommonResult fail(String message) {
        return new CommonResult("500", message, null);
    }
}
