package com.zbq.jvm.monitor;

/**
 * Created by zhangboqing on 2017/7/27.
 * 线程死锁
 * <p>
 * 造成死锁的原因是Integer.valueOf()方法基于减少对象创建次数和节省内存的考虑，
 * [-128，127]之间的数字会被缓存，当valueOf()方法传入参数在这个范围之内，将直接返回缓存中的对象
 *
 * 出现线程死锁之后，点击JConsole线程面板的“检测到死锁”按钮，将出现一个新的“死锁”页签
 */
public class MonitorDemo3 {

    /**
     * 线程死锁等待演示
     */
    static class SynAddRunalbe implements Runnable {
        int a, b;

        public SynAddRunalbe(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)) {
                synchronized (Integer.valueOf(b)) {
                    System.out.println(a + b);

                }
            }
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(new SynAddRunalbe(1, 2)).start();
            new Thread(new SynAddRunalbe(2, 1)).start();
        }
    }
}
