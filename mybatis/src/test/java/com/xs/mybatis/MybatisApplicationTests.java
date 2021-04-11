package com.xs.mybatis;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xs.mybatis.login.entity.User;
import com.xs.mybatis.login.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.SQLOutput;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisApplicationTests {

	//todo  怎么实现的代理，自己实现一遍
	@Resource
	private UserMapper userMapper;



	//------------------------------------mybatis plus开始-----------------------

	//查询全部返回list
	@Test
	public void selectAll(){
		List<User> users = userMapper.selectList(null);
		users.stream().forEach(System.out::println);
	}
	//分页查询
	@Test
	public void selectByPage(){
//		IPage<User> userIPage = userMapper.selectPage();
	}
	//条件查询
	@Test
	public void selectByWhere(){
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.like("name","Tom");
		userMapper.selectList(wrapper).forEach(System.out::println);
	}
	//利用lambda 进行链式查询
	@Test
	public void lambdaSelect(){
		//lambda 查询
		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(User::getAge,10);
		userMapper.selectList(lambdaQueryWrapper).forEach(System.out::println);

		//链式查询
		new LambdaQueryChainWrapper<>(userMapper)
				.eq(User::getAge, 10)
				.list().forEach(System.out::println);




	}


	@Test
	public void insert(){
		User user = new User();
		user.setName("插入");
		user.setAge(10);
		user.setEmail("827681776@qq.com");

		int insert = userMapper.insert(user);
		boolean insertFlag = insert>0? true:false;
		System.out.println("插入是否成功："+insertFlag);
	}
	@Test
	public void del(){
		int delete = userMapper.deleteById(2);
		boolean flag = delete>0? true:false;
		System.out.println("插入是否成功："+flag);
	}
	//------------------------------------mybatis plus结束-----------------------

}
