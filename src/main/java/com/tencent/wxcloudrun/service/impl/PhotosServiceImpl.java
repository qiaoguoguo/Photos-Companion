package com.tencent.wxcloudrun.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.wxcloudrun.config.ZtApiResponse;
import com.tencent.wxcloudrun.model.RefurbishmentResult;
import com.tencent.wxcloudrun.service.PhotosService;
import com.tencent.wxcloudrun.wxapi.FileApi;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class PhotosServiceImpl implements PhotosService {

    @Override
    public RefurbishmentResult RefurbishmentPhotos() throws IOException {
        String url = FileApi.getFileUrl("cloud://prod-0grz6agt43c4b51b.7072-prod-0grz6agt43c4b51b-1307548922/20230129152743.jpg");
        System.out.println("获取到云存储照片下载地址：" + url);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image_url",url)
                .addFormDataPart("type", "face")
                .addFormDataPart("sync", "1")
                .build();
        Request request = new Request.Builder()
                .url("https://techsz.aoscdn.com/api/tasks/visual/scale")
                .addHeader("X-API-KEY", "wxr88le5ucb8bfoh2")
                .post(requestBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        System.out.println("佐糖照片修复后返回信息："+response.body());
        String result = response.body().string();
        Gson gson = new Gson();
        ZtApiResponse<RefurbishmentResult> refurbishmentResult = gson.fromJson(result,new TypeToken<ZtApiResponse<RefurbishmentResult>>(){}.getType());
        return refurbishmentResult.getData();
    }

}
