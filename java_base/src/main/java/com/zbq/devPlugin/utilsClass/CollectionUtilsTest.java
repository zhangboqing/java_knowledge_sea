package com.zbq.devPlugin.utilsClass;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author zhangboqing
 * @date 2018/6/27
 */
public class CollectionUtilsTest {

    @Test
    public void run() {

        ArrayList<Integer> inputList = new ArrayList<>();
        inputList.add(1);
        inputList.add(2);

        Collection collect = CollectionUtils.collect(inputList, (a) -> {
            return "aaa";
        });

        System.out.println(collect);

    }

    @Test
    public void run2() {
        Object a = Integer.valueOf(1);

        Long b = (Long) a;

        System.out.println(b);
    }



}

