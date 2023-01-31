package com.tencent.wxcloudrun.wxapi;

import com.google.gson.Gson;
import com.tencent.wxcloudrun.model.FileInfo;
import com.tencent.wxcloudrun.model.FileInfoRequest;
import com.tencent.wxcloudrun.utils.HttpClients;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信开放接口服务-文件管理相关方法
 */
public class FileApi {

    public static String getFileUrl(String fileId) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileid(fileId);
        fileInfo.setMax_age(7200);
        List<FileInfo> fileInfoList = new ArrayList<>();
        fileInfoList.add(fileInfo);
        FileInfoRequest fileInfoRequest = new FileInfoRequest();
        fileInfoRequest.setEnv("prod-0grz6agt43c4b51b");
        fileInfoRequest.setFile_list(fileInfoList);
        Gson gson = new Gson();
        String requestBody = gson.toJson(fileInfoRequest);
        String result = HttpClients.doPost("https://api.weixin.qq.com", "/tcb/batchdownloadfile", requestBody);
        return result;
    }

}
