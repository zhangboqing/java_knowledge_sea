package com.zbq.base.javabase;

import org.junit.Test;

/**
 * @author zhangboqing
 * @date 2019-06-19
 */
public class BaseClassTest {

    public static void main(String[] args) {
        Integer var1 = new Integer(100);
        byte var2 = 100;
        System.out.println(var1 == var2);
    }

    @Test
    public  void run2() {
        Integer var1 = new Integer(100);
        Integer var2 = 100;
        System.out.println(var1 == var2);
    }

}
