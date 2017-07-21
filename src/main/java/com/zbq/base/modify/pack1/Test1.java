package com.zbq.base.modify.pack1;

import org.junit.Test;

/**
 * Created by zhangboqing on 2017/7/19.
 *
 */
public class Test1 {

    @Test
    public void run() {
        Obj1 obj1 = new Obj1();

        System.out.println(obj1.dStr);
        System.out.println(obj1.prStr);
        System.out.println(obj1.puStr);

        obj1.printStr2();
        obj1.printStr3();
        obj1.printStr4();
    }



}
