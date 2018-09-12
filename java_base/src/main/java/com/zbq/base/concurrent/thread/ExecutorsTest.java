package com.zbq.base.concurrent.thread;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author zhangboqing
 * @date 2018/7/29
 */
public class ExecutorsTest {


    @Test
    public void run() {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Object> submit = executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName());
            throw new NullPointerException();
        });
        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Future<Object> submit2 = executorService.submit(() -> {
            System.out.println( Thread.currentThread().getName());
            return 0;
        });

        try {
            System.out.println(submit2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void run2() {
        System.out.println(~29);
    }
}
