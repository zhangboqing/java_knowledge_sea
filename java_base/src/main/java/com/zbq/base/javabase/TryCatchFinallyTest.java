package com.zbq.base.javabase;

import org.junit.Test;

/**
 * @author zhangboqing
 * @date 2018/1/12
 */
public class TryCatchFinallyTest {

    @Test
    public void run() {


        try {
            System.out.println("1");

            throw  new  RuntimeException("111");
        } finally {
            System.out.println("finally");
        }


    }
}
