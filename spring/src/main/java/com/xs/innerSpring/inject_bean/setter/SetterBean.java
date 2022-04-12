package com.xs.innerSpring.inject_bean.setter;

import com.xs.innerSpring.inject_bean.bean.AInterface;
import com.xs.innerSpring.inject_bean.bean.BInterface;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author xueshuai
 * @date 2022/4/12 8:21
 * @description setter 注入方式
 */

@Component
public class SetterBean {

    private AInterface aInterface;
    private BInterface bInterface;

//    @Autowired
    @Resource
    public void setaInterface(AInterface aInterface) {
        this.aInterface = aInterface;
    }

//    @Autowired
    public void setbInterface(BInterface bInterface) {
        this.bInterface = bInterface;
    }
}
