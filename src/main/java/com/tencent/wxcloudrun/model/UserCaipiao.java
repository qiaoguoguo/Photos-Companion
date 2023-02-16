package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserCaipiao implements Serializable {

    private String type;

    private String number;

    private String refernumber;

    private String openid;
}
