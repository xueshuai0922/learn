package com.xs.thread.juc.safeproblem;

/**
 * @author xueshuai
 * @date 2021/2/27 12:15
 * @description 两人分别通过卡、存折来取钱
 */
public class MoneyExample {

    private static double money = 1000;

    public static void main(String[] args) {
        Object o = new Object();
        //模拟张三取钱线程
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                if (money > 0) {
                    money -= 100;
                    System.out.println("张三取钱第" + i + "【结束】，余额为" + money);
                } else {
                    System.out.println("余额不足100");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < 10; i++) {
                    if (money > 0) {
                        money -= 100;
                        System.out.println("【张三媳妇】取钱第" + i + "【结束】，余额为" + money);
                    } else {
                        System.out.println("余额不足100");
                    }
                }
            }
        }).start();

    }


}
