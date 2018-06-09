package com.zbq.jvm.optimize;

import org.junit.Test;

/**
 * @author zhangboqing
 * @date 2018/6/4
 */
public class CacheOptimize {


    @Test
    public void integerCacheTest() {

        Integer i0 = 128 ; //调用Integer的valueOf()方法
        Integer i1 = 127 ; //调用Integer的valueOf()方法
        Integer i2 = 127 ;
        Integer i3 = 0 ;
        Integer i4 = new Integer(127) ;
        Integer i5 = new Integer(127) ;
        Integer i6 = new Integer(0) ;


        System.out.println("i0==i1 "+(i0==i1));//false
        System.out.println("i1==i2 "+(i1==i2));//true
        System.out.println("i1==i2+i3 "+(i1==i2+i3));//true  i2+i3拆箱做加法 然后 i1 拆箱再做比较
        System.out.println("i1==i4 "+(i1==i4));//false
        System.out.println("i4==i5 "+(i4==i5));//false
        System.out.println("i4==i5+i6 "+(i4==i5+i6));//true i5+i6 拆箱 做加法 得到127 i4 无法与数值直接比较  所以自动拆箱

    }

    @Test
    public void stringCacheTest() {

        String str1 = "abc";
        String str2 = "abc";
        String str3 = new String("abc");
        String str4 = new String("ccc");//创建了两个对象 一个是 常量池中的 一个是堆中的
        System.out.println("str1=str2 "+ (str1==str2) );//true
        System.out.println("str2=str3 "+ (str2==str3) );//false

        String ab1="a"+"b"; //ab1 = "ab"; //只创建了一个对象——编译器自动优化(为什么会优化 因为”a”,”b”是确定量)
        String a = "a";
        String b = "b";
        String ab2 = a+b; //为什么不能优化 因为a , b 是变量 如用final 修饰a b 那么就能优化了  因为 这时  a b 是一个常量
        System.out.println("ab1=ab2 " + (ab1==ab2));//false

        String ab3 = "ab";
        System.out.println("ab1=ab3 " + (ab1==ab3));//true


//        final String A = "a";
//        final String B = "b";
//        String ab4 = A+B;
//        System.out.println("ab4=ab3 " + (ab4==ab3));//true


//        如果这段代码替换以下代码，
//        因为使用了{…} /static{ …} 时A，B 此时不能在编译期间被确定值，这时编译器无法优化成 ab4 = "ab"
//        当多个确定量（常量）字符串相加时，编译器会为将他们直接编译为相加后的字符串，这种情况下 “+” 比StringBuilder 运行 效率更高。

        final String A ;
        final String B ;
        {
            A = "a";
            B = "b";
        }
        String ab4 = A+B;
        System.out.println("ab4=ab3 " + (ab4==ab3));//false
    }


}
