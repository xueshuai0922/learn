package com.xs.service;

import com.xs.spring.BeanPostProcessor;
import com.xs.spring.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xueshuai
 * @date 2022/4/18 0:10
 * @description  bean 的后置方法实现类
 */
@Component
public class AopBeanPostProcessor  implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(String beanName, Object bean) {


        System.out.println("Bean 初始化之前"+beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(String beanName, Object bean) {

        if(beanName.equals("orderService")){
            //这里就可以进行动态代理返回
            Object proxyInstance = Proxy.newProxyInstance(AopBeanPostProcessor.class.getClassLoader(),
                    bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("aop方法执行前");
                    Object invoke = method.invoke(bean, args);
                    System.out.println("aop方法执行后");
                    return invoke;
                }
            });
            return proxyInstance;
        }
        return bean;
    }
}
