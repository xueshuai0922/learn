package com.xs.container.map;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xueshuai
 * @date 2022/4/26 12:17
 * @description
 */
public class ConcurrentHashMapTest {
    public static void main(String[] args) {

        ConcurrentHashMap<String,Integer> map  = new ConcurrentHashMap<String,Integer>();
        //下面多线程运行，会出现BUG
        final Integer[] value = {map.get("word")};
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {

                if (value[0] == null) {
                    map.put("word", 1);
                } else {
                    map.put("word", value[0]++);
                }
            });
           thread.start();
        }
        System.out.println( value[0]);



    }
}
