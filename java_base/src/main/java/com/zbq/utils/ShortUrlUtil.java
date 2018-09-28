package com.zbq.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author zhangboqing
 * @date 2018/9/18
 */
public class ShortUrlUtil {

    private static final String app_key = "2735081358";

    /**
     * source去新浪微博申请App Key
     * @param long_url
     * @return
     */
    public static String getShortUrl(String long_url) {
        String result = HttpUtils.get(
                "https://api.weibo.com/2/short_url/shorten.json?source="+app_key+"&url_long=" + long_url);

        JSONObject jsonObject = JSON.parseObject(result);

        JSONObject urls = jsonObject.getJSONArray("urls").getJSONObject(0);
        String url_short = urls.getString("url_short");
        return url_short;
    }

    public static void main(String[] args) {
        System.out.println(getShortUrl("https://www.baidu.com"));
    }
}
