package com.tencent.wxcloudrun.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.wxcloudrun.config.ZtApiResponse;
import com.tencent.wxcloudrun.model.FileDownLoadResult;
import com.tencent.wxcloudrun.model.RefurbishmentResult;
import com.tencent.wxcloudrun.service.PhotosService;
import com.tencent.wxcloudrun.wxapi.WxApi;
import com.tencent.wxcloudrun.wxapi.ZtApi;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class PhotosServiceImpl implements PhotosService {

    /**
     * 人脸变清晰方法
     * @param fileId
     * @param type
     * @param fixFaceOnly
     * @return
     * @throws IOException
     */
    @Override
    public String RefurbishmentPhotos(String fileId,String type,String fixFaceOnly) throws IOException {
        Gson gson = new Gson();
        String file = WxApi.getFileUrl(fileId);
        FileDownLoadResult fileDownLoadResult = gson.fromJson(file, new TypeToken<FileDownLoadResult>() {
        }.getType());
        if (fileDownLoadResult.getErrcode() == 0) {
            String url = fileDownLoadResult.getFile_list().get(0).getDownload_url();
            ZtApiResponse<RefurbishmentResult> refurbishmentResultZtApiResponse = ZtApi.RefurbishmentPhotos(url, type, fixFaceOnly);
            if (refurbishmentResultZtApiResponse.getStatus() == 200){
                return refurbishmentResultZtApiResponse.getData().getImage();
            }else {
                log.error("调用佐糖人脸变清晰接口错误，错误码：{}",refurbishmentResultZtApiResponse.getStatus());
                return null;
            }
        } else {
            log.error("获取微信fileId:{}的文件下载地址错误，错误码：{}", fileId, fileDownLoadResult.getErrcode());
            return null;
        }
    }
}
