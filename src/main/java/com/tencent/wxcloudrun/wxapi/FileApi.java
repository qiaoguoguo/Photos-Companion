package com.tencent.wxcloudrun.wxapi;

import com.tencent.wxcloudrun.model.FileInfo;
import com.tencent.wxcloudrun.utils.HttpClients;
import lombok.Data;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信开放接口服务-文件管理相关方法
 */
@Data
public class FileApi {

    public static String getFileUrl(String fileId) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileid(fileId);
        fileInfo.setMax_age(300);
        List<FileInfo> fileInfoList = new ArrayList<>();
        fileInfoList.add(fileInfo);
        Map postParams = new HashMap();
        postParams.put("env","prod-0grz6agt43c4b51b");
        postParams.put("file_list",fileInfoList);
        String result = HttpClients.doPost("https://api.weixin.qq.com", "/tcb/batchdownloadfile", postParams);
        return result;
    }

}
