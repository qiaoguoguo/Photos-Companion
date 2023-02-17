package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserCaipiao implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String type;

    private String number;

    private String refernumber;

    private String openid;
}
