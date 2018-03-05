package com.zbq.base.concurrent.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhangboqing
 * @date 2018/3/2
 *
 * Synchronized 测试
 */
public class SynchronizedLockTest {

    public static CountDownLatch countDownLatch = null;


    public static void run1() {

        System.out.println(Thread.currentThread().getName()+"================>");
        synchronized (SynchronizedLockTest.class) {

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "================>内部");

        }

    }


    public static void main(String[] args) throws InterruptedException {

        countDownLatch = new CountDownLatch(2);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                run1();
                countDownLatch.countDown();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                run1();
                countDownLatch.countDown();
            }
        });

        thread1.setName("thread1");
        thread2.setName("thread2");

        thread1.start();
        thread2.start();

        countDownLatch.await();


    }

}
