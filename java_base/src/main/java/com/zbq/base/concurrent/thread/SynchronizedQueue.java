package com.zbq.base.concurrent.thread;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangboqing
 * @date 2018/4/22
 */
public class SynchronizedQueue {

    private final Object DLOCK = new Object();
    private final Object ADDLOCK = new Object();
    private final Object GETLOCK = new Object();

    private LinkedList<Integer> queue;
    private int capacity;
    private int count;

    private int getSignal = 0;
    private int addSignal = 0;


    public SynchronizedQueue(int capacity) {
        if (capacity <= 0) {
            throw new RuntimeException("param capacity must greater than zero ");
        }

        this.capacity = capacity;
        this.count = 0;
        this.queue = new LinkedList();
    }

    public synchronized void add(int i) throws InterruptedException {
        synchronized (DLOCK) {
            while (count == capacity) {
//                addSignal++;
                DLOCK.wait();
            }

            queue.addLast(i);
            count++;
//            if (count > 0 && getSignal > 0) {
//                getSignal--;
                DLOCK.notifyAll();
//            }
        }
    }

    public int get() throws InterruptedException {

        synchronized (DLOCK) {
            while (count == 0) {
//                getSignal++;
                DLOCK.wait();
            }
            Integer value = queue.removeFirst();
            count--;

//            if (count < capacity && addSignal > 0) {
//                addSignal--;
            DLOCK.notify();
//            }

            return value;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
        SynchronizedQueue synchronizedQueue = new SynchronizedQueue(4);
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.submit(new MyRunnable(i,synchronizedQueue));

            fixedThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(synchronizedQueue.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        Thread.sleep(2000);

    }


    public static class MyRunnable implements Runnable {

        SynchronizedQueue queue;
        int i;

        public MyRunnable(int i, SynchronizedQueue queue) {
            this.queue = queue;
            this.i = i;
        }

        @Override
        public void run() {
            try {
                queue.add(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
