package com.xs.mybatis.interceptors;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author xueshuai
 * @date 2021/4/12 20:41
 * @description
 */
@Intercepts({@Signature(
        type = Executor.class,
        method = "query",
        args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class })
})

public class SqlTimeInterceptor implements Interceptor {


    //
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        System.out.println();
        //调用
        Object proceed = invocation.proceed();
        long end = System.currentTimeMillis();
        System.out.println("执行时间： "+(end-startTime)+"ms");

        return proceed;
    }


    //target 就是四个接口Executors,ResultSetHandler,ParameterHandler、StatementHandler中的一个，返回的为代理对象
    //然后根据注解来获取到底，我们要代理哪个接口
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
