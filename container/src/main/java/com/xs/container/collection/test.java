package com.xs.container.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xueshuai
 * @date 2022/4/2 14:13
 * @description
 */
public class test {


    public static void main(String[] args) {
        stream();
        arrayListString();
        linkedListString();
    }

    private static void stream() {
        long s = System.currentTimeMillis();
        int start =1;
        int end =300000;
        List<Integer> collect = Stream.iterate(start, item -> item + 1).limit(end).collect(Collectors.toList());
        System.out.println("stream "+(System.currentTimeMillis()-s));
    }

    private static void arrayListString() {
        long s = System.currentTimeMillis();
        int start =1;
        int end =300000;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < end; i++) {
            list.add(i);
        }
        System.out.println("listString "+(System.currentTimeMillis()-s));
    }
    private static void linkedListString() {
        long s = System.currentTimeMillis();
        int start =1;
        int end =300000;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < end; i++) {
            list.add(i);
        }
        System.out.println("listString "+(System.currentTimeMillis()-s));
    }
}
