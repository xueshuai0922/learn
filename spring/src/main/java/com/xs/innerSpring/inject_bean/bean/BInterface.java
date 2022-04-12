package com.xs.innerSpring.inject_bean.bean;

import org.springframework.stereotype.Component;

/**
 * @author xueshuai
 * @date 2022/4/12 8:27
 * @description
 */
@Component
public class BInterface {
    public  void B(){
        System.out.println("method B");
    }
}
