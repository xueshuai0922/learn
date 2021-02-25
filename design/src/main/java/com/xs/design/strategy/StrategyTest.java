package com.xs.design.strategy;

/**
 * @author xueshuai
 * @date 2021/2/20 16:24
 * @description 策略模式
 */
public class StrategyTest {

    public static void main(String[] args) {
        Context context = new Context();

        //---------开始-------------  这块具体策略类，1.知道具体的策略方式 2.可以利用反射 动态代理
        OneCalStrategy oneCalStrategy = new OneCalStrategy();
        context.setStrategy(oneCalStrategy);
        //--------结束-------------

        context.doCal(1);

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

}
