package com.xs.java8.stream;

import com.xs.java8.lamdba.Employee;
import org.junit.Test;

import java.util.*;
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
        Employee xues = new Employee(18, "xueshuai", sonList);
        Employee employee = new Employee(19, "xiaoming", sonList);
        Employee employee1 = new Employee(20, "xiaozhang", sonList);
        Employee employee2 = new Employee(21, "xiaohong", sonList);
        Employee employee3 = new Employee(22, "xiaotao", sonList);
        Employee employee4 = new Employee(23, "xiaoxiao", sonList);
        Employee employee5 = new Employee(24, "nicai", sonList);
        Employee employee6 = new Employee(25, "wobucai", sonList);

        list.add(xueshuai);
        list.add(xues);
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

    @Test
    public void MapTest() {
        List<Employee> employeeList = getEmployeeList();
        List<Integer> xueshuai = employeeList.stream().map(Employee::getAge).filter(m -> m>1).collect(Collectors.toList());
        List<Employee> xueshuas = employeeList.stream().filter(m -> m.getAge()>18).collect(Collectors.toList());
        String[] chars  = {"XUESHUAI","HANDSOME"};
        List<Stream<String>> collect = Arrays.stream(chars).map(m -> m.split("")).map(Arrays::stream).collect(Collectors.toList());
        List<String> collect1 = Arrays.stream(chars).map(m -> m.split("")).flatMap(Arrays::stream).collect(Collectors.toList());
        collect.forEach(s-> System.out.println(s));
        collect1.forEach(s -> System.out.println(s));
        xueshuai.forEach(s-> System.out.println(s));
        xueshuas.forEach(s-> System.out.println(s));
    }

    /**
     * Java Program to demonstrate how to use the flatMap() function in Java 8.
     * The flatMap() function is used to convert a Stream of list of values to
     * just a Stream of values. This is also called flattening of stream.
     *
     * @author Javin Paul
     */

    @Test
    public  void FlatMap() {

        List<String> teamIndia = Arrays.asList("Virat", "Dhoni", "Jadeja");
        List<String> teamAustralia = Arrays.asList("Warner", "Watson", "Smith");
        List<String> teamEngland = Arrays.asList("Alex", "Bell", "Broad");
        List<String> teamNewZeland = Arrays.asList("Kane", "Nathan", "Vettori");
        List<String> teamSouthAfrica = Arrays.asList("AB", "Amla", "Faf");
        List<String> teamWestIndies = Arrays.asList("Sammy", "Gayle", "Narine");
        List<String> teamSriLanka = Arrays.asList("Mahela", "Sanga", "Dilshan");
        List<String> teamPakistan = Arrays.asList("Misbah", "Afridi", "Shehzad");

        List<List<String>> playersInWorldCup2016 = new ArrayList<>();
        playersInWorldCup2016.add(teamIndia);
        playersInWorldCup2016.add(teamAustralia);
        playersInWorldCup2016.add(teamEngland);
        playersInWorldCup2016.add(teamNewZeland);
        playersInWorldCup2016.add(teamSouthAfrica);
        playersInWorldCup2016.add(teamWestIndies);
        playersInWorldCup2016.add(teamSriLanka);
        playersInWorldCup2016.add(teamPakistan);

        // Let's print all players before Java 8
        List<String> listOfAllPlayers = new ArrayList<>();

        for(List<String> team : playersInWorldCup2016){
            for(String name : team){
                listOfAllPlayers.add(name);
            }
        }

        System.out.println("Players playing in world cup 2016");
        System.out.println(listOfAllPlayers);

        // Now let's do this in Java 8 using FlatMap
        List<Stream<String>> collect = playersInWorldCup2016.stream()
                .map(pList -> pList.stream())
                .collect(Collectors.toList());

        System.out.println("List of all Players using Java 8 Map");
        System.out.println(collect);

        // Now let's do this in Java 8 using FlatMap
        List<String> flatMapList = playersInWorldCup2016.stream()
                .flatMap(pList -> pList.stream())
                .collect(Collectors.toList());

        System.out.println("List of all Players using Java 8 FlatMap");
        System.out.println(flatMapList);
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

    //----------------------------------【终止操作】开始 -------------------------------------------------
    //匹配 allMatch anyMatch  nonMatch 判断，返回是true 或者false
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

    //归约操作 重复循环的操作用归约来解决
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
        System.out.println("年龄最大值reduce max：" + reduce);
        Optional<Integer> max = employeeList.stream().map(Employee::getAge).max((a, b) -> -1);
        System.out.println("年龄最大值max："+max);


        Optional<String> nameReduce = employeeList.stream().map(Employee::getName).reduce((a, b) -> a + "," + b);
        System.out.println(nameReduce);
    }

