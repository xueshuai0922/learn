package com.xs.container.collection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author xueshuai
 * @date 2021/3/4 19:01
 * @description
 */
public class CollectionTest {

    public static void main(String[] args) {
        Collection collection = new ArrayList<>();
        collection.add(1);//自动装箱  1变为 new Integer
        collection.add(true);
        collection.add("string");
        System.out.println(collection);
    }
}
