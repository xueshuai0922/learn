package com.xs.jvm.classloder;

/**
 * @author xueshuai
 * @date 2021/3/21 23:34
 * @description 双亲委派加载class ,往上先看是否已经加载class ,然后往下找class进行加载
 */
public class DoubleParentLoadAndFindClass {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> ssss = DoubleParentLoadAndFindClass.class.getClassLoader().loadClass("ssss");

    }


}
