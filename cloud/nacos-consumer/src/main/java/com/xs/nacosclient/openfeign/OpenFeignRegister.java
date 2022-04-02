package com.xs.nacosclient.openfeign;

import com.xs.nacosclient.openfeign.annotation.EnableXsOpenFeign;
import com.xs.nacosclient.openfeign.annotation.XsOpenFeign;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Set;

/**
 * @author xueshuai
 * @date 2021/5/11 9:36
 * @description openFeign的主要作用就是实现简单有效的RPC，远程调用就像本地调用一样简单
 *    1.rpc的主要关注的点：http请求，序列和反序列化
 *    2.openFeign结合SpringBoot 是如何实现的
 *      利用spring的强大的接口，对接口生成动态代理类
 *      一是在创建的时候，自动配置类中增加了相关HTTP 组件接口，默认实现Apache的http client
 *      二是在
 *
 */

public class OpenFeignRegister implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware,
        BeanFactoryAware, InvocationHandler {


    private Environment environment;
    private ResourceLoader resourceLoader;
    private BeanFactory beanFactory;



    private RestTemplate restTemplate = new RestTemplate();


    /**
     * @param metadata
     * @param registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        System.out.println("registerBeanDefinitions");
        registerFeignClients(metadata, registry);

    }

    /**
     * 获取扫描包下的注册类，代理后进行注入
     *
     * @param metadata
     * @param registry
     */
    private void registerFeignClients(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {


        //获取基础扫描包
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(EnableXsOpenFeign.class.getName());
        String basePackages = (String) annotationAttributes.get("basePackages");


        //扫描basePackages
        ClassPathScanningCandidateComponentProvider scanner = getScanner();
        scanner.setResourceLoader(resourceLoader);


        //指定只关注标注了@XsOpenFeign注解的接口
        AnnotationTypeFilter annotationTypeFilter = new AnnotationTypeFilter(XsOpenFeign.class);
        scanner.addIncludeFilter(annotationTypeFilter);
        //过滤只是XsOpenFeign的注解
        Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents(basePackages);

        for (BeanDefinition beanDefinition : candidateComponents) {
            if (beanDefinition instanceof AnnotatedBeanDefinition) {


                //扫描完毕，获得beanDefinition,进行注入bean
                registerBeans(((AnnotatedBeanDefinition) beanDefinition));
            }
        }

    }

    /**
     * 注入bean
     *
     * @param beanDefinition
     */
    private void registerBeans(AnnotatedBeanDefinition beanDefinition) {

        String beanClassName = beanDefinition.getBeanClassName();

        Object proxyBeanDefinition = createProxy(beanDefinition);

        //进行注入代理后的bean
        ((DefaultListableBeanFactory) beanFactory).registerSingleton(beanClassName, proxyBeanDefinition);


    }


    /**
     * 进行代理：服务代理
     * 1.服务发现
     * 2.服务调用
     *
     * @param beanDefinition
     * @return
     */
    private Object createProxy(AnnotatedBeanDefinition beanDefinition) {
        try {
            AnnotationMetadata annotationMetadata = ((AnnotatedBeanDefinition) beanDefinition).getMetadata();
            //远程调用服务名
            Map<String, Object> name = annotationMetadata.getAnnotationAttributes("name");
            //服务发现
//        getServers();

            String beanClassName = beanDefinition.getBeanClassName();
            //反射得到class
            Class<?> aClass = Class.forName(beanClassName);

            //当代理对象进行调用时候触发，服务调用
            Object o = Proxy.newProxyInstance(aClass.getClassLoader(), new Class[]{aClass}, this);
            return o;


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ClassPathScanningCandidateComponentProvider getScanner() {
        return new ClassPathScanningCandidateComponentProvider(false, this.environment) {
            @Override
            protected boolean isCandidateComponent(
                    AnnotatedBeanDefinition beanDefinition) {
                boolean isCandidate = false;
                if (beanDefinition.getMetadata().isInterface()) {
                    if (!beanDefinition.getMetadata().isAnnotation()) {
                        isCandidate = true;
                    }
                }
                return isCandidate;
            }
        };
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("setEnvironment");
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("setResourceLoader");
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory");
        this.beanFactory = beanFactory;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //写这个就无限套娃了
//        method.invoke(proxy, args);

        String url="http://nacos-privoder/getPort";

        //todo 服务发现 将服务名替换为请求地址和端口号
        Class<?> returnType = method.getReturnType();
        //远程调用整起来
        Object forObject = restTemplate.getForObject(url, returnType, "");
        return forObject;
    }
}
