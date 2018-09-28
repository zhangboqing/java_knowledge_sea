package com.zbq.base.jodatime;

import java.util.Date;

/**
 * @author zhangboqing
 * @date 2018/9/28
 */
public class DateTest {

    public static void main(String[] args) {
        Date expiredDate = DateUtils.parse("2018-10-10", DateUtils.FORTER_DATE);
        System.out.println(expiredDate);
        Date nowDate = new Date();
        Long days = (expiredDate.getTime() - nowDate.getTime()) / (1000 * 60 * 60 * 24) +1;

        System.out.println(days);
    }
}
