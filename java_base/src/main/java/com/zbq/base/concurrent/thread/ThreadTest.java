package com.zbq.base.concurrent.thread;

/**
 * @author zhangboqing
 * @date 2018/4/22
 */
public class ThreadTest {

    public static class SimpleRunnble implements Runnable {
        int i;

        public SimpleRunnble(int i) {
            this.i = i;
        }
        @Override
        public void run() {
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new SimpleRunnble(1));
        Thread thread2 = new Thread(new SimpleRunnble(2));
        Thread thread3 = new Thread(new SimpleRunnble(3));

        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
        thread3.join();
        System.out.println("end");
    }

}
