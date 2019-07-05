package com.zbq.base.javabase;

import org.junit.Test;

/**
 * @author zhangboqing
 * @date 2018/10/31
 */
public class IntegerTest {
    private long a;

    @Test
    public void run() {

        int a = 1<<30;
        System.out.println(a);

    }

    @Test
    public void run2() {
        a += 1;
        System.out.println(a);

    }

    public static void main(String[] args) {
        IntegerTest integerTest = new IntegerTest();
        integerTest.run2();
    }
}
