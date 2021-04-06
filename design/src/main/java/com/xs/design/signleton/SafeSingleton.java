package com.xs.design.signleton;

/**
 * @author xueshuai
 * @date 2021/2/6 11:21
 * @description 双保证懒汉单例
 */
public class SafeSingleton {
    private static volatile SafeSingleton INSTANCE;

    private SafeSingleton() {
    }



    public static SafeSingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (SafeSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SafeSingleton();
                    return INSTANCE;
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(SafeSingleton.getInstance().hashCode())).start();
        }
    }
}
