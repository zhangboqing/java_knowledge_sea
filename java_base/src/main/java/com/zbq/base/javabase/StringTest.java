package com.zbq.base.javabase;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @author zhangboqing
 * @date 2017/11/21
 */
public class StringTest {

    @Test
    public void run1() {
        String a = "1";
        String b = "1";

        System.out.println(String.join(".",a,b));

        ArrayList<String> list = new ArrayList<>();
        list = null;
        Assert.notEmpty(list);

        System.out.println("ahha ");


    }


    @Test
    public void run2() {
//        ArrayList<String> list = new ArrayList<>();
//        list.add("a");
//        list.add(null);

//        System.out.println(StringUtils.join(list,"、"));

        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("1");
        treeSet.add("2");
        System.out.println(StringUtils.join(treeSet,"、"));
        System.out.println(String.join("、",treeSet));


    }

    @Test
    public void run3() {

        String a = "22121212,,,,,,";
        String[] split = a.split(",");
        for (String s : split) {

            System.out.println(s);
        }
    }



    @Test
    public void run4() {

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(null);
        arr.add(null);
        arr.add(3);
        arr.add(2);

        System.out.println("排序前："+arr);
        Collections.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                if (Objects.isNull(o1)) {
                    return -1;
                }

                if (Objects.isNull(o2)) {
                    return 1;
                }



                return o1.compareTo(o2);
            }
        });
        System.out.println("排序后："+arr);

    }

    @Test
    public void run5() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("123,");
//        System.out.println(sb.append(-1));

        System.out.println(Long.MAX_VALUE);
    }


    @Test
    public void run6() {
        String str = "12121";

        str = str.replaceAll(",", "','");
        str = "'"+str+"'";
        System.out.println(str);
    }


    @Test
    public void run7() {
        String str = "12121";
        String str2 = "121212";

        System.out.println(str == str2);

    }





}
