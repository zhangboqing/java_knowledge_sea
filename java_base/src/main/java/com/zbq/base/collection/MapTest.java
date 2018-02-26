package com.zbq.base.collection;

import org.junit.Test;

import java.util.HashMap;

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
}
