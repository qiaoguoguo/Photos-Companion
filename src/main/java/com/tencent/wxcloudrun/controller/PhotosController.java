package com.tencent.wxcloudrun.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.config.ZtApiResponse;
import com.tencent.wxcloudrun.model.RefurbishmentResult;
import com.tencent.wxcloudrun.service.PhotosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping(value = "/photos/test")
    ApiResponse test(){
        String result = "{\"status\":200,\"data\":{\"completed_at\":1675135406,\"created_at\":1675135403,\"image\":\"https://wxtechsz.oss-cn-shenzhen.aliyuncs.com/tasks/output/scale/5f7eb9f1-2613-40d3-94af-b9978c417430.jpg?Expires=1675139006\\u0026OSSAccessKeyId=LTAI5tGjJnh66c1txANiRBQN\\u0026Signature=siZwnJqyH8EhRxe6/2DUy5bqat8%3D\",\"processed_at\":1675135403,\"progress\":100,\"return_type\":1,\"state\":1,\"task_id\":\"5f7eb9f1-2613-40d3-94af-b9978c417430\",\"time_elapsed\":2023.32,\"type\":\"face\"}}";
        Gson gson = new Gson();
        ZtApiResponse<RefurbishmentResult> refurbishmentResult = gson.fromJson(result,new TypeToken<ZtApiResponse<RefurbishmentResult>>(){}.getType());
        return null;
    }


}
