package com.zbq.base.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @author zhangboqing
 * @date 2018/3/30
 */
public class TreeMapTest {

    @Test
    public void run() {
        ArrayList<String> list = new ArrayList<>();
        list.add("2018年-第1周");
        list.add("2018年-第2周");
        list.add("2018年-第3周");
        list.add("2018年-第4周");
        list.add("2018年-第5周");
        list.add("2018年-第6周");
        list.add("2018年-第7周");
        list.add("2018年-第8周");
        list.add("2018年-第9周");
        list.add("2018年-第10周");
        list.add("2018年-第11周");
        list.add("2018年-第12周");
        list.add("2018年-第13周");

        TreeMap<String, String> treeMap = new TreeMap<>((a,b)-> 1);
        for (String s : list) {
            treeMap.put(s,"1");
        }
        list.stream().forEach((a)-> System.out.println(a));

        System.out.println("------");
        for (String s : treeMap.keySet()) {
            System.out.println(s);
        }

        System.out.println("------");
        TreeMap<String, String> treeMap2 = new TreeMap<>((a,b)-> 1);
        treeMap2.put("2018年-第11周","1");
        treeMap2.put("2018年-第1周","1");
        for (String s : treeMap2.keySet()) {
            System.out.println(s);
        }


        String a = "2018年-第1周";
        String b = "2018年-第11周";
        String c = "第2周";
        String d = "11";
        String e = "1";

        System.out.println(d.compareTo(e));
//        System.out.println(c.compareTo(b));


    }

    public static void main(String[] args) {
        String a = "2018年-第01周";
        String b = "2018年-第11周";
        String c = "第2周";
        String d = "周";
        String e = "1";
        int i = e.compareTo(d);
        int i2 = b.compareTo(a);
        System.out.println(i);
        System.out.println(i2);
    }
}
