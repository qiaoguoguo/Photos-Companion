package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserZhongjiang {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String openid;

    private String type;

    private String unumber;

    private String urefernumber;

    private String number;

    private String refernumber;

    private String issueno;

    private String zuhe;

    private Integer level;



}
