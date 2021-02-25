package com.xs.java8.funcationInterface;

/**
 * @author xueshuai
 * @date 2021/1/24 15:57
 * @description 接口中只有一个抽象方法的接口，为函数式接口
 */
@FunctionalInterface
public interface FunctionInterfaceTest<T> {
    int compare(T a, T b);
}
