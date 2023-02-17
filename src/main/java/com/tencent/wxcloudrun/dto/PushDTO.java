package com.tencent.wxcloudrun.dto;

import com.tencent.wxcloudrun.model.MiniprogramData;
import com.tencent.wxcloudrun.model.TemplateData;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class PushDTO {

    /**
     * 用户openid
     */
    private String touser;
    /**
     * //模版id
     */
    private String template_id = "sQGDpgiCrZWwNP-xUEyKVeg5HEs2cQ37YrlfIHaXFzg";
    /**
     * 默认
     */
    private String page = "pages/index/index";
    /**
     * 跳转小程序页面
     */
    private MiniprogramData miniprogram;
    /**
     * 放大那个推送字段
     */
    private String emphasisKeyword = "keyword1.DATA";
    /**
     * 推送文字
     */
    private Map<String, TemplateData> data;

    public PushDTO(String touser, String thing2) {
        Map<String, TemplateData> data = new HashMap<>();
        data.put("thing1", new TemplateData("您的彩票中奖了"));
        data.put("thing2", new TemplateData(thing2));
        this.touser = touser;
        this.data = data;
    }

    public PushDTO() {
    }
}
