package com.xs.innerSpring.aop;

import java.lang.annotation.*;

/**
 * @author xueshuai
 * @date 2022/4/12 10:43
 * @description  aop自定义注解
 */
@Target(ElementType.METHOD) //对方法有效
@Retention(RetentionPolicy.RUNTIME) // 运行时起作用
@Documented
public @interface Aop {
    String value() default "";
}
