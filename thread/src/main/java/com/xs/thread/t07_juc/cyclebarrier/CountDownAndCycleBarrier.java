package com.xs.thread.t07_juc.cyclebarrier;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author xueshuai
 * @date 2022/3/9 22:49
 * @description
 */
public class CountDownAndCycleBarrier implements Runnable{
    /**
     * 创建 4 个屏障，处理完之后执行当前类的 run 方法
     */
    private CyclicBarrier c = new CyclicBarrier(4, this);


    private CountDownLatch countDownLatch = new CountDownLatch(4);


    /**
     * 假设只有 4 个 sheet，所以只启动 4 个线程
     */
    private Executor executor = Executors.newFixedThreadPool(4);
    /**
     * 保存每个 sheet 计算出的银流结果
     */
    private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new
            ConcurrentHashMap<String, Integer>();

    private void count() {
        for (int i = 0; i < 4; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    // 计算当前 sheet 的银流数据，计算代码省略
                    sheetBankWaterCount
                            .put(Thread.currentThread().getName(), 1);
                    // 银流计算完成，插入一个屏障
                    try {
                        c.await();
//                        countDownLatch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        int result = 0;
        // 汇总每个 sheet 计算出的结果
        for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
            result += sheet.getValue();
        }
        // 将结果输出 sheetBankWaterCount.put("result", result);
        System.out.println(result);
    }

    @Override
    public void run() {
        int result = 0;
        // 汇总每个 sheet 计算出的结果
        for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
            result += sheet.getValue();
        }
        // 将结果输出 sheetBankWaterCount.put("result", result);
        System.out.println(result);
    }

    public static void main(String[] args) {
        CountDownAndCycleBarrier bankWaterCount = new CountDownAndCycleBarrier();
        bankWaterCount.count();
    }
}

