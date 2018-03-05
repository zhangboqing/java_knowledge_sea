package com.zbq.functionaProgram;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @author zhangboqing
 * @date 2018/3/4
 */
public class FunctionDemo1 {


    @Test
    public void run() {

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("func1");
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        stringList.add("func2");


        stringList.stream().filter(x -> x.startsWith("func")).mapToLong(x -> x.hashCode())
                .forEach(x -> System.out.println(x));
    }

}
