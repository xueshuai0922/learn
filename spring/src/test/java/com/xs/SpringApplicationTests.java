package com.xs;

import com.xs.innerSpring.traction.UserServiceOne;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringApplicationTests {

    @Autowired
    UserServiceOne userServiceOne;

    /**
     * 测试spring事务传播机制
     */
    @Test
    public  void  tractionPropagation(){
        userServiceOne.updateUser();
    }


}
