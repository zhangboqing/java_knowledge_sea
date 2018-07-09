package com.zbq.devPlugin.lombok;

import lombok.NonNull;
import org.junit.Test;
import org.springframework.lang.Nullable;

/**
 * @author zhangboqing
 * @date 2018/6/21
 */
public class LombokTest {


    public static void main(String[] args) {

        System.out.println(getValue(null));
    }


    @Test
    public void run() {

        System.out.println(getValue(null));
    }



    @Nullable
    public static String getValue(String name) {
        String result = name;
        result = "1";
        return result;
    }

    public static String getValue2(@NonNull String name) {
        String result = name;

        return result;
    }
}
