package com.tencent.wxcloudrun.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.config.ZtApiResponse;
import com.tencent.wxcloudrun.dto.CounterRequest;
import com.tencent.wxcloudrun.dto.RefurbishmentRequest;
import com.tencent.wxcloudrun.model.RefurbishmentResult;
import com.tencent.wxcloudrun.service.PhotosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 图片处理控制器
 */
@RestController
@Slf4j
public class PhotosController {

    @Autowired
    private PhotosService photosService;

    /**
     * 老照片修复
     * @return API response json
     */
    @PostMapping(value = "/photos/refurbishment")
    ApiResponse get(@RequestBody RefurbishmentRequest request) throws IOException {
        log.info("调用照片修复接口request：{}",request.toString());
        String image = photosService.RefurbishmentPhotos(request.getFileId(),request.getType(),request.getFixFaceOnly());
        return ApiResponse.ok(image);
    }

    /**
     * 模糊照片修复
     * @return API response json
     */


}
