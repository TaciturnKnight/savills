package com.bamboo.savills.utils;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

/**
 * 作者： LYJ
 * 功能： 请求对象
 * 创建日期： 2017/5/3
 */

public class HttpClient {
    private OkHttpClient client;//网络请求对象

    /**
     * 私有的构造器 在HttpClient实例化后 创建OkHttpClient对象
     * 用于网络的请求
     */
    private HttpClient() {
        client = new OkHttpClient.Builder()
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .sslSocketFactory(createSSLSocketFactory())
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .build();
    }
    public static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }

    public static class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
    private static class SingleTon {
        public static final HttpClient INSTANCE = new HttpClient();
    }

    /**
     * 获取单例
     *
     * @return
     */
    public static HttpClient getInstance() {
        return SingleTon.INSTANCE;
    }

    /**
     * 获取OkHttpClient
     *
     * @return
     */
    public OkHttpClient getClient() {
        return client;
    }
}
