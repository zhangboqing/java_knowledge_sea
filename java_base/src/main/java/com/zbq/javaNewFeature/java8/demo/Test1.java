package com.zbq.javaNewFeature.java8.demo;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * @author zhangboqing
 * @date 2018/3/29
 */
public class Test1 {

    @Test
    public void run() {


        Comparator<Integer> c = (Integer a,Integer b) ->  a > b ? 1 : -1;

        boolean equals = c.equals(new Object());
        int compare = c.compare(2, 1);
        System.out.println(equals);
        System.out.println(compare);

    }

    @Test
    public void run2() {
        LocalDateTime localDateTime = LocalDate.now().atStartOfDay();
        System.out.println(localDateTime.toString());
    }
}
