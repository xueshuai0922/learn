package com.xs.innerSpring.inject_bean.filed;

import com.xs.innerSpring.inject_bean.bean.AInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xueshuai
 * @date 2022/4/12 9:06
 * @description 字段注入
 */
@Component
public class FiledBean {

    @Autowired
//    @Resource
    private AInterface aInterface;
}
