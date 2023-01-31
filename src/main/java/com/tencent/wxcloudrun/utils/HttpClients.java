package com.tencent.wxcloudrun.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.wxcloudrun.config.ZtApiResponse;
import com.tencent.wxcloudrun.model.RefurbishmentResult;
import okhttp3.*;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class HttpClients {

    /**
     * 设置超时时间
     */
    private static final int CONNECT_TIMEOUT = 6;
    private static final int READ_TIMEOUT = 60;
    private static final int WRITE_TIMEOUT = 6;

    /**
     * 设置编码集
     */
    private static final String ENCODE = "UTF-8";

    /**
     * public Builder() {
     * dispatcher = new Dispatcher();
     * protocols = DEFAULT_PROTOCOLS; //默认支持的协议
     * connectionSpecs = DEFAULT_CONNECTION_SPECS; //默认的连接规范
     * proxySelector = ProxySelector.getDefault(); //默认的代理选择器，直连
     * cookieJar = CookieJar.NO_COOKIES; //默认不进行管理Cookie
     * socketFactory = SocketFactory.getDefault();
     * hostnameVerifier = OkHostnameVerifier.INSTANCE; //主机验证
     * certificatePinner = CertificatePinner.DEFAULT; //证书锁，默认不开启
     * proxyAuthenticator = Authenticator.NONE; //默认不进行授权
     * authenticator = Authenticator.NONE;
     * connectionPool = new ConnectionPool(); //连接池
     * dns = Dns.SYSTEM;
     * followSslRedirects = true;
     * followRedirects = true;
     * retryOnConnectionFailure = true;
     * //超时时间
     * connectTimeout = 10_000;
     * readTimeout = 10_000;
     * writeTimeout = 10_000;
     * }
     */
    public static String doGet(String url, String path, Map<String, Object> mapParams) {
        String resultString = "";
        Response response = null;
        try {
            //******** 一、 创建 httpClient 对象***********
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .build();

            String params = setUrlParams(mapParams);
            StringBuffer strinb = new StringBuffer();
            strinb.append(url);
            strinb.append(path);
            strinb.append(params);

            // ******** 二、创建request 对象*************
            Request request = new Request.Builder()
                    .url(strinb.toString())
                    .get()
                    .build();

            //********* 三、发送请求 ********************

            response = httpClient.newCall(request).execute();

            //********* 四、对响应进行处理 ***************
            //1、如果 http 状态码不在 【200 ，300】区间内，抛出异常
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            // 因为服务器返回的数据可能非常大，所以必须通过数据流的方式来进行访问
            // 提供了诸如 string() 和 bytes() 这样的方法将流内的数据一次性读取完毕
            resultString = response.body().string();

            return resultString;
        } catch (SocketTimeoutException e) {
            // 超时
            System.out.println("executeHttpRequest TimeOut, e: " + e);
        } catch (IOException e) {
            // 网络IO异常
            System.out.println("executeHttpRequest IOException, e: " + e);
        } catch (Exception e) {
            // 其他异常
            e.printStackTrace();
        } finally {
            if (response != null) {
                // body 必须被关闭，否则会发生资源泄漏；
                response.body().close();
            }
        }
        return resultString;
    }

    public static String doPost(String url, String path, String param){

        Response response = null;
        String resultString = "";
        try{
            //******* 一、创建 httpClient 对象 **********
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .build();

            StringBuffer stringB = new StringBuffer();
            stringB.append(url);
            stringB.append(path);

            //******** 二、创建 request 对象 ************

            Request request = new Request.Builder()
                    .url(stringB.toString())
                    .post(okhttp3.RequestBody.create(MediaType.parse("application/json; charset=utf-8"), param))
                    .build();
            //******** 三、执行请求 ********************
            response = httpClient.newCall(request).execute();

            //******** 四、处理响应 ********************
            if(!response.isSuccessful()){
                throw new IOException( " Unexpect code " + response);
            }

            resultString = response.body().string();

        }catch (SocketTimeoutException e) {
            // 超时
            System.out.println("executeHttpRequest TimeOut, e: " + e);
        } catch (IOException e) {
            // 网络IO异常
            System.out.println("executeHttpRequest IOException, e: " + e);
        } catch (Exception e) {
            // 其他异常
            System.out.println("请求异常");
            e.printStackTrace();
        } finally {
            if (response != null) {
                // body 必须被关闭，否则会发生资源泄漏；
                response.body().close();
            }
        }
        return resultString;
    }

    public static String setUrlParams(Map<String, Object> mapParams) {

        // 是否已经设置了问号
        boolean hasSetQuestionMark = false;
        StringBuffer strParams = new StringBuffer("");
        if (mapParams != null) {
            Iterator<String> iterator = mapParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                try {
                    key = iterator.next().toString();
                    if (!hasSetQuestionMark) {
                        strParams.append("?").append(key).append("=")
                                .append(URLEncoder.encode(mapParams.get(key).toString(), "utf-8"));
                        hasSetQuestionMark = true;
                        continue;
                    }
                    strParams.append("&").append(key).append("=")
                            .append(URLEncoder.encode(mapParams.get(key).toString(), "utf-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return strParams.toString();
    }

}
