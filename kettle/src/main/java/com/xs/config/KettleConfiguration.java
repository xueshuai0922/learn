package com.xs.config;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xueshuai
 * @date 2021/8/9 15:58
 * @description kettle 配置类
 */
//@Component
//@EnableConfigurationProperties(KettleProperties.class)
public class KettleConfiguration {

    /**
     * 初始化kettle环境
     */
    @PostConstruct
    public void init() {
        try {
            KettleEnvironment.init();
        } catch (KettleException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭kettle环境
     */
    @PreDestroy
    public void destroy(){
        KettleEnvironment.shutdown();
    }

    @Bean
    public  ThreadPoolExecutor  KettlePoolExecutor(KettleProperties properties){
        //todo 阻塞队列
        BlockingQueue workQueue=new LinkedBlockingQueue<Runnable>(properties.getQueueCapacity());
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(properties.getCorePoolSize(), properties.getMaximumPoolSize(),
                properties.getKeepAliveTime(), TimeUnit.MINUTES, workQueue);
        return  threadPoolExecutor;
    }

}
