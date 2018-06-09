package com.zbq.base.javabase;

import com.alibaba.fastjson.JSONObject;
import com.zbq.base.javabase.bean.Haha;
import com.zbq.base.javabase.bean.User;
import com.zbq.base.javabase.bean.User2;
import com.zbq.utils.PinYinUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

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
//        ArrayList<String> list = new ArrayList<>();
//        list.add("第1行，数据错误，1");
//        list.add("第20行，数据错误，20");
//        list.add("第3行，数据错误，3");
//        list.add("第2行，数据错误，2");
//        list.add("数据错误");
//        System.out.println(sortErrorList(list));

//        System.out.println("11 ".matches("[a-zA-Z0-9]{1,36}"));

        System.out.println(new BigDecimal("200.00").divide(new BigDecimal(100),2,BigDecimal.ROUND_DOWN ));
    }


    @Test
    public void run14() {
        HashSet<StringBuilder> hashSet = new HashSet<>();

        StringBuilder sb1 = new StringBuilder("aaa");
        StringBuilder sb2 = new StringBuilder("aaabbb");

        hashSet.add(sb1);
        hashSet.add(sb2);

        StringBuilder sb3 = sb1;
        sb3.append("bbb");

        System.out.println(hashSet);

    }


    @Test
    public void run15() {

        String[] arr =  {"奶乳饮品","冲饮保健","休闲零食","雪糕冰棍","饼干糕点","方便速食","酒水畅饮","甜心糖果","粮油副食","调料蘸料","家居清洁","美容洗护","个人护理","生鲜果蔬","日用百货","节日礼品","代购香烟","泉水饮料","奶粉","尿裤湿巾","营养辅食","喂养用品","洗护用品","童车童床","妈妈专区","寝具服饰","玩具乐器","母婴护理","安全座椅","母婴清洁","母婴服务","动漫玩具","绘画/DIY","积木拼插","优质奶粉","遥控/电动","娃娃玩具","益智玩具","毛绒布艺","健身玩具","模型玩具","创意减压","其他","自定义"};


//        for (String name : arr) {
            System.out.println(PinYinUtils.getPinyin("保健品"));
//        }


    }

    @Test
    public void run16() {

        List<String> list = Arrays.asList("1", "2", "3");
        Vector<String> vector = new Vector<>(list);

        for (int i = 0; i < vector.size(); i++) {

            vector.remove(i);
            System.out.println(vector);
        }

        System.out.println(vector);

    }


    @Test
    public void run17() {
        BigDecimal aa = new BigDecimal("1");

        BigDecimal realStockNum = BigDecimal.ZERO;
        realStockNum.add(new BigDecimal(1));
        realStockNum.add(new BigDecimal(1));
        System.out.println(realStockNum.intValue());
//        System.out.println(aa.intValue());


    }

    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        System.out.println(n);
        n |= n >>> 2;
        System.out.println(n);
        n |= n >>> 4;
        System.out.println(n);
        n |= n >>> 8;
        System.out.println(n);
        n |= n >>> 16;
        System.out.println(n);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }



    /** === ============================ ==*/
    public List<String> sortErrorList(List<String> errorList) {

        if (CollectionUtils.isEmpty(errorList)) {
            return errorList;
        }

        errorList = errorList.stream().sorted((o1,o2)-> {
            //定义排序规则
            int firstRow = 0;
            int secondRow = 0;

            int index1 = o1.indexOf("第");
            int index2 = o1.indexOf("行");
            //没有显示行号的错误,都展示在最后
            if (index1 < 0) {
                return 1;
            }

            int index3 = o2.indexOf("第");
            int index4 = o2.indexOf("行");
            if (index3 < 0) {
                return 1;
            }

            firstRow =  Integer.valueOf(o1.substring(index1+1,index2));
            secondRow =  Integer.valueOf(o2.substring(index3+1,index4));

            return firstRow - secondRow;
        }).collect(Collectors.toList());
        return errorList;
    }

    /**
     * 生成随机的符合规则的条码
     * */
    public static synchronized String randomBarcode(String start, int end) {
        if (end < 1) {
            end = 1;
        }
        if (end > 999) {
            end = 999;
        }
        if( StringUtils.isBlank(start) ){
            start = "";
        }

        /**指定条码开头数字**/
        int length = start.length();
        String regex = "^";
        for (int i = 0; i < length; i++) {
            regex += ".";
        }
        String string = String.valueOf(System.currentTimeMillis() / 1000).concat(String.format("%03d", end));


        return string;
    }
}
