package com.tencent.wxcloudrun.wxapi;

import com.google.gson.Gson;
import com.tencent.wxcloudrun.model.FileInfo;
import com.tencent.wxcloudrun.model.FileInfoRequest;
import com.tencent.wxcloudrun.utils.HttpClients;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 微信开放接口服务-文件管理相关方法
 */
public class WxApi {

    @Value("${api.wx.env}")
    private String env1;
    @Value("${api.wx.url}")
    private String url1;
    @Value("${api.wx.downloadfile}")
    private String downloadfile1;

    private static String env;
    private static String url;
    private static String downloadfile;

    @PostConstruct
    public void init() {
        env = env1;
        url = url1;
        downloadfile = downloadfile1;
    }

    /**
     * 获取文件下载地址
     * @param fileId
     * @return
     */
    public static String getFileUrl(String fileId) {
        System.out.println("参数"+env+url+downloadfile);
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileid(fileId);
        fileInfo.setMax_age(7200);
        List<FileInfo> fileInfoList = new ArrayList<>();
        fileInfoList.add(fileInfo);
        FileInfoRequest fileInfoRequest = new FileInfoRequest();
        fileInfoRequest.setEnv(env);
        fileInfoRequest.setFile_list(fileInfoList);
        Gson gson = new Gson();
        String requestBody = gson.toJson(fileInfoRequest);
        String result = HttpClients.doPost(url, downloadfile, requestBody);
        return result;
    }

}
