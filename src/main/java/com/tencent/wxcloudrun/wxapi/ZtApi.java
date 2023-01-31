package com.tencent.wxcloudrun.wxapi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.wxcloudrun.config.ZtApiResponse;
import com.tencent.wxcloudrun.model.RefurbishmentResult;
import lombok.Data;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * 佐糖API接口
 */
@Data
@Component
public class ZtApi {

    @Value("${api.zt.key}")
    private String key1;
    @Value("${api.zt.scale}")
    private String scale1;

    private static String key;
    private static String scale;

    @PostConstruct
    public void init() {
        key = key1;
        scale = scale1;
    }

    /**
     * 佐糖图片变清晰接口(同步)
     * @param fileUrl
     * @param type clean: 通用放大清晰化 face: 人像放大清晰化
     * @param fixFaceOnly 只针对于face 0：整图处理【默认】1：只处理人脸
     * @return
     */
    public static ZtApiResponse<RefurbishmentResult> RefurbishmentPhotos(String fileUrl,String type,String fixFaceOnly){
        System.out.println("参数："+key+scale);
        Gson gson = new Gson();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image_url", fileUrl)
                .addFormDataPart("type", type)
                .addFormDataPart("fix_face_only", fixFaceOnly)
                .addFormDataPart("sync", "1")
                .build();
        Request request = new Request.Builder()
                .url(scale)
                .addHeader("X-API-KEY", key)
                .post(requestBody)
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            String result = response.body().string();
            ZtApiResponse<RefurbishmentResult> refurbishmentResult = gson.fromJson(result, new TypeToken<ZtApiResponse<RefurbishmentResult>>() {
            }.getType());
            return refurbishmentResult;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                // body 必须被关闭，否则会发生资源泄漏；
                response.body().close();
            }
        }
        return null;
    }

}
