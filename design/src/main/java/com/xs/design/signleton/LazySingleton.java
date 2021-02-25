package com.xs.design.signleton;

/**
 * @author xueshuai
 * @date 2021/2/6 11:13
 * @description 懒汉单例 需要是加载，进行实例化，但是有线程安全问题
 */
public class LazySingleton {
    private static LazySingleton INSTANCE;

    private LazySingleton() {
    }

    ;


    public static LazySingleton getInstance() {
        //这里如果多个线程，判断后进入，就会有多个实例出现
        if (INSTANCE == null) {
            //这里给线程sleep为了测试能让其他下线程进来
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new LazySingleton();
            return INSTANCE;
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(LazySingleton.getInstance().hashCode())).start();
        }
    }
}
