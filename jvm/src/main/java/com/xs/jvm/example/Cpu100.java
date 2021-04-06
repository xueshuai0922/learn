package com.xs.jvm.example;

import org.springframework.boot.autoconfigure.jms.JmsProperties;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xueshuai
 * @date 2021/3/26 10:28
 * @description  错误使用线程池，导致频繁GC，因为线程数多了，走阻塞队列，但是这个阻塞队列是个int的最大值，就
 *               一直网阻塞队列中存放值，导致出现大对象
 *
 *               解决办法：监听模式，不要进行死循环
 */
public class Cpu100 {

    /**
     * 从数据库中读取信用数据，套用模型，并把结果进行记录和传输
     */


    private static class CardInfo {
        BigDecimal price = new BigDecimal(0.0);
        String name = "张三";
        int age = 5;
        Date birthdate = new Date();

        public void m() {
        }
    }

    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50,
            new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) throws Exception {
        executor.setMaximumPoolSize(50);

        for (; ; ) {
            modelFit();
            Thread.sleep(100);
        }
    }

    private static void modelFit() {
        List<CardInfo> taskList = getAllCardInfo();
        // do something
        for (CardInfo info : taskList) {
            ScheduledFuture<?> scheduledFuture = executor.scheduleWithFixedDelay(() -> {
                //do sth with info
                info.m();


            }, 2, 1, TimeUnit.SECONDS);
        }
    }

    private static List<CardInfo> getAllCardInfo() {
        List<CardInfo> taskList = new ArrayList<>();
        CardInfo ci=null;
        for (int i = 0; i < 100; i++) {
             ci = new CardInfo();
            taskList.add(ci);
        }


        return taskList;
    }
}


