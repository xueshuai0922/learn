package com.xs;

import com.xs.innerSpring.aop.AopServerice;
import com.xs.innerSpring.traction.UserServiceOne;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringApplicationTests {

    @Autowired
    UserServiceOne userServiceOne;
    @Autowired
    AopServerice aopServerice;



    /**
     * 测试spring事务传播机制
     */
    @Test
    public  void  tractionPropagation(){

        userServiceOne.updateUser();
    }



    @Test
    public void aopTest(){
        aopServerice.doSomething("参数");
    }

    @Test
    public void aopThrowTest(){
        aopServerice.doSomethingThrow("参数");
    }

}
