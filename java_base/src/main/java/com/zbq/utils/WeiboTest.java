package com.zbq.utils;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangboqing
 * @date 2018/9/18
 */
public class WeiboTest {

    @Test
    public void shortUrlTest() {

        Map<String,Object> map = new HashMap<>();
        map.put("url_long","");
        String s = HttpUtils.get("https://api.weibo.com/2/short_url/shorten.json", map);

        System.out.println(s);


    }
}
