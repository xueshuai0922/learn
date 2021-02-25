package com.xs.design.signleton;

import sun.security.jca.GetInstance;

/**
 * @author xueshuai
 * @date 2021/2/6 11:32
 * @description 完美单例：利用内部类  实现懒加载，又实现线程安全
 */
public class PerfectInnerSingleton {


    private PerfectInnerSingleton() {
    }


    private static class inner {
        private static PerfectInnerSingleton INSTANCE = new PerfectInnerSingleton();
    }


    public static PerfectInnerSingleton getInstance() {
        return inner.INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(PerfectInnerSingleton.getInstance().hashCode())).start();
        }
    }

}
