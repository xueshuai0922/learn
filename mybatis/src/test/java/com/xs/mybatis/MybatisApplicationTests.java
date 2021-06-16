package com.xs.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xs.mybatis.login.controller.UserController;
import com.xs.mybatis.login.entity.User;
import com.xs.mybatis.login.mapper.UserMapper;
import com.xs.mybatis.sharding_jdbc.order.entity.Detail;
import com.xs.mybatis.sharding_jdbc.order.entity.TOrder;
import com.xs.mybatis.sharding_jdbc.order.mapper.DetailMapper;
import com.xs.mybatis.sharding_jdbc.order.mapper.TOrderMapper;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisApplicationTests {

    //todo  怎么实现的代理，自己实现一遍
    @Resource
    private UserMapper userMapper;


    //------------------------------------mybatis plus开始-----------------------

    //查询全部返回list
    @Test
    public void selectAll() {
        List<User> users = userMapper.selectList(null);
        users.stream().forEach(System.out::println);
    }

    //分页查询
    @Test
    public void selectByPage() {
//		IPage<User> userIPage = userMapper.selectPage();
    }

    //条件查询
    @Test
    public void selectByWhere() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name", "Tom");
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    //利用lambda 进行链式查询
    @Test
    public void lambdaSelect() {
        //lambda 查询
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getAge, 10);
        userMapper.selectList(lambdaQueryWrapper).forEach(System.out::println);

        //链式查询
        new LambdaQueryChainWrapper<>(userMapper)
                .eq(User::getAge, 10)
                .list().forEach(System.out::println);


    }


    @Test
    public void insert() {
        User user = new User();
        user.setName("xs");
        user.setAge(10);
        user.setEmail("827681776@qq.com");

        int insert = userMapper.insert(user);
        boolean insertFlag = insert > 0 ? true : false;
        System.out.println("插入是否成功：" + insertFlag);
    }

    @Test
    public void del() {
        int delete = userMapper.deleteById(2);
        boolean flag = delete > 0 ? true : false;
        System.out.println("删除是否成功：" + flag);
    }
    //------------------------------------mybatis plus结束-----------------------

    @Resource
    UserController controller;

    @Test
    public void insert2() {
        controller.insert();
    }


    @Resource
    TOrderMapper orderMapper;

    @Resource
    DetailMapper detailMapper;


    @ShardingTransactionType(TransactionType.XA)
    @Transactional(rollbackFor = RuntimeException.class)
    @Test
    //todo
    public void insert3() {
        try {
            for (int i = 0; i < 100; i++) {
                TOrder tOrder = new TOrder();
                tOrder.setOrderId(i);
                tOrder.setOrderName("suibian");
                tOrder.setOrderDesc("nicai");


                orderMapper.insert(tOrder);

                Detail detail = new Detail();
//                detail.setDetailId(i + 100);
                detail.setOrderId(i);
                detail.setOrderDetailName("orderDetailName");
                detailMapper.insert(detail);

            }
        } catch (Exception e) {
            System.out.println("----------------------------------");
            e.printStackTrace();
            throw e;
        } finally {
        }
    }

    /**
     * 测试分库关联查询
     */
    @Test
    public void selec() {
        List<Map> select = orderMapper.select();
        System.out.println(select);
    }

    /**
     * 测试广播表
     */

    @Test
    public void insertConfig() {
        orderMapper.insertConfig();
    }

    /**
     * 测试分布式事务
     */
    @Test
    public void tractional() {

    }
    /**
     * 更新
     */
    @Test
    public void update(){
        orderMapper.update();
    }

    /**
     * 测试读写分离 ----插入master
     */
    @Test
    public  void  insertUser(){
        orderMapper.insertUser();
    }

    /**
     * 测试读写分离 从slave 查询
     */
    @Test
    public  void  selectUser(){
        orderMapper.selectUser();
    }

}
