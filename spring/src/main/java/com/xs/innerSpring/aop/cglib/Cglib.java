package com.xs.innerSpring.aop.cglib;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xueshuai
 * @date 2022/4/13 9:32
 * @description cglib动态代理实现
 */
public class Cglib {

    public static void main(String[] args) {
        //debugger 出cglib代理后的类对象
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"D:\\CGLIB");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(tank.class);
        enhancer.setCallback(new MethodProxyHandler());
        tank tank = (tank)enhancer.create();
        tank.move();


    }



}
class MethodProxyHandler implements MethodInterceptor {


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before");
        Object invoke = methodProxy.invokeSuper(o, objects);
        System.out.println("after");
        return invoke;
    }
}

class tank {
    public  void move(){
        System.out.println("tank  moving ");
    }
}
