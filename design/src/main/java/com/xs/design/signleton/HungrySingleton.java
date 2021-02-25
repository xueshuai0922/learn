package com.xs.design.signleton;

/**
 * @author xueshuai
 * @date 2021/2/6 10:40
 * @description 饿汉式单例
 */
public class HungrySingleton {
    private static final HungrySingleton INSTANCE = new HungrySingleton();


    private HungrySingleton() {
    }

    ;

    public static HungrySingleton getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(HungrySingleton.getInstance().hashCode());
            }).start();

        }
    }
}
