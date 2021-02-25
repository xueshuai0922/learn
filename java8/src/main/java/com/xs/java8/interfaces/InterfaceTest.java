package com.xs.java8.interfaces;

/**
 * @author xueshuai
 * @date 2021/1/26 22:00
 * @description
 */
public interface InterfaceTest {
    void test();

    default void getMoney() {
        System.out.println("this is a beautiful dream");
    }

    static String getName() {
        return "Object.getClass().getName()";
    }
}
