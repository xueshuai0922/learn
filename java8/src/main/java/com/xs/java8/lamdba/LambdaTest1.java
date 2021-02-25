package com.xs.java8.lamdba;

import com.sun.media.sound.SF2GlobalRegion;
import com.xs.java8.funcationInterface.FunctionInterfaceTest;
import org.junit.Test;
import org.springframework.aop.scope.ScopedProxyUtils;

import java.io.PrintStream;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.*;

/**
 * @author xueshuai
 * @date 2021/1/24 16:43
 * @description 测试java内置基本4种函数式接口(参数一般都是泛型)
 * 1.消费型  Consumer ===>void
 * 2.生产线  Supplier
 * 3.判断型  Predicate ===>Boolean test();
 * 4.函数处理型 Function
 */
public class LambdaTest1 {


    //------------------------基础实例--------------------------
    @Test
    public void test2() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类");
            }
        };
        runnable.run();

        Runnable runnable1 = () -> System.out.println("使用lambda方式");
        runnable1.run();


        Runnable runnable2 = System.out::println;
        runnable2.run();
    }


    //-----------------------lambda表达式---------------------------
    //有参数，无返回值
    @Test
    public void test3() {
        Consumer<String> objectConsumer = new Consumer<String>() {
            @Override
            public void accept(String o) {
                System.out.println(o);
            }
        };
        objectConsumer.accept("3333");
        System.out.println("===========");

        Consumer<String> objectConsumer1 = (s) -> System.out.println(s);
        objectConsumer1.accept("lambda --- 22222");

    }


    //无参数，有返回值
    @Test
    public void test4() {
        Supplier<String> stringSupplier = new Supplier<String>() {
            @Override
            public String get() {
                return "这是个供给型函数式接口";
            }
        };
        System.out.println(stringSupplier.get());
        System.out.println("=========");
        Supplier<String> stringSupplier1 = () -> "这是个供给型函数式接口";
        System.out.println(stringSupplier1.get());

    }

    //有参数，有返回值
    @Test
    public void test5() {
        Function<String, String> function = new Function<String, String>() {
            @Override
            public String apply(String o) {
                return "函数有入有出，入参：" + o;
            }
        };
        System.out.println(function.apply("哒哒哒大"));


        Function<String, String> function1 = (o) -> "函数有入有出，入参：" + o;
        System.out.println(function1.apply("sfsfsfsf"));


    }

    //有参数，进行判断
    @Test
    public void test6() {
        Predicate<String> stringPredicate = new Predicate<String>() {
            @Override
            public boolean test(String o) {
                return o.contains("ni");
            }
        };
        System.out.println(stringPredicate.test("nihao"));//true

        Predicate<String> stringPredicate1 = (s) -> s.contains("ni");
        System.out.println(stringPredicate1.test("nihao"));//true

    }

    //    ------------------函数调用----------------------------------
    //    lambda中有现有实现方法


    //对象::非静态方法
    @Test
    public void test() {
        Consumer<String> consumer = (s) -> System.out.println(s);
        consumer.accept("接受的值");


        Consumer<String> consumer1 = System.out::println;
        consumer1.accept("接受的值1111");

    }

    @Test
    public void test7() {
        Employee employee = new Employee(18, "xueshuai");
        // Supplier中的get    入参：无；出参：String
        //employee.getName()  入参：无；出参：String
        Supplier<String> supplier = () -> employee.getName();
        System.out.println(supplier.get());//xueshuai


        Supplier<Integer> supplier1 = employee::getAge;
        System.out.println(supplier1.get());//18

    }

    //类::静态方法
    @Test
    public void test8() {
        //Integer.compare是个静态方法
        Comparator<Integer> comparator = (a, b) -> Integer.compare(a, b);
        int compare = comparator.compare(2, 3);
        System.out.println(compare);//-1


        Comparator<Integer> comparator1 = Integer::compare;
        int compare1 = comparator1.compare(5, 3);
        System.out.println(compare1);//1


        FunctionInterfaceTest<Integer> function = Integer::compareTo;
        System.out.println(function.compare(2, 3));//-1


    }

    @Test
    public void test9() {
        //Function<入参, 出参> apply
        Function<Double, Long> function = Math::round;
        System.out.println(function.apply(5.5));//6
    }


    //类::非静态方法
    @Test
    public void test10() {

        Comparator<String> compare1 = (a, b) -> a.compareTo(b);
        int res = compare1.compare("abc", "acb");
        System.out.println(res);//-1


        Comparator<String> compare = String::compareTo;
        int re = compare.compare("abc", "acb");
        System.out.println(re);//-1
    }
    //----------------------构造器引用----------------------
    // 函数式接口的抽象方法 出入参 === new Employee();
    //

    @Test
    public void test11() {
        Supplier<Employee> supplier = () -> new Employee();
        Employee employee = supplier.get();


        Supplier<Employee> supplier1 = Employee::new;
        Employee employee1 = supplier1.get();


        Function<Integer, Employee> function = Employee::new;
        Employee employee2 = function.apply(15);
        System.out.println(employee2);


        BiFunction<Integer, String, Employee> biFunction = Employee::new;
        Employee xueshuai = biFunction.apply(12, "xueshuai");
        System.out.println(xueshuai);
    }

    //    --------------------数组引用-----------------
    @Test
    public void test12() {

    }

}
