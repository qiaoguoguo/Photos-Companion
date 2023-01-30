package com.tencent.wxcloudrun.config;

import lombok.Data;

@Data
public class ZtApiResponse<T> {

    private Integer status;
    private String message;
    private T data;

    public static ZtApiResponse success(Object object){
        ZtApiResponse resultVO = new ZtApiResponse();
        resultVO.setData(object);
        resultVO.setMessage("成功");
        resultVO.setStatus(0);
        return resultVO;
    }

    public static ZtApiResponse success(){
        return success(null);
    }

    public static ZtApiResponse error(Integer code,String msg){
        ZtApiResponse resultVO = new ZtApiResponse();
        resultVO.setStatus(code);
        resultVO.setMessage(msg);
        return resultVO;
    }

}
