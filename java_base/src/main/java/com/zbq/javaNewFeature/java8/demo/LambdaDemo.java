package com.zbq.javaNewFeature.java8.demo;

import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author zhangboqing
 * @date 2018/3/29
 * <p>
 * <p>
 * (parameters) -> expression 或者 (parameters) -> {statements;}
 * 括号里的参数可以省略其类型，编译器会根据上下文来推导参数的类型，你也可以显式地指定参数类型，如果没有参数，括号内可以为空。
 * 方法体，如果有多行功能语句用大括号括起来，如果只有一行功能语句则可以省略大括号。
 */
public class LambdaDemo {

    /**
     * 1. 使用() -> {} 替代匿名类
     */
    @Test
    public void demo1() {
        // Before Java 8:
        Thread hah1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hah");
            }
        });


        // Java 8 way:
        Thread hah2 = new Thread(() -> System.out.println("hah"));
    }


    /**
     * 2.实现事件处理
     */
    @Test
    public void demo2() {

        // Before Java 8:
        JButton show = new JButton("Show");
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("without lambda expression is boring");
            }
        });


        // Java 8 way:
        show.addActionListener((e) -> {
            System.out.println("Action !! Lambda expressions Rocks");
        });
    }


    /**
     * 3.使用Lambda表达式遍历List集合
     */
    //方法引用是使用两个冒号::这个操作符号。
    @Test
    public void demo3() {

        //Prior Java 8 :
        List<String> features = Arrays.asList("Lambdas", "Default Method",
                "Stream API", "Date and Time API");
        for (String feature : features) {
            System.out.println(feature);
        }

        //In Java 8:
        List features2 = Arrays.asList("Lambdas", "Default Method", "Stream API",
                "Date and Time API");
        features.forEach(n -> System.out.println(n));

        // Even better use Method reference feature of Java 8
        // method reference is denoted by :: (double colon) operator
        // looks similar to score resolution operator of C++
        features.forEach(System.out::println);
    }


    /**
     * 4.使用Lambda表达式和函数接口
     */
    //为了支持函数编程，Java 8加入了一个新的包java.util.function，其中有一个接口java.util.function.Predicate是支持Lambda函数编程
    @Test
    public void demo4() {
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        String a = "1";

        System.out.println("Languages which starts with J :");
        filter(languages, (str) -> str.startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str) -> str.endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str) -> true);

        System.out.println("Print no language : ");
        filter(languages, (str) -> false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str) -> str.length() > 4);

    }

    public static void filter(List<String> names, Predicate<String> condition) {
        for (String name : names) {
            if (condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }


    /**
     * 5.复杂的结合Predicate 使用
     */
    //    java.util.function.Predicate提供and(), or() 和 xor()可以进行逻辑操作，比如为了得到一串字符串中以"J"开头的4个长度：
    //其中startsWithJ.and(fourLetterLong)是使用了AND逻辑操作
    @Test
    public void demo5() {
        List<String> names = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        // We can even combine Predicate using and(), or() And xor() logical functions
        // for example to find names, which starts with J and four letters long, you
        // can pass combination of two Predicate
        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;

        names.stream()
                .filter(startsWithJ.and(fourLetterLong))
                .forEach((n) -> System.out.print("\nName, which starts with 'J' and four letter long is : " + n));


    }


    /**
     * 6.使用Lambda实现Map 和 Reduce
     */
    /*
    最流行的函数编程概念是map，它允许你改变你的对象，
    在这个案例中，我们将costBeforeTeax集合中每个元素改变了增加一定的数值，
    我们将Lambda表达式 x -> x*x传送map()方法，这将应用到stream中所有元素。
    然后我们使用 forEach() 打印出这个集合的元素.
    */
    @Test
    public void demo6() {
        // applying 12% VAT on each purchase
        // Without lambda expressions:
        List<Integer> costBeforeTax1 = Arrays.asList(100, 200, 300, 400, 500);
        for (Integer cost1 : costBeforeTax1) {
            double price = cost1 + .12 * cost1;
            System.out.println(price);
        }

        // With Lambda expression:
        List<Integer> costBeforeTax2 = Arrays.asList(100, 200, 300, 400, 500);
        costBeforeTax2.stream().map((cost) -> cost + .12 * cost)
                .forEach(System.out::println);


        //reduce() 是将集合中所有值结合进一个，Reduce类似SQL语句中的sum(), avg() 或count(),

        // Applying 12% VAT on each purchase
        // Old way:
        List<Integer> costBeforeTax3 = Arrays.asList(100, 200, 300, 400, 500);
        double total = 0;
        for (Integer cost : costBeforeTax3) {
            double price = cost + .12 * cost;
            total = total + price;

        }
        System.out.println("Total : " + total);

        // New way:
        List<Integer> costBeforeTax4 = Arrays.asList(100, 200, 300, 400, 500);
        double bill = costBeforeTax4.stream().map((cost) -> cost + .12 * cost)
                .reduce((sum, cost) -> sum + cost)
                .get();
        System.out.println("Total : " + bill);
    }


    /**
     * 7.通过filtering 创建一个字符串String的集合
     */
    /*
    Filtering是对大型Collection操作的一个通用操作，Stream提供filter()方法，接受一个Predicate对象，意味着你能传送lambda表达式作为一个过滤逻辑进入这个方法：
    */
    @Test
    public void demo7() {

        List<String> strList = Arrays.asList("123","12","1");
        // Create a List with String more than 2 characters
        List<String> filtered = strList.stream().filter(x -> x.length()> 2)
                .collect(Collectors.toList());
        System.out.printf("Original List : %s, filtered list : %s %n",
                strList, filtered);

    }


    /**
     * 8.对集合中每个元素应用函数
     */
    /*
    我们经常需要对集合中元素运用一定的功能，如表中的每个元素乘以或除以一个值等等.
    */
    @Test
    public void demo8() {

        // Convert String to Uppercase and join them using coma
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany",
                "Italy", "U.K.","Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase())
                .collect(Collectors.joining(", "));
        System.out.println(G7Countries);

    }



    /**
     * 9.通过复制不同的值创建一个子列表
     */
    /*
    使用Stream的distinct()方法过滤集合中重复元素。
    */
    @Test
    public void demo9() {

        // Create List of square of all distinct numbers
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map( i -> i*i).distinct()
                .collect(Collectors.toList());
        System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);

    }

    /**
     * 10.计算List中的元素的最大值，最小值，总和及平均值
     */
    /*
    使用Stream的distinct()方法过滤集合中重复元素。
    */
    @Test
    public void demo10() {

        //Get count, min, max, sum, and average for numbers
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x)
                .summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());

    }




    public static void main(String[] args) {
        System.out.println(.12);
    }


}
