package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.RefurbishmentResult;

import java.io.IOException;

/**
 * 照片处理业务
 */
public interface PhotosService {

    //照片翻新
    String RefurbishmentPhotos(String fileId,String type,String fixFaceOnly) throws IOException;
}
