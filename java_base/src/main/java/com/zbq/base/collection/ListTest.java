package com.zbq.base.collection;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhangboqing
 * @date 2018/9/12
 */
public class ListTest {

    @Test
    public void run2() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            linkedList.add(i);
        }

        Instant now = Instant.now();
        for (int i = 0; i < linkedList.size(); i++) {
            linkedList.get(i);
        }
        Instant now2 = Instant.now();
        System.out.println(Duration.between(now,now2).toMillis());

        Iterator<Integer> iterator = linkedList.iterator();
        if (iterator.hasNext()) {
            iterator.next();
        }
        Instant now3 = Instant.now();
        System.out.println(Duration.between(now2,now3).toMillis());
    }


    @Test
    public void run() {
        List<String> list1 = new ArrayList<String>();
        list1.add("A");
        list1.add("B");

        List<String> list2 = new ArrayList<String>();
        list2.add("c");
        list2.add("C");

        boolean b = list1.retainAll(list2);
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(b);
    }


    public static void main(String[] args) {
        List<String> list1 = new ArrayList<String>();
        list1.add("A");
        list1.add("B");

        List<String> list2 = new ArrayList<String>();
        list2.add("B");
        list2.add("C");

//        1. 并集

        list1.addAll(list2);

//        运行结果：A, B, B, C

//        2. 无重复并集
        list2.removeAll(list1);
        list1.addAll(list2);

//        运行结果：A, B, C

//        3. 交集

        list1.retainAll(list2);

//        运行结果：B

//        4. 差集
        list1.removeAll(list2);

//        运行结果：A
    }




}
