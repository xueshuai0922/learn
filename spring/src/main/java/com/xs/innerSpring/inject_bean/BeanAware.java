package com.xs.innerSpring.inject_bean;

import com.xs.innerSpring.inject_bean.bean.AInterface;
import com.xs.innerSpring.inject_bean.bean.BInterface;
import com.xs.innerSpring.inject_bean.construct.ConstructBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author xueshuai
 * @date 2022/4/12 8:41
 * @description
 */
@Component
public class BeanAware  implements ApplicationContextAware, InitializingBean {


    private  ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @Override
    public void afterPropertiesSet() throws Exception {

        Map<String, AInterface> beansOfType = applicationContext.getBeansOfType(AInterface.class);

        beansOfType.forEach((k,v)->{
            System.out.println("key:"+k+" value:"+v);
        });
        Map<String, BInterface> beansOfType1 = applicationContext.getBeansOfType(BInterface.class);
        beansOfType1.forEach((k,v)->{
            System.out.println("key:"+k+" value:"+v);
        });
        //name正常是首字符小写的类名
        ConstructBean constructBean = (ConstructBean)applicationContext.getBean("constructBean");
        constructBean.run();


    }
}
