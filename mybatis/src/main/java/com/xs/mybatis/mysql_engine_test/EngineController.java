package com.xs.mybatis.mysql_engine_test;

import com.xs.mybatis.login.entity.User;
import com.xs.mybatis.login.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author xueshuai
 * @date 2021/5/19 13:11
 * @description 对MySQL中两个存储引擎进行测试
 */
@RestController
public class EngineController {


    @Resource
    private UserMapper userMapper;

    /**
     * 批量插入
     */
    @GetMapping("/insertBatch")
    public void insertBatch(){
        ArrayList<User> list = new ArrayList<>();
        long count = 100*100*10;
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setAge(10);
            user.setName("xs");
            user.setEmail("827681776@qq.com");
            list.add(user);

        }
        long start= System.currentTimeMillis();
        userMapper.insertBatch(list);
        System.out.println((System.currentTimeMillis()-start)+" ms");
    }
//    <==    Updates: 100000    myisam  4177 ms 6312 ms 5970 ms 6393 ms
//    Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@10238b05]
//            6960 ms



//    <==    Updates: 100000  innodb 4155 ms 4393 ms 4378 ms 4431 ms  4337 ms  4449 ms
//    Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@2471c2de]
//            6691 ms

    @GetMapping("/updateBatch")
    public void updateBatch(){
        ArrayList<User> list = new ArrayList<>();
        long count = 100*100*10;
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setAge(10);
            user.setName("更新后的值");
            user.setEmail("827681776@qq.com");
            list.add(user);

        }
        long start= System.currentTimeMillis();
        userMapper.updateBatch(list);
        System.out.println((System.currentTimeMillis()-start)+" ms");
    }
}
