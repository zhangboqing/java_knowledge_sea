package com.zbq.base.concurrent;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangboqing
 * @date 2017/12/29
 * <p>
 * 测试synchronized 方法
 */
public class SynchronizedTest {

    public static CountDownLatch countDownLatch = null;


    //
    public static synchronized void printA() throws InterruptedException {
        System.out.println("当前类class: " + SynchronizedTest.class);
        Thread thread = Thread.currentThread();
        String name = thread.getName();
        long id = thread.getId();
        System.out.println("当前线程thread name: " + name);
        System.out.println("当前线程thread id: " + id);


        if (name.equals("thread1")) {
            Thread.sleep(1000 * 2);
        }
        System.out.println("线程名称："+name+";printA1");

        printB(name);

        if (name.equals("thread1")) {
            Thread.sleep(1000 * 2);
        }
        System.out.println("线程名称："+name+";printA2");
        if (name.equals("thread1")) {
            Thread.sleep(1000 * 2);
        }
        System.out.println("线程名称："+name+";printA3");
        if (name.equals("thread1")) {
            Thread.sleep(1000 * 2);
        }
        System.out.println("线程名称："+name+";printA4");
        if (name.equals("thread1")) {
            Thread.sleep(1000 * 2);
        }
        System.out.println("线程名称："+name+";printA5");
    }


    public static void printB(String name) throws InterruptedException {

        System.out.println("线程名称："+name+";printB1");
        System.out.println("线程名称："+name+";printB2");
        Thread.sleep(1000 * 3);
        System.out.println("线程名称："+name+";printB3");
        Thread.sleep(1000 * 3);
        System.out.println("线程名称："+name+";printB4");
        System.out.println("线程名称："+name+";printB5");
    }


    @Test
    public void testRun1() throws InterruptedException {
        printA();
    }


    @Test
    public void testRun2() throws InterruptedException {
        printA();
    }


    public static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    @Test
    public void ParallelRun1() throws InterruptedException {

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread.sleep(100000);

    }

    @Test
    public void ParallelRun2() throws InterruptedException {

        countDownLatch = new CountDownLatch(2);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    printA();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    printA();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.setName("thread1");
        thread2.setName("thread2");

        thread1.start();
        thread2.start();

        countDownLatch.await();
    }

}
