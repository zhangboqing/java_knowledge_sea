package com.zbq.base.json.bean;

import java.util.Map;

/**
 * @author zhangboqing
 * @date 2017/12/25
 */
public class JsonObj {

    private String name;
    private Map<String,String> map;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
