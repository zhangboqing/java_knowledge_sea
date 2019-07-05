package com.zbq.jvm.classInvoke;

/**
 * @author zhangboqing
 * @date 2019-06-19
 */
public class ObjBean {
    private Integer a;

    public ObjBean() {
        System.out.println(1);
    }

    public static final void init() {
        System.out.println(1);
    }

    public static void main(String[] args) {
        ObjBean objBean = new ObjBean();
    }
}
