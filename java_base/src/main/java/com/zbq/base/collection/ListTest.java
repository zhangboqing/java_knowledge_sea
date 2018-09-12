package com.zbq.base.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangboqing
 * @date 2018/9/12
 */
public class ListTest {



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
