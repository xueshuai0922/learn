package com.xs.nacosclient.openfeign.annotation;

import java.lang.annotation.*;

/**
 * @author xueshuai
 * @date 2021/5/11 9:28
 * @description 自定义注解
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface XsOpenFeign {
    String name() default "";
}
