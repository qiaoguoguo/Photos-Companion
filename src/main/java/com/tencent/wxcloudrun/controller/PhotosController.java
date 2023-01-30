package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.Counter;
import com.tencent.wxcloudrun.model.RefurbishmentResult;
import com.tencent.wxcloudrun.service.PhotosService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

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
    @GetMapping(value = "/photos/refurbishment")
    ApiResponse get() throws IOException {
        log.info("调用照片修复接口");
        RefurbishmentResult refurbishmentResult = photosService.RefurbishmentPhotos();
        return ApiResponse.ok(refurbishmentResult);
    }

    /**
     * 模糊照片修复
     * @return API response json
     */


}
