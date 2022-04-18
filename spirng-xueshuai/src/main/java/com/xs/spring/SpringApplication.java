package com.xs.spring;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xueshuai
 * @date 2022/4/17 20:49
 * @description
 */
public class SpringApplication {


    private ConcurrentHashMap<String,BeanDefinition> BeanDefinitionMap = new ConcurrentHashMap();



    //单例池
    private  ConcurrentHashMap<String ,Object> singletonBeanMap = new ConcurrentHashMap<>();



    //
    private ArrayList<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    public SpringApplication(Class clazz) {

        //1.【扫描】----根据注解下的扫描，扫描到需要注入的bean，存放到BeanDefinitionMap中

        if (clazz.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan componentScan = (ComponentScan) clazz.getAnnotation(ComponentScan.class);
            String path = componentScan.value();//com.xs
            path=path.replace(".","/");
            URL resource = clazz.getClassLoader().getResource(path);
            File file = new File(resource.getFile());//父文件夹

            if(file.isDirectory()){
                File[] files = file.listFiles();//子文件
                for (File f : files) {
                    String className = f.getAbsolutePath();
                    if (className.endsWith(".class")) {
                        className= className.substring(className.indexOf("com"),className.indexOf(".class"));
                        className=className.replace("\\",".");
                        try {
                            Class<?> beanClazz  = Class.forName(className);
                            if (beanClazz.isAnnotationPresent(Component.class)) {
                                //todo 这里将BeanPostProcessor的相关的接口类Bean 放在list中

                                if (BeanPostProcessor.class.isAssignableFrom(beanClazz)) {
                                    BeanPostProcessor beanPostProcessor = (BeanPostProcessor) beanClazz.newInstance();
                                    beanPostProcessorList.add(beanPostProcessor);
                                }



                                String beanName = beanClazz.getAnnotation(Component.class).value();
                                if("".equals(beanName)){
                                    String simpleName = beanClazz.getSimpleName();
                                    beanName = Introspector.decapitalize(simpleName);
                                }


                                //todo 生成BeanDefinition 正常逻辑，我们应该就这里进行实例化bean，但是比如我们创建的bean是多例的，
                                // 在我们需要的时候，再去创建多例的bean的时候，单纯的在这里进行创建是不合适的，
                                // 所以给出的是BeanDefinition
                                BeanDefinition beanDefinition = new BeanDefinition();
                                if (beanClazz.isAnnotationPresent(Scope.class)) {
                                    String scope = beanClazz.getAnnotation(Scope.class).value();

                                    beanDefinition.setType(scope);
                                }else {
                                    beanDefinition.setType("singleton");
                                }
                                beanDefinition.setClazz(beanClazz);
                                BeanDefinitionMap.put(beanName,beanDefinition);
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        //2.【单例bean实例化】从BeanDefinitionMap中循环beanName进行创建单例bean

        for (String beanName : BeanDefinitionMap.keySet()) {
            this.getBean(beanName);
        }

    }

    private Object createBean(String beanName, BeanDefinition beanDefinition){
        Class clazz = beanDefinition.getClazz();
        try {
            //todo 为了简化,这里默认使用无参构造器
            Object bean = clazz.getConstructor().newInstance();


            //3.【依赖注入】
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields) {
                if (field.isAnnotationPresent(Autowire.class)) {
                    String fieldName = field.getName();
                    Object fieldBean = getBean(fieldName);
                    field.setAccessible(true);
                    try {
                        field.set(bean,fieldBean);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            //【aware】---创建bean的时候，如果有接口，那么进行接口的方法调用  Aware 感知spring相关的信息，
            // 来给实现对应Aware接口的bean调用使用
            if(bean instanceof  BeanNameAware){
                ((BeanNameAware)bean).setBeanName(beanName);
            }



            //【初始化前-BeanPostProcessor】
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                 bean = beanPostProcessor.postProcessBeforeInitialization(beanName, bean);
            }


            //【初始化方法】
            if(bean instanceof  InitializingBean){
                ((InitializingBean)bean).afterPropertiesSet();
            }

            //【初始化后-BeanPostProcessor   aop】
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                bean= beanPostProcessor.postProcessAfterInitialization(beanName,bean);
            }


            return  bean;

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return  null;
    }

    /**
     * 根据beanName 获取bean
     * @param beanName
     * @return
     */
    public Object getBean(String beanName){
        BeanDefinition beanDefinition = BeanDefinitionMap.get(beanName);
        if(beanDefinition==null){
            throw  new RuntimeException("找不到BeanName: "+beanName);
        }

        String type = beanDefinition.getType();
        //如果是单例bean,从单例池中先拿，存在返回，不存在则创建，再返回
        if("singleton".equals(type) ){
            Object o = singletonBeanMap.get(beanName);
            if(o==null){
                Object bean = createBean(beanName, beanDefinition);
                singletonBeanMap.put(beanName, bean);
                return bean;
            }
            return o;
        }else {
            return createBean(beanName, beanDefinition);
        }
    }
}
