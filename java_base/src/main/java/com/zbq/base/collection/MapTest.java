package com.zbq.base.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 * @author zhangboqing
 * @date 2018/2/1
 */
public class MapTest {


    @Test
    public void run() {

        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("1",1);

        System.out.println(hashMap.get("1"));
    }

    @Test
    public void run2() {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        Hashtable<String, Integer> hashtable = new Hashtable<>();
        TreeMap<String, Integer> treeMap = new TreeMap<>();


        linkedHashMap.put(null,null);
        hashtable.put("a",1);
        treeMap.put("a",null);

        for (String s : linkedHashMap.keySet()) {
            System.out.println(s+"===>"+linkedHashMap.get(s));
        }

    }

}