//----------------------------collect-------------------------------------------------

    //变成集合 这样生成一个新的集合
    @Test
    public void test10() {
        List<Employee> employeeList = getEmployeeList();
        List<Employee> employees = employeeList.stream().filter(employee -> employee.getAge() > 20).collect(Collectors.toList());
        System.out.println("年龄大于20的");
        employees.forEach(System.out::println);
    }
    //Collectors 的sum
    @Test
    public void CollectorsSum() {
        List<Employee> employeeList = getEmployeeList();
        IntSummaryStatistics collect = employeeList.stream().collect(Collectors.summarizingInt(a -> a.getAge()));
        System.out.println(collect);//IntSummaryStatistics{count=8, sum=172, min=18, average=21.500000, max=25}
    }

    //Collectors 的groupingby
    @Test
    public void CollectorsGroupingBy() {
        List<Employee> employeeList = getEmployeeList();
        //返回一个以name来进行分组的Employee列表
        Map<String, List<Employee>> collect = employeeList.stream().collect(Collectors.groupingBy(Employee::getName));
        collect.forEach((a,list)->{
            list.forEach(employee-> System.out.println("key: "+a+"; value: "+employee));
        });

        Map<Integer, List<Employee>> ageLists = employeeList.stream().collect(Collectors.groupingBy(employee -> employee.getAge()));
        ageLists.forEach((a,list)->{
            list.forEach(employee-> System.out.println("key: "+a+"; value: "+employee));
        });

    }

    //Collectors 的joining
    @Test
    public void CollectorsJoining() {
        List<Employee> employeeList = getEmployeeList();
        String collect = employeeList.stream().map(Employee::getName).collect(Collectors.joining(", "));
        System.out.println(collect);//xueshuai, xueshuai, xiaoming, xiaozhang, xiaohong, xiaotao, xiaoxiao, nicai, wobucai
    }

    //Collectors 的reducing
    @Test
    public void CollectorsReducing() {
        List<Employee> employeeList = getEmployeeList();
        //Bad return type in lambda expression: int cannot be converted to Employee  ====>collect 返回同类型参数
        //employeeList.stream().collect(Collectors.reducing((a,b)->a.getAge()+b.getAge()));
        Optional<Integer> collect = employeeList.stream().map(Employee::getAge).collect(Collectors.reducing((a, b) -> a + b));
        System.out.println(collect.get());

        Integer age = employeeList.stream().collect(Collectors.reducing(0, Employee::getAge, (a, b) -> a + b));
        System.out.println(age);
    }




//----------------------------终止操作结束 -------------------------------------------------


//数值流：就是特殊的数值流 IntegerStream DoubleStream

    @Test
    public void integerStream() {
        List<Employee> employeeList = getEmployeeList();
        IntStream intStream = employeeList.stream().mapToInt(employee -> employee.getAge());
        int sum = intStream.sum();
        System.out.println(sum);
    }

    @Test
    public void integerStreamBoxed() {
        List<Employee> employeeList = getEmployeeList();
        IntStream intStream = employeeList.stream().mapToInt(employee -> employee.getAge());//类似Integer到int
        Stream<Integer> boxed = intStream.boxed(); //装箱  boxed  int 到Integer

    }

    @Test
    public void ranged() {
        Stream<double[]> stream = IntStream.rangeClosed(1, 100).boxed().flatMap(a ->
                IntStream.rangeClosed(a, 100).mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        .filter(t -> t[2] % 1 == 0)
        );
        stream.forEach(doubles -> {
            Arrays.stream(doubles).forEach(a-> System.out.print(a+" "));
            System.out.println();
        });

    }


    @Test
    public void StreamIterate() {
        Stream.iterate(0,a->a+2).limit(10).forEach(System.out::println);

    }







}
