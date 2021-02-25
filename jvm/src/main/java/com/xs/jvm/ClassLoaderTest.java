package com.xs.jvm;

import com.sun.nio.zipfs.ZipInfo;

import java.lang.String;

/**
 * @author xueshuai
 * @date 2021/2/7 11:44
 * @description 双亲委派机制测试
 */
public class ClassLoaderTest {//类信息--》方法区
    private static String s = "1";//静态变量--》方法区
    private final String ss = "1";//常量---》方法区

    public static void main(String[] args) {
        String s = new String();
        System.out.println(s.getClass().getClassLoader());//BOOT根加载器输出是null
        Car car = new Car();
        Car car1 = new Car();
        Car car2 = new Car();

        System.out.println(car == car2);//false 栈中引用地址不一样


        Class<? extends Car> carClass = car.getClass();
        Class<? extends Car> car1Class = car1.getClass();
        Class<? extends Car> car2Class = car2.getClass();
        System.out.println(carClass);//class com.xs.jvm.Car
        System.out.println(car1Class);//class com.xs.jvm.Car
        System.out.println(car2Class);//class com.xs.jvm.Car
        System.out.println(car1Class == car2Class);//true

        ClassLoader classLoader = carClass.getClassLoader();
        System.out.println(classLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(classLoader.getParent());//sun.misc.Launcher$ExtClassLoader@3cd1a2f1
        System.out.println(classLoader.getParent().getParent());//null


        System.out.println(String.class.getClassLoader());//null
        System.out.println(ZipInfo.class.getClassLoader());//sun.misc.Launcher$ExtClassLoader@3cd1a2f1
        System.out.println(ClassLoaderTest.class.getClassLoader());//sun.misc.Launcher$AppClassLoader@18b4aac2


        String st = "abc";
        String ss = "abc";
        System.out.println(st == ss);


    }
}
