package com.xs.innerSpring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author xueshuai
 * @date 2022/4/12 10:28
 * @description aop
 */

@Aspect //切面
@Component //注意，需要注入到spring ioc容器中去
public class SpringAop {

    @Pointcut(value = "@annotation(com.xs.innerSpring.aop.Aop)") //切点
    private  void pointcut(){}


    @Before(value = "pointcut()")
    public void beforeDo(){
        System.out.println("方法之前调用！！！");
    }

    @After(value = "pointcut()")
    public  void afterDo(JoinPoint point){
        Class<?> aClass = point.getTarget().getClass();
        try {
            Class<?> aClass1 = Class.forName(aClass.getName());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("无论成功失败，方法之后调用！！！");
    }

    @AfterReturning(value = "pointcut()")
    public void afterReturn(){
        System.out.println("成功，return 之后调用！！！");
    }


    @AfterThrowing(value = "pointcut()")
    public  void afterThrow(){
        System.out.println("有异常抛出，throw 之后调用！！！");
    }

    @Around(value ="pointcut()" )
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        //获取请求相关信息
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();


        MethodSignature signature = (MethodSignature)proceedingJoinPoint.getSignature();

        String methodName = signature.getName();
        String[] parameterNames = signature.getParameterNames();
        Object[] args = proceedingJoinPoint.getArgs();
        HashMap<String, Object> paramMap = new HashMap<>();
        for (int i = 0; i < parameterNames.length; i++) {
            paramMap.put(parameterNames[i],args[i].toString());

        }
        Class targetClass = signature.getDeclaringType();



        System.out.println("调用前 【class】 "+targetClass.getName() +"【method】"+methodName+"【入参args】"+paramMap);

        //【重点】 proceedingsJoinPoint比JoinPoint多个proceed方法，返回方法的结果值
        Object proceed = proceedingJoinPoint.proceed();

        System.out.println("调用后 【class】 "+targetClass.getName() +"【method】"+methodName+"【返回】"+proceed.toString());

        return proceed;
    }

}
