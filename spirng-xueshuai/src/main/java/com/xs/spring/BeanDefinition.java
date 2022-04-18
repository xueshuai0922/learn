package com.xs.spring;

/**
 * @author xueshuai
 * @date 2022/4/17 21:50
 * @description
 */
public class BeanDefinition {

    String type; //单例还是多例
    Class clazz; //属于哪个Class

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
