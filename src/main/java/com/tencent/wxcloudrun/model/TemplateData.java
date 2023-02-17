package com.tencent.wxcloudrun.model;

import lombok.Data;

@Data
public class TemplateData {

    private String value;

    public TemplateData(String value) {
        this.value = value;
    }
}
