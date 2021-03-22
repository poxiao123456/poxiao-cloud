package com.poxiao.system;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author qq
 * @date 2020/10/27
 */
public class TestJson {
    public static void main(String[] args){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("communityName","测试社区名");
        jsonObject.put("id",261);
        jsonArray.add(jsonObject);
        System.out.println("jsonArray:"+jsonArray.toJSONString());
        System.out.println("jsonArray2:"+jsonArray.toString());
    }
}
