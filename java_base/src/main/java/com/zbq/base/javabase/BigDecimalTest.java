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


    @Test
    public void run2() {
        String s = new String("1233");
        byte[] bytes = s.getBytes();
        System.out.println(byte2hex(bytes));
    }

    @Test
    public void run3() {
        System.out.println(BigDecimal.valueOf(100.99).stripTrailingZeros().toPlainString());
    }


    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    public static void main(String[] args) {
        /*Integer a = 16;
        byte b = a.byteValue();
        System.out.println( 0xFF);
        String hex = Integer.toHexString(a);
        System.out.println(hex);*/

//        Double aDouble = new Double(1.1);
//        System.out.println(aDouble.longValue());
        double a = 1.0;
        long b = (long) a;

        System.out.println(a);
        System.out.println(b);

    }



}
