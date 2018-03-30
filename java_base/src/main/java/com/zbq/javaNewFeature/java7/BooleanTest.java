package com.zbq.javaNewFeature.java7;

import org.junit.Test;

/**
 * @author zhangboqing
 * @date 2018/3/29
 */
public class BooleanTest {



    @Test
    public void run() {

        Boolean bl1 = true;
        Boolean bl2 = false;

        System.out.println(Boolean.logicalAnd(bl1,bl2));

        Long one_million = 1_000_000L;
        System.out.println(one_million);

        int binary = 0b1001_1001;

        System.out.println(binary);
    }
}
