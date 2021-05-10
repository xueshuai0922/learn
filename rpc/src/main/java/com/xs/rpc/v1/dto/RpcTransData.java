package com.xs.rpc.v1.dto;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @author xueshuai
 * @date 2021/5/4 12:28
 * @description
 */
public class RpcTransData implements Serializable {
    private Class clazz;
    private String methodName;
    private  Object[] args;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public RpcTransData(Class clazz, String method, Object[] args) {
        this.clazz = clazz;
        this.methodName = method;
        this.args = args;
    }

    public RpcTransData() {
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }


    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
