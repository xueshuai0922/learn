package com.xs.container.collection;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author xueshuai
 * @date 2021/3/4 19:14
 * @description
 */
public class ArrayListTest {

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        int i = 2;
        new Thread(()->{
            list.add(i);
            Object o = list.get(1);
        }).start();
        list.add(1);
    }
}
