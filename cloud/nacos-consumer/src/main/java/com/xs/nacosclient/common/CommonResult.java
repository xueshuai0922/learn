package com.xs.nacosclient.common;

/**
 * @author xueshuai
 * @date 2021/5/7 15:24
 * @description
 */
public class CommonResult {

    private Object data;
    private String msg;
    private int code;

    public CommonResult() {
    }

    public CommonResult(Object data, String msg, int code) {
        this.data = data;
        this.msg = msg;
        this.code = code;
    }

    public static CommonResult success(Object data){
        return  new CommonResult(data,"成功",200);
    }

    public static CommonResult successMsg(String msg){
        return  new CommonResult(null,msg,200);
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
