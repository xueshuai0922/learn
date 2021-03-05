package com.xs.java8.optional;

import com.xs.java8.lamdba.Employee;
import org.junit.Test;

import java.util.HashMap;
import java.util.Optional;

/**
 * @author xueshuai
 * @date 2021/1/26 21:32
 * @description
 */
public class OptionalTest {

    @Test
    public void test() {
        Employee e = new Employee();
        e = null;
        Optional<Employee> employee = Optional.ofNullable(e);
        //是否存在
        System.out.println(" ifPresent 判断是否存在，存在还可以搞活");
        employee.ifPresent(System.out::println);
        boolean present = employee.isPresent();
        System.out.println("是否存在：" + present);


        //如果存在，则正常输出，如果不存在则用备份的x
        Employee orElse = employee.orElse(new Employee(1, "x"));
        System.out.println(orElse);// Employee{age=1, name='x'}
        //Optional的get方法
        System.out.println(orElse.getAge());

        HashMap<Object, Object> hashMap = new HashMap<>();
//        hashMap.put("xx",123);
        System.out.println(Optional.ofNullable(hashMap.get("xx")+"").orElse("xxxxxx"));

        //如果存在，则正常输出，如果不存在异常抛出
        Employee orElseThrow = employee.orElseThrow(ExceptionInInitializerError::new);//java.lang.ExceptionInInitializerError
        System.out.println(orElseThrow);





    }
}
