package com.tencent.wxcloudrun.config;

import lombok.Data;

@Data
public class ZtApiResponse<T> {

    private Integer status;
    private String message;
    private T data;

}
