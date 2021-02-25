package com.xs.design.chainrespons;

/**
 * @author xueshuai
 * @date 2021/2/20 9:49
 * @description 责任链 ---》 请求发送和多个请求处理避免耦合
 * 请求处理由一个个链组成，处理完自动进行传递
 * 优点：请求处理，运行时确定，动态处理请求
 */
public class ChainResponsibility {


    public static void main(String[] args) {
        //进行链式装置
        ChainHandlerImpOne one = new ChainHandlerImpOne();
        ChainHandlerImpTwo two = new ChainHandlerImpTwo();
        //指定下一个处理器
        one.setNext(two);

        //提交请求
        one.doHandle("two");//第二个处理器进行处理
        one.doHandle("three");//没有处理器进行处理
    }


    //责任抽象类
    public static abstract class ChainHandler {
        //组成链式的重要属性，指向下一个的处理器
        private ChainHandler next;

        public ChainHandler getNext() {
            return next;
        }

        public void setNext(ChainHandler next) {
            this.next = next;
        }

        //抽象方法
        public abstract void doHandle(String msg);
    }

    public static class ChainHandlerImpOne extends ChainHandler {

        @Override
        public void doHandle(String msg) {
            if ("one".equals(msg)) {
                System.out.println("第一个处理器进行处理");
            } else {
                ChainHandler next = getNext();
                if (next != null) {
                    next.doHandle(msg);
                } else {
                    System.out.println("没有处理器进行处理");
                }
            }
        }
    }

    public static class ChainHandlerImpTwo extends ChainHandler {

        @Override
        public void doHandle(String msg) {
            if ("two".equals(msg)) {
                System.out.println("第二个处理器进行处理 ");
            } else {
                ChainHandler next = getNext();
                if (next != null) {
                    next.doHandle(msg);
                } else {
                    System.out.println("没有处理器进行处理");
                }
            }
        }
    }
}
