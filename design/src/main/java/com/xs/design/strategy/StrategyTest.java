package com.xs.design.strategy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xueshuai
 * @date 2021/2/20 16:24
 * @description 策略模式
 */
public class StrategyTest {

    public static void main(String[] args) {
        Context context = new Context();

        //---------开始-------------  这块具体策略类，1.知道具体的策略方式 2.可以利用反射 动态代理
        StrategyInterface strategyInterface = new OneCalStrategy();
        context.setStrategy(strategyInterface);
        //--------结束-------------

        context.doCal(1);
        getBeanInstance(OneCalStrategy.class);

    }

    private static void getBeanInstance(Class target) {
        StrategyInterface o = (StrategyInterface)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{target}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });
        o.doCal(1);
    }

    //策略接口
    public interface StrategyInterface {
        void doCal(int i);
    }

    //具体策略实现类1
    static class OneCalStrategy implements StrategyInterface {

        @Override
        public void doCal(int i) {
            System.out.println("OneCalStrategy =" + i);
        }
    }

    //具体策略实现类2
    class TwoCalStrategy implements StrategyInterface {

        @Override
        public void doCal(int i) {
            System.out.println("TwoCalStrategy =" + i);
        }
    }

    //具体策略实现类3
    class ThreeCalStrategy implements StrategyInterface {

        @Override
        public void doCal(int i) {
            System.out.println("ThreeCalStrategy =" + i);
        }
    }

    //环境类，放入接口属性和接口方法
    static class Context {
        private StrategyInterface strategy;


        public StrategyInterface getStrategy() {
            return strategy;
        }

        public void setStrategy(StrategyInterface strategy) {
            this.strategy = strategy;
        }


        public void doCal(int i) {
            strategy.doCal(i);
        }

    }
    static class ProxyMain{

    }

}
