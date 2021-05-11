package com.xs.nacosclient.openfeign.annotation;

import com.xs.nacosclient.openfeign.OpenFeignRegister;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author xueshuai
 * @date 2021/5/11 10:28
 * @description 启动 XsOpenFeign注解
 */

@Target(ElementType.TYPE) //适用于哪些java元素 type：类，接口； filed：字段属性；method：方法；
@Retention(RetentionPolicy.RUNTIME)//适用于java哪个阶段  source：源文件；class，runtime
@Documented
@Inherited  //子类继承该注解
@Import(OpenFeignRegister.class)
public @interface EnableXsOpenFeign {

    String basePackages();
}
