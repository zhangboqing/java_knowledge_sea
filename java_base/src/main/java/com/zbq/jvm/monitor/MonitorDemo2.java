package com.zbq.jvm.monitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by zhangboqing on 2017/7/26.
 * 演示线程的死循环和线程等待
 */
public class MonitorDemo2 {

    /*** 线程死循环演示*/
    public static void createBusyThread() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    ;
            }
        }, "testBusyThread");

        thread.start();
    }

    /*** 线程锁等待演示*/
    public static void createLockThread(final Object lock) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "testLockThread");

        thread.start();
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        br.readLine();
        Object obj = new Object();
        createLockThread(obj);
    }
}
