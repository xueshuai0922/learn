package com.xs.innerSpring.inject_bean.construct;

import com.xs.innerSpring.inject_bean.bean.AInterface;
import com.xs.innerSpring.inject_bean.bean.BInterface;
import org.springframework.stereotype.Component;

/**
 * @author xueshuai
 * @date 2022/4/12 9:00
 * @description 通过构造器进行注入bean
 */
@Component
public class ConstructBean {


    private AInterface aInterface;
    private BInterface bInterface;

//    @Autowired
//    @Resource resource 只能通过setter和字段注入的方式，不能通过构造器的方式进行注入
    public ConstructBean(AInterface aInterface, BInterface bInterface) {
        System.out.println("ConstructBean");
        this.aInterface = aInterface;
        this.bInterface = bInterface;
    }

    public void run(){
        aInterface.a();
        bInterface.B();
    }
}
