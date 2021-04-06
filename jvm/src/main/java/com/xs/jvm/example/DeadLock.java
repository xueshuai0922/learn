package com.xs.jvm.example;

import javax.sound.midi.Soundbank;
import java.io.IOException;

/**
 * @author xueshuai
 * @date 2021/3/29 14:22
 * @description 死锁测试
 */
public class DeadLock {
    private  static  Object a = new Object();
    private  static  Object b = new Object();


    public void a(){
        synchronized (a){
            synchronized (b){
                System.out.println("a");
                try {
                    System.in.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    public void b(){
        synchronized (b){
            synchronized (a){
                System.out.println("b");
                try {
                    System.in.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        new Thread(()->{
            deadLock.a();
        }).start();
        new Thread(()->{
            deadLock.b();
        }).start();


    }
}
