package com.xs.container.collection;

import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author xueshuai
 * @date 2021/3/5 11:52
 * @description ArrayList 是不安全的，那么安全的list容器有Vector，Collections.synchronizedList、CopyOnWriteArrayList
 */
public class SyncArrayList {
    private static Vector<Object> vectorlists = new Vector<>();//线程安全的
    private static ArrayList<Object> arrayList = new ArrayList<>(); //线程不安全，add方法产生不安全
    private static List<Object> syncList = Collections.synchronizedList(new ArrayList<>());//线程安全的
    private static OwnerArrayList ownerArrayList = new OwnerArrayList();//自制List，实现扩容，方法增加synchronized就是线程安全的
    private static CopyOnWriteArrayList<Object> copyOnWriteArrayList = new CopyOnWriteArrayList<>(); //线程安全的
    //todo ConcurrentLinkedQueue


    public static void main(String[] args) {

        //100个线程进行对容器增加数据
        for (int i = 0; i < 100; i++) {

            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    copyOnWriteArrayList.add(j);
                }
            }).start();
        }
        //主线程睡1000ms 让其他线程把正常程序走完
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(copyOnWriteArrayList.size());//10000


    }
}
