package com.zbq.base.collection;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangboqing
 * @date 2018/1/30
 */
public class SetTest {

    @Test
    public void run() {
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        set.add(1);
        set.add(5);
        set.add(3);
        set.add(4);
        System.out.println("LinkedHashSet:"+set);

        List<Integer> list = set.stream().collect(Collectors.toList()).subList(2,3);
        System.out.println("list:"+list);


        HashSet<Integer> set2 = new HashSet<>();
        set2.add(1);
        set2.add(5);
        set2.add(3);
        set2.add(4);
        System.out.println("HashSet:"+set2);
    }
}
