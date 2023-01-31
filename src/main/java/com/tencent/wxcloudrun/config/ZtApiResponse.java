package com.tencent.wxcloudrun.config;

import com.tencent.wxcloudrun.model.RefurbishmentResult;
import lombok.Data;

@Data
public class ZtApiResponse<T> {

    private Integer status;
    private String message;
    private T data;

}
