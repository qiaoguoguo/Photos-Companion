package com.tencent.wxcloudrun.utils;

import lombok.Data;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class Query {

    @Value("${api.cp.key}")
    private String key;

    public static String APPKEY;// 你的appkey
    public static final String URL = "https://api.jisuapi.com";
    public static final String PATH = "/caipiao/query";

    @PostConstruct
    public void init() {
        APPKEY = key;
    }

    public static JSONObject Get(Integer caipiaoid,String issueno) {
        String result = null;
        Map parm = new HashMap();
        parm.put("appkey",APPKEY);
        parm.put("caipiaoid",caipiaoid);
        parm.put("issueno",issueno);
        try {
            result = HttpClients.doGet(URL,PATH,parm);
            JSONObject json = JSONObject.fromObject(result);
            if (json.getInt("status") != 0) {
                return null;
            } else {
                JSONObject resultarr = json.optJSONObject("result");
                return resultarr;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}