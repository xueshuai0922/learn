package com.xs.design.proxy.dynamic;

/**
 * @author xueshuai
 * @date 2021/4/8 20:12
 * @description
 */
public class CalMain {
    public static void main(String[] args) {
//        ProxyCal proxyCal = new ProxyCal();
//        proxyCal.cal();



        CalInterface calInterface = new ProxyCal();//面向接口编程 多态
        calInterface.cal();
    }


}
//定义一个行为规范
interface CalInterface {
    void cal();
}

//被代理对象
class RealCal implements CalInterface{

    @Override
    public void cal() {
        System.out.println("realCal 真正的实现者");
    }
}

//代理对象
class ProxyCal implements  CalInterface{
    RealCal realCal ;

    @Override
    public void cal() {
        realCal= new RealCal();
        doBefore();
        realCal.cal();
        doAfter();
    }

    private void doAfter() {
        System.out.println("方法执行后的增强");
    }

    private void doBefore() {
        System.out.println("方法执行前的增强");
    }
}
//    方法执行前的增强
//    realCal 真正的实现者
//    方法执行后的增强
