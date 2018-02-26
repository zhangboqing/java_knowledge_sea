package com.zbq.base.javabase;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author zhangboqing
 * @date 2018/1/11
 */
public class BigDecimalTest {

    @Test
    public void run1() {
        BigDecimal b1 = new BigDecimal(10);
        BigDecimal b2 = new BigDecimal(20);

        System.out.println(b1.compareTo(b2) > 0);
    }
}
