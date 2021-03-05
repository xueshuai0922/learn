package com.xs.container.collection;

import java.util.Arrays;
import java.util.List;

/**
 * @author xueshuai
 * @date 2021/3/5 8:58
 * @description 手写一个Arraylist简易版
 * 1.初始化
 *          1）字段 ：存放数据数量，一个是容器容量，一个object的数组
 * 2.新增
 *          1）确定内部数组容量  2）如果新增后的数据量大于数组容量，要进行自动扩容
 * 3.查询
 * 正常获取内部数组的值
 */
public class OwnerArrayList {

    private Object[] elementData;
    private int length;
    private int capacity;


    private static int DEFAULT_CAPACITY = 10;


    public OwnerArrayList() {
        elementData = new Object[]{};
        length = 0;
        capacity = 0;
    }


    public synchronized void add(Object o) {
        //确定容量  为什么在add的时候进行定容量呢，为了节省内存（积少成多），思想就是懒加载，需要的时候分配，不需要的时候不给
        ensureCapacity(length);
        //当数据量大于容量时候，进行扩容
        if (length+1 > capacity) {
            grow();
        }
        //赋值进行
        elementData[length++] = o;
    }

    //确定容量
    private  synchronized void ensureCapacity(int length) {
        //如果elementData为空,初始化默认为10的容量数组
        if (length == 0) {
            //length和10比较，初始化容量
            capacity = Math.max(length + 1, DEFAULT_CAPACITY);
            elementData = new Object[capacity];
        }
    }

    //当数据量大于容量时候，进行扩容
    private synchronized void grow() {
        int oldCapacity = capacity;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        capacity=newCapacity;
        elementData = Arrays.copyOf(elementData, capacity);
        System.out.println("发生一次扩容");
    }

    //获取列表的size
    public int size() {
        return length;
    }

    //列表内数组容量
    public  synchronized int getCapacity() {
        return capacity;
    }

    public synchronized  Object get(int index) {
        return elementData[index];
    }


    public static void main(String[] args) {
        OwnerArrayList list = new OwnerArrayList();

        //-----------------超过10次的加入-------------
        for (int i = 0; i < 16; i++) {
            list.add(33);
        }
        for (int i = 0; i < 16; i++) {
            System.out.println(list.get(i));
        }
        System.out.println("list的数据量" + list.size());
        System.out.println("list的容量" + list.getCapacity());

    }


}
