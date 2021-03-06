package com.bamboo.savills.base.net;

import android.content.Context;
import android.os.Handler;
import androidx.annotation.NonNull;

import com.bamboo.savills.base.utils.LogUtil;
import com.bamboo.savills.base.utils.StringUtil;
import com.bamboo.savills.base.view.BaseApplication;
import com.bamboo.savills.base.view.ToastUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by qyj on 2019/9/29.
 */

public class HttpUtil {

    private OkHttpClient client;
    public static final String JSON = "application/json; charset=utf-8";
    public static final String URLENCODE = "application/x-www-form-urlencoded";
    public static final String LANCOOKIE_FN = "lang=zh-hk";
    public static final String LANCOOKIE_CN = "lang=zh-cn";
    public static final String LANCOOKIE_EN = "lang=en";
    //拦截器 （暂时没有需求）
    private Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request finalRequest = null;
            if ("GET".equals(request.method())) {
                //get请求
                String url = request.url().toString();
                //在url后加公共参数
//                url+="&token=''";
                finalRequest = request.newBuilder()
                        .get()
                        .url(url)
                        .build();
            } else if ("POST".equals(request.method())) {
                //post请求
                RequestBody body = request.body();
                FormBody.Builder builder = new FormBody.Builder();
                if (body instanceof FormBody) {
                    FormBody formBody = (FormBody) body;
                    for (int i = 0; i < formBody.size(); i++) {
                        builder.add(formBody.encodedName(i), formBody.encodedValue(i));
                    }
                    //builder中添加公共参数
//                    builder.add("token","");
                    finalRequest = request.newBuilder()
                            .post(builder.build())
                            .url(request.url().toString())
                            .build();
                }
            }
            Response response = chain.proceed(finalRequest);
            return response;

        }
    };
    private Handler mainHandler;

    private HttpUtil() {
        client = new OkHttpClient.Builder().connectTimeout(1000 * 10, TimeUnit.SECONDS).readTimeout(1000 * 10, TimeUnit.SECONDS).writeTimeout(1000 * 10, TimeUnit.SECONDS)
                .cookieJar(new CookieJar() {
                    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        LogUtil.e("saveFromResponse","saveFromResponse----------" );
                        cookieStore.put(url, cookies);
                        cookieStore.put(HttpUrl.parse(RequstList.BASE_URL+RequstList.SESSION_AUTHENTICATION), cookies);
                        for (Cookie cookie : cookies) {
                            LogUtil.e("cookie","cookie Name:"+cookie.name()+"  cookie value "+cookie.value());
                            LogUtil.e("cookie","cookie Path:"+cookie.path());
                        }
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore
                                .get(HttpUrl.parse(RequstList.BASE_URL+RequstList.SESSION_AUTHENTICATION));
                        if (cookies == null) {
                            LogUtil.e("cookie","没加载到cookie");
                        }
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                }).sslSocketFactory(RxUtils.createSSLSocketFactory())
                .hostnameVerifier(new RxUtils.TrustAllHostnameVerifier())
                .retryOnConnectionFailure(true).build();
        client.dispatcher().setMaxRequests(10);//最大请求数
    }

    public OkHttpClient getClient() {
        return client;
    }

    public static HttpUtil getInstance() {
        return HttpUtilHolder.instatnce;
    }

    private static class HttpUtilHolder {
        private static HttpUtil instatnce = new HttpUtil();
    }

    //通用post请求，默认添加BASE_URL
    public void post(Context mContext, String url, int tag, RequestParams params, NetCallback callback) {
        post(mContext, url, JSON, tag, params, callback, true);
    }

    //自定义contentType
    public void post(Context mContext, String url, @NonNull String contentType, int tag, RequestParams params, NetCallback callback) {
        post(mContext, url, contentType, tag, params, callback, true);
    }

    //单独请求一个地址，不添加BASE_URL
    public void postWithoutBaseUrl(Context mContext, String url, int tag, RequestParams params, NetCallback callback) {
        post(mContext, url, JSON, tag, params, callback, false);
    }

    public void postJson(final Context mContext, String url, final int tag, String params, final NetCallback callback) {
        if (url == null)
            url = "";
        LogUtil.e("url",RequstList.BASE_URL + url);
        LogUtil.e("params",params);
        RequestBody requestBody = RequestBody.create(MediaType.parse(JSON), params);
        Request request = new Request.Builder()
                .url(RequstList.BASE_URL + url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(createCallback(mContext, tag, callback,url));
    }
    public void postImage(final Context mContext,final int tag,File mFile, final NetCallback callback){
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), mFile);
        // 文件上传的请求体封装
        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("fileupload", mFile.getName(), requestBody)
                .build();
        Request request = new Request.Builder()
                .url(RequstList.BASE_URL+RequstList.UPLOAD_IMAGE)
                .post(multipartBody)
                .build();
        client.newCall(request).enqueue(createCallback(mContext, tag, callback,RequstList.UPLOAD_IMAGE));
        LogUtil.e("url",RequstList.BASE_URL+RequstList.UPLOAD_IMAGE);
    }

    private void post(final Context mContext, String url, String contentType, final int tag, RequestParams params, final NetCallback callback, boolean useBaseUrl) {
        if (url == null)
            url = "";
        RequestBody requestBody = addParams(url, params);
        Request request = new Request.Builder()
                .url(useBaseUrl ? RequstList.BASE_URL + url : url)
                //添加公共参数之后发起请求
                .post(requestBody)
                .header("Content-Type", contentType)
                .build();
        LogUtil.e("post",useBaseUrl ? RequstList.BASE_URL + url : url);
        if (params != null)
            LogUtil.e("params",params.toString());
        client.newCall(request).enqueue(createCallback(mContext, tag, callback,url));
    }
    public void get(final Context mContext, String url, String contentType, final int tag, boolean useBaseUrl,final NetCallback callback){
        if (url == null)
            url = "";
        Request request = new Request.Builder()
                .url(useBaseUrl ? RequstList.BASE_URL + url : url).get().header("Content-Type", contentType).build();
        LogUtil.e("get",useBaseUrl ? RequstList.BASE_URL + url : url);
        client.newCall(request).enqueue(createCallback(mContext, tag, callback,url));
    }

    private Callback createCallback(final Context mContext, final int tag, final NetCallback callback,final String url) {
        if (mainHandler == null)
            mainHandler = new Handler(mContext.getMainLooper());
        return new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                //切回主线程运行
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onComplete(tag);
                        //接口失败处理
                        if ( e != null &&StringUtil.isNotEmpty(e.getMessage()))
                            ToastUtil.showToast(mContext, "网络请求失败 请求接口："+url+"失败原因："+e.getMessage());
                        else
                            ToastUtil.showToast(mContext, "网络请求失败 请求接口："+url);
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String result = response.body().string();

                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onComplete(tag);
//                        LogUtil.e("result--->",result);
//                        登录失效统一处理
//                        if (result.contains("\"code\":-10,\"") ||result.contains("\"code\":-10}")){
//                            ToastUtil.showToast(mContext,mContext.getResources().getString(R.string.login_out_time));
//                            Intent intent = new Intent(mContext,LoginActivity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                            mContext.startActivity(intent);
//                            ((BaseActivity)mContext).finish();
//                        }else {
//                            try{
//                                callback.onSuccess(tag, result);
//                            }catch (Exception e){
//
//                            }
//                        }
                    }
                });

            }
        };
    }

    //参数统一处理
    private RequestBody addParams(String url, RequestParams params) {
        if (params == null)
            params = new RequestParams();
        FormBody.Builder builder = new FormBody.Builder();
        Set<String> keys = params.getKeys();
        for (String key : keys) {
            builder.add(key, params.getBody(key));
        }
        return builder.build();
    }
}
