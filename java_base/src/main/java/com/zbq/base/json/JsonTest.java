package com.zbq.base.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zbq.base.json.bean.JsonObj;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

/**
 * @author zhangboqing
 * @date 2017/12/25
 */
public class JsonTest {


    @Test
    public void run() {

        JsonObj jsonObj = new JsonObj();
        HashMap<String, String> map = new HashMap<>();


        map.put("obj1","111");
        map.put("obj2","222");
        jsonObj.setName("zbq");
        jsonObj.setMap(map);;

        System.out.println(JSON.toJSONString(jsonObj.getMap()));

    }


    @Test
    public void run2() {
        String storeIds;
        List<List> lists = JSON.parseArray("[[1,2,3],[4,5,6]]", List.class);
        for (List list : lists) {
        }
//        storeIds =  org.apache.commons.lang3.StringUtils.join(storeIdList,",");
//        System.out.println(storeIds);
    }


    public static void main(String[] args) {
        System.out.println(JSONObject.toJSONString(null));
    }
}
