package com.xs.java8.interfaces;

import org.junit.Test;

/**
 * @author xueshuai
 * @date 2021/1/26 22:03
 * @description
 */
public class InterfaceImpTest implements InterfaceTest {
    @Override
    public void test() {

    }

    @Test
    public void test2() {
        InterfaceTest interfaceTest = () -> {
            System.out.println("test");
        };

        interfaceTest.test();
        interfaceTest.getMoney();
        System.out.println(InterfaceTest.getName());

    }
}
