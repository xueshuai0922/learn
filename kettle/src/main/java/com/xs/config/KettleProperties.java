package com.xs.config;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author xueshuai
 * @date 2021/8/9 15:59
 * @description kettle调用的线程池配置 ：线程名称(方便排查)，核心线程数，阻塞队列，最大线程数，线程释放时间（多长时间空闲归还给系统）
 *                                     拒绝策略、
 */
//@ConfigurationProperties(prefix = "kettle.pool")
@Component
public class KettleProperties implements Serializable {
    /**
     *核心线程数
     */
    private int corePoolSize=8;
    /**
     * 最大线程数
     */
    private int maximumPoolSize=50;
    /**
     * 空闲时间
     */
    private int keepAliveTime=4;
    /**
     * 队列容量
     */
    private int queueCapacity=100;

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public int getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(int keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public int getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }
}
