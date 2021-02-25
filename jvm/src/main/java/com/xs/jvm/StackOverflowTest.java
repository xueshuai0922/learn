package com.xs.jvm;

/**
 * @author xueshuai
 * @date 2021/2/8 9:49
 * @description 栈溢出测试
 */
public class StackOverflowTest {


    public static void a() {
        b();
    }

    public static void b() {
        a();
    }

    public static void main(String[] args) {
        a();
    }
}
