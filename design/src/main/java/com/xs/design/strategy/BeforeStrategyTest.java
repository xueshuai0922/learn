package com.xs.design.strategy;

/**
 * @author xueshuai
 * @date 2021/2/6 14:38
 * @description
 */
public class BeforeStrategyTest {
    public static void main(String[] args) {
        Cal cal = new Cal();
        int i = 2;
        switch (i) {
            case 1:
                cal.doCalOneOnly(i);
                break;
            case 2:
                cal.doCalTwoOnly(i);
                break;
            case 3:
                cal.doCalThreeOnly(i);
                break;
            default:
                break;
        }
    }

    public static class Cal {
        //假设每个方法只是固定只处理一个
        public void doCalOneOnly(int i) {
            System.out.println(i + 1);
        }

        public void doCalTwoOnly(int i) {
            System.out.println(i + 2);
        }

        public void doCalThreeOnly(int i) {
            System.out.println(i + 3);
        }
    }
}
