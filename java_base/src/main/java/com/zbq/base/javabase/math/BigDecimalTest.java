package com.zbq.base.javabase.math;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author zhangboqing
 * @date 2019/4/16
 */
public class BigDecimalTest {

    @Test
    public void roundingMode() {

        BigDecimal bd1 = BigDecimal.valueOf(-0.15);
        System.out.println("BigDecimal.ROUND_UP :"+bd1.divide(BigDecimal.valueOf(1),1,BigDecimal.ROUND_UP));
        System.out.println("BigDecimal.ROUND_DOWN:"+bd1.divide(BigDecimal.valueOf(1),1,BigDecimal.ROUND_DOWN));
        System.out.println("BigDecimal.ROUND_CEILING:"+bd1.divide(BigDecimal.valueOf(1),1,BigDecimal.ROUND_CEILING));
        System.out.println("BigDecimal.ROUND_FLOOR:"+bd1.divide(BigDecimal.valueOf(1),1,BigDecimal.ROUND_FLOOR));
        System.out.println("BigDecimal.ROUND_HALF_UP:"+bd1.divide(BigDecimal.valueOf(1),1,BigDecimal.ROUND_HALF_UP));
        System.out.println("BigDecimal.ROUND_HALF_DOWN:"+bd1.divide(BigDecimal.valueOf(1),1,BigDecimal.ROUND_HALF_DOWN));
        System.out.println("BigDecimal.ROUND_HALF_EVEN:"+bd1.divide(BigDecimal.valueOf(1),1,BigDecimal.ROUND_HALF_EVEN));
        System.out.println("BigDecimal.ROUND_UNNECESSARY:"+bd1.divide(BigDecimal.valueOf(1),1,BigDecimal.ROUND_UNNECESSARY));

    }
}
