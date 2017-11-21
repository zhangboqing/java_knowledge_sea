package com.zbq.base.modify.pack1;

/**
 * Created by zhangboqing on 2017/7/19.
 *
 * private,default,protected,public
 * 用于验证四种访问修饰符，作用范围
 */
public class Obj1 {


    private String pStr = "private";
    String dStr = "default";
    protected  String prStr = "protected";
    public  String puStr = "public";


    private void printStr1() {
        System.out.println("private");
    }

    void printStr2() {
           System.out.println("default");
    }

    protected void printStr3() {
        System.out.println("protected");
    }

    public void printStr4() {
        System.out.println("public");
    }
}
