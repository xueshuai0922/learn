package com.xs.nacosclient.common;

import lombok.Builder;
import lombok.Data;

/**
 * @author xueshuai
 * @date 2021/5/7 15:24
 * @description
 */
@Data
@Builder
public class CommonResult {

    private Object data;
    private String msg;
    private int code;

    public static CommonResult success(Object s) {
       return CommonResult.builder().data(s).build();
    }

    public static CommonResult successMsg(String s) {
        return CommonResult.builder().msg(s).build();
    }
}
