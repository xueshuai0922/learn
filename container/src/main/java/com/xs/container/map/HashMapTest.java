package com.xs.container.map;

import java.util.HashMap;

/**
 * @author xueshuai
 * @date 2021/3/6 12:19
 * @description
 */
public class HashMapTest {
    public static void main(String[] args) {
        HashMap<Object, Object> hashMap = new HashMap<>(9);

        hashMap.put(6,4);//  ()0000 0110

        System.out.println(66 & 14);//1
        System.out.println(66%15);//1


        System.out.println(76 & 15);//12
        System.out.println(76 %16);//12


        System.out.println(70 ^ 70>>4);

    }
}
