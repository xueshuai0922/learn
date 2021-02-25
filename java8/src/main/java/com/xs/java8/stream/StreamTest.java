package com.xs.java8.stream;

import com.xs.java8.lamdba.Employee;
import org.junit.Test;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author xueshuai
 * @date 2021/1/25 21:18
 * @description
 */
public class StreamTest {

    public List<Employee> getEmployeeList() {

        ArrayList<Employee> list = new ArrayList<>();

        ArrayList<Object> sonList = new ArrayList<>();
        sonList.add("xiaoxiaoxue");
        sonList.add("xiaoxue");
        Employee xueshuai = new Employee(18, "xueshuai", sonList);
        Employee employee = new Employee(19, "xiaoming", sonList);
        Employee employee1 = new Employee(20, "xiaozhang", sonList);
        Employee employee2 = new Employee(21, "xiaohong", sonList);
        Employee employee3 = new Employee(22, "xiaotao", sonList);
        Employee employee4 = new Employee(23, "xiaoxiao", sonList);
        Employee employee5 = new Employee(24, "nicai", sonList);
        Employee employee6 = new Employee(25, "wobucai", sonList);

        list.add(xueshuai);
        list.add(employee);
        list.add(employee1);
        list.add(employee2);
        list.add(employee3);
        list.add(employee4);
        list.add(employee5);
        list.add(employee6);


        return list;
    }


    @Test
    public void test1() {
        List<String> list = Arrays.asList("12", "12", "34");
        Stream<String> stream = list.stream();
    }

    @Test
    public void test2() {
        int[] ints = {1, 2, 3};
        IntStream stream = Arrays.stream(ints);

    }

    @Test
    public void test3() {
        Stream<String> stringStream = Stream.of("m", "f", "f");


    }

//    ------------------------测试中间操作-----------------------

    //测试 筛选filter 切片：limit skip  去重：distinct
    @Test
    public void test4() {
        List<Employee> list = getEmployeeList();
        System.out.println("filter");
        list.stream().filter(e -> e.getAge() > 20).forEach(System.out::println);
        System.out.println();


        list.stream().filter(e -> e.getName().length() > 5).forEach(System.out::println);
        System.out.println();

        System.out.println("前4个");
        //前4个
        list.stream().limit(4).forEach(System.out::println);

        System.out.println();

        System.out.println("跳过前四个");
        //跳过前四个
        list.stream().skip(4).forEach(System.out::println);

        System.out.println("去重");

        list.add(new Employee(18, "xueshuai"));
        list.add(new Employee(18, "xueshuai"));
        list.add(new Employee(18, "xueshuai"));
        list.add(new Employee(18, "xueshuai"));
        //根据hashcode,equal来进行判断
        list.stream().distinct().forEach(System.out::println);


    }

    //map(function)  flatMap(function<t,r extends Stream>)
    @Test
    public void test5() {
        List<Employee> employeeList = getEmployeeList();
        Optional<Integer> first = employeeList.stream().map(Employee::getAge).findFirst();
        System.out.println(first);


        System.out.println();

        Optional<Integer> flatMap = employeeList.stream().flatMap(employee -> {
            Stream stream = employee.getSons().stream().filter(son -> son.equals("xiaoxue"));
            return stream;
        }).findFirst();
        System.out.println(flatMap);
        employeeList.stream().map(Employee::getAge).filter(age -> age > 20).forEach(System.out::println);

    }
    //sorted默认升序   sorted(自定义Compartor)

    @Test
    public void test6() {
        List<Integer> strings = Arrays.asList(555, 45, 67);
        Stream<Integer> sorted = strings.stream().sorted();
        sorted.forEach(System.out::println);
        //自定义compare方式
        strings.stream().sorted((v, b) -> b - v).forEach(System.out::println);
    }

    //----------------------------终止操作----------------------------------
    //匹配 allMatch anyMatch  nonMatch
    @Test
    public void test7() {
        List<Employee> employeeList = getEmployeeList();
        boolean b = employeeList.stream().allMatch(employee -> employee.getAge() == 18);
        System.out.println("是否所有都是18岁: " + b);

        boolean anyMatch = employeeList.stream().anyMatch(employee -> employee.getName().equals("xueshuai"));
        System.out.println("是否有一个名字是xueshuai: " + anyMatch);

        boolean noneMatch = employeeList.stream().noneMatch(employee -> employee.getAge() < 10);
        System.out.println("不存在年龄小10岁的： " + noneMatch);
    }

    //返回第一个 findFirst findAny  min  max
    @Test
    public void test8() {
        List<Employee> employeeList = getEmployeeList();
        Optional<Employee> first = employeeList.stream().sorted(Comparator.comparingInt(Employee::getAge)).findFirst();
        System.out.println("年龄最小的是：" + first);

        Optional<Employee> min = employeeList.stream().min(Comparator.comparingInt(Employee::getAge));
        System.out.println("年龄最小的是： " + min);

        Optional<Employee> max = employeeList.stream().max(Comparator.comparingInt(Employee::getAge));
        System.out.println("年龄最大的是： " + max);


        Optional<Employee> any = employeeList.stream().findAny();
        System.out.println("随机出现的 " + any);

    }

    //规约 reduce

    @Test
    public void test9() {
        List<Employee> employeeList = getEmployeeList();
        Integer integer = employeeList.stream().map(Employee::getAge).reduce(0, (a, b) -> a + b);
        System.out.println("所有人年龄之和为 " + integer);
        Optional<Integer> reduce = employeeList.stream().map(Employee::getAge).reduce((a, b) -> {
            if (a < b) {
                a = b;
            }
            return a;
        });
        System.out.println("年龄最大值：" + reduce);
    }

    //变成集合 这样生成一个新的集合
    @Test
    public void test10() {
        List<Employee> employeeList = getEmployeeList();
        List<Employee> employees = employeeList.stream().filter(employee -> employee.getAge() > 20).collect(Collectors.toList());
        System.out.println("年龄大于20的");
        employees.forEach(System.out::println);
    }


}
