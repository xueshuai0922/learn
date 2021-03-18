package com.xs.container.collection;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author xueshuai
 * @date 2021/3/5 13:59
 * @description list中remove出现的问题
 */
public class ListRemoveTest {

    public static void main(String[] args) {
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 10; i++) {
            copyOnWriteArrayList.add("xueshuai"+i);
        }
        for(String s: copyOnWriteArrayList){
            if("xueshuai5".equals(s)){
                copyOnWriteArrayList.remove(s);
            }
        }
        copyOnWriteArrayList.forEach(System.out::println);

        System.out.println();

        //--------------------------arraylist.remove() 和vector.remove----------------
        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            arrayList.add("xueshuai"+i);
        }
        for(String s: arrayList){
            if("xueshuai5".equals(s)){
                arrayList.remove(s);
                System.out.println("删除成功");
            }
            //调用next（） 调用checkForComodification(),修改次数和期望次数不一样，出现java.util.ConcurrentModificationException
        }
        arrayList.forEach(System.out::println);

    }

}
