package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dto.PushDTO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class PushBizService {

    private OkHttpClient okHttpClient = new OkHttpClient();

    @Value("${api.wx.appId}")
    private String appId;
    @Value("${api.wx.appSecret}")
    private String appSecret;

    /**
     * 发送方法
     * @param pushDTO 所需数据
     * @return responseEntity
     */
    @SneakyThrows
    public ResponseEntity<String> pushOneUser(PushDTO pushDTO) {
        final String SEND_INFO_URL = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=";
        RestTemplate restTemplate = new RestTemplate();
        // 获取accessTokenJson
        String accessTokenJson = getAccessTokenJSON(appId, appSecret);
        // 获取accessToken
        JSONObject jsonObject = JSONObject.fromObject(accessTokenJson);
        String accessToken = jsonObject.getString("access_token");
        // 获取过期时间
        Long expiresTime = jsonObject.getLong("expires_in");
        String url = SEND_INFO_URL + accessToken;

        return restTemplate.postForEntity(url, pushDTO, String.class);
    }

    /**
     * 查微信AcessToken,返回JSON值
     *
     * @param appId
     * @param appSecret
     * @return
     * @throws Exception
     */
    private String getAccessTokenJSON(String appId, String appSecret) throws Exception {
        String json = okHttpClient.newCall(
                new Request.Builder().url("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                        + appId + "&secret=" + appSecret).build())
                .execute().body().string();

        return json;
        }

}
