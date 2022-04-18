package com.xs.innerSpring.aop;

import org.springframework.stereotype.Component;

/**
 * @author xueshuai
 * @date 2022/4/12 12:42
 * @description
 */
@Component
public class AopServerice {


    @Aop
    public String doSomething(String arg){
        System.out.println("执行do方法+ "+arg);
        return "执行do方法+ "+arg;
    }

    @Aop
    public String doSomethingThrow (String arg){
       try {
           int i=1/0;
       }catch (Exception e){
           throw  e;
       }
        return "";
    }
}
