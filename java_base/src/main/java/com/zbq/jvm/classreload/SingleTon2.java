package com.zbq.jvm.classreload;

/**
 * @author zhangboqing
 * @date 2017/11/17
 */
public class SingleTon2 {

    public static int count1;
    public static int count2 = 0;
    private static SingleTon2 singleTon = new SingleTon2();


    private SingleTon2() {
        count1++;
        count2++;
    }

    public static SingleTon2 getInstance() {
        return singleTon;
    }



    public static void main(String[] args) {
        SingleTon2 singleTon = SingleTon2.getInstance();
        System.out.println("count1=" + singleTon.count1);
        System.out.println("count2=" + singleTon.count2);

        System.out.println("count1=" + SingleTon2.count1);
        System.out.println("count2=" + SingleTon2.count1);
    }

}
