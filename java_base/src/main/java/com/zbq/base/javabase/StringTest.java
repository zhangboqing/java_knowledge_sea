package com.zbq.base.javabase;

import com.alibaba.fastjson.JSONObject;
import com.zbq.base.javabase.bean.Haha;
import com.zbq.base.javabase.bean.User;
import com.zbq.base.javabase.bean.User2;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.text.NumberFormat;
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

        System.out.println(String.join(".", a, b));

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
        System.out.println(StringUtils.join(treeSet, "、"));
        System.out.println(String.join("、", treeSet));


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

        System.out.println("排序前：" + arr);
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
        System.out.println("排序后：" + arr);

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
        str = "'" + str + "'";
        System.out.println(str);
    }


    @Test
    public void run7() {
        String str = "12121";
        String str2 = "121212";

        System.out.println(str == str2);

    }


    @Test
    public void run8() {

        Long categary = -1L;

        System.out.println(categary.equals(-1L));

    }

    @Test
    public void run9() {
        String a = "1";

        switch (a) {
            case "1":
                System.out.println(a);
                break;
            default:
                System.out.printf("");
        }

    }

    @Test
    public void run10() {
        User user = new User();
        ArrayList<Haha> hahas = new ArrayList<>();
        Haha haha = new Haha();
        haha.setName("haha");
        hahas.add(haha);
        user.setA(1);
        user.setList(hahas);

        User user2 = new User();

        BeanUtils.copyProperties(user, user2);

        System.out.println(user2);
        System.out.println(JSONObject.toJSONString(hahas));

    }


    @Test
    public void run11() {
        User user = new User();
        BigDecimal money = new BigDecimal("10.11");
        user.setMoney(money);

        NumberFormat nf = NumberFormat.getInstance();

        User2 user2 = new User2();
//        BeanUtils.copyProperties(user,user2);
//        user2.setMoney(money.stripTrailingZeros().toPlainString());
        user2.setMoney(nf.format(money));
        System.out.println(user2);

    }


    @Test
    public void run12() {
        ArrayList<String> barcodeList = new ArrayList<>();
        barcodeList.add("123");
//        barcodeList.add("123");
        StringBuilder sb = new StringBuilder();
        barcodeList.stream().forEach(s -> {
            sb.append("'").append(s).append("'").append(",");
        });

        sb.deleteCharAt(sb.length() - 1);
//        System.out.println(sb.toString());

        System.out.println(StringUtils.join(barcodeList, "、"));
    }


//    public static void main(String[] args) {
//        System.out.println(String.valueOf(null));
//    }


    @Test
    public void run13() {
//        BigDecimal stockNum = BigDecimal.valueOf(0.012112);
//
//        String stockNumStr = stockNum.toString();
//        System.out.println(stockNumStr.indexOf("."));
//        if (stockNumStr.indexOf(".") > 0) {
//            stockNum = new BigDecimal(stockNumStr.substring(0, stockNumStr.indexOf(".")));
//        }
//
//
//        System.out.println(stockNum);

        System.out.println();
    }


}
