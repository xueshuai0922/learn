package com.xs.design.signleton;

/**
 * @author xueshuai
 * @date 2021/2/6 12:19
 * @description 终究单例
 */
public enum EnumSingleton {
    INSTANCE;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(EnumSingleton.INSTANCE.hashCode())).start();
        }
    }
}
