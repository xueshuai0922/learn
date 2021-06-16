package com.xs.design.observe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xueshuai
 * @date 2021/5/11 17:13
 * @description 观察者模式test  发布-订阅  监听器模式
 *              使用场景：一个事件对多个事件的状态行为改变 例如：mq的发布订阅模型、监听器的使用
 */
public class ObserveTest1 {
    public static void main(String[] args) {
        subject s = new subjectImp();
        AObserver aObserver = new AObserver();
        BObserver bObserver = new BObserver();
        s.addObs(aObserver);
        s.addObs(bObserver);
        s.act();


    }
}


interface  subject{

     void addObs(observer observer);

     void del(observer observer);

     void act();
}

class subjectImp implements subject{

    List<observer> observerLis=new ArrayList<>();
    //增加观察者
    @Override
    public void addObs(observer observer) {
        observerLis.add(observer);
    }


    //删除观察者
    @Override
    public void del(observer observer) {
        observerLis.add(observer);
    }

    //观察者做出反应
    @Override
    public void act() {
       observerLis.forEach((s)-> System.out.println(s.response()));
    }


}

interface  observer{
    String response();
}

class AObserver implements observer{

    @Override
    public String response() {
        return "这是A观察者发出的信息";
    }
}

class BObserver implements observer{

    @Override
    public String response() {
        return "这是B观察者发出的信息";
    }
}