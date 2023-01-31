package com.tencent.wxcloudrun.wxapi;

import com.google.gson.Gson;
import com.tencent.wxcloudrun.model.FileInfo;
import com.tencent.wxcloudrun.model.FileInfoRequest;
import com.tencent.wxcloudrun.utils.HttpClients;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 微信开放接口服务-文件管理相关方法
 */
@Component
public class WxApi {

    public static String ENV;
    public static String URL;
    public static String DOWNLOADFILE;

    @Value("${api.wx.env}")
    public void setEnv(String env){
        this.ENV = env;
    }

    @Value("${api.wx.url}")
    public void setDownloadfile(String url){
        this.URL = url;
    }

    @Value("${api.wx.downloadfile}")
    public void setUrl(String downloadfile){
        this.DOWNLOADFILE = downloadfile;
    }




    /**
     * 获取文件下载地址
     * @param fileId
     * @return
     */
    public static String getFileUrl(String fileId) {
        System.out.println("参数"+ENV+URL+DOWNLOADFILE);
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileid(fileId);
        fileInfo.setMax_age(7200);
        List<FileInfo> fileInfoList = new ArrayList<>();
        fileInfoList.add(fileInfo);
        FileInfoRequest fileInfoRequest = new FileInfoRequest();
        fileInfoRequest.setEnv(ENV);
        fileInfoRequest.setFile_list(fileInfoList);
        Gson gson = new Gson();
        String requestBody = gson.toJson(fileInfoRequest);
        String result = HttpClients.doPost(URL, DOWNLOADFILE, requestBody);
        return result;
    }

}
