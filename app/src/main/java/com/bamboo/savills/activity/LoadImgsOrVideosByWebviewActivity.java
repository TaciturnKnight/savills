package com.bamboo.savills.activity;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;

import android.annotation.TargetApi;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bamboo.savills.R;
import com.bamboo.savills.base.net.HttpUtil;
import com.bamboo.savills.base.net.RequstList;
import com.bamboo.savills.base.utils.LogUtil;
import com.bamboo.savills.base.utils.OkHttpDownUtil;
import com.bamboo.savills.base.utils.StringUtil;
import com.bamboo.savills.base.view.BaseActivity;
import com.bamboo.savills.base.view.BaseApplication;
import com.bamboo.savills.base.view.ToastUtil;
import com.bamboo.savills.utils.FileDeleteUtils;
import com.bamboo.savills.utils.GlideUtil;
import com.microsoft.graph.requests.extensions.IFieldValueSetRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LoadImgsOrVideosByWebviewActivity extends BaseActivity {
    private WebView webView;
    private boolean ispic;
    private ImageView bigImage;
    private String url;
    private String path;

    private WebViewClient mWebViewClient = new WebViewClient() {

        // Handle API until level 21
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            //API 21以下，此处不考虑兼容性
            return super.shouldInterceptRequest(view, url);
        }

        // Handle API 21+
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            String url = request.getUrl().toString();
            return getNewResponse(url, request.getRequestHeaders());
        }

        private WebResourceResponse getNewResponse(String url, Map<String, String> headers) {

            try {
                OkHttpClient httpClient = new OkHttpClient();

                Request.Builder builder = new Request.Builder()
                        .url(url.trim())
                        .addHeader("Authorization", BaseApplication.token);

                Set<String> keySet = headers.keySet();
                for (String key : keySet) {
                    builder.addHeader(key, headers.get(key));
                }

                Request request = builder.build();

                final Response response = httpClient.newCall(request).execute();

                String conentType = response.header("Content-Type", response.body().contentType().type());
                String temp = conentType.toLowerCase();
                if (temp.contains("charset=utf-8")) {
                    conentType = conentType.replaceAll("(?i)" + "charset=utf-8", "");//不区分大小写的替换
                }
                if (conentType.contains(";")) {
                    conentType = conentType.replaceAll(";", "");
                    conentType = conentType.trim();
                }

                return new WebResourceResponse(
                        conentType,
                        response.header("Content-Encoding", "utf-8"),
                        response.body().byteStream()
                );

            } catch (Exception e) {
                return null;
            }

        }
    };

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 99:
                    hideLoading();
                    if (StringUtil.isNotEmpty(path))
                        webView.loadUrl("file://"+path);
                    break;
            }
        }
    };

    @Override
    public void initView() {
        webView = findViewById(R.id.loadfile_webview);
        bigImage = findViewById(R.id.loadfile_image);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        url = getIntent().getStringExtra("url");
        ispic = getIntent().getBooleanExtra("ispic", false);
        Map<String,String> header = new HashMap<>();
        header.put("Authorization",BaseApplication.token);
        webView.setWebViewClient(mWebViewClient);
        if (ispic) {
            webView.setVisibility(View.GONE);
            bigImage.setVisibility(View.VISIBLE);
            GlideUtil.getInstance().showImages(mContext,url,bigImage);
        } else {
            bigImage.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
            getVideoSource();
        }


    }

    private void getVideoSource(){
        download(url);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    public   void download( final String url){
        try {

            showLoading();
            final long startTime = System.currentTimeMillis();
            LogUtil.loge("DOWNLOAD","startTime="+startTime);
            Request request = new Request.Builder().url(url).header("Authorization", BaseApplication.token).build();
            HttpUtil.getInstance().getClient().newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    // 下载失败
                    e.printStackTrace();
                    LogUtil.loge("DOWNLOAD","download failed");
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Sink sink = null;
                    BufferedSink bufferedSink = null;
                    try {
                        String mSDCardPath= Environment.getExternalStorageDirectory().getAbsolutePath();//SD卡路径
                        String appPath= getApplicationContext().getFilesDir().getAbsolutePath();//此APP的files路径
                        LogUtil.loge("DOWNLOAD",appPath);
                        File dest = new File(appPath,url.substring(url.lastIndexOf("/") + 1)+".mp4");
                        sink = Okio.sink(dest);
                        bufferedSink = Okio.buffer(sink);


                        bufferedSink.writeAll(response.body().source());
                        LogUtil.loge("DOWNLOAD",response.body().source().toString());
                        bufferedSink.close();
                        LogUtil.loge("DOWNLOAD","download success");
                        LogUtil.loge("DOWNLOAD","totalTime="+ (System.currentTimeMillis() - startTime));
                        LogUtil.loge("DOWNLOAD",dest.getName()+"---"+dest.getAbsolutePath());
                        path = dest.getAbsolutePath();
                        mHandler.sendEmptyMessage(99);

                    } catch (Exception e) {
                        e.printStackTrace();
                        LogUtil.loge("DOWNLOAD","download failed");
                    } finally {
                        if(bufferedSink != null){
                            bufferedSink.close();
                        }
                    }
                }
            });

        }catch (Exception e){
            LogUtil.loge("download",e.getMessage());
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_loadbywebview;
    }

    @Override
    public void onClickNext(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
//        把下载过的文件删除掉
        if (StringUtil.isNotEmpty(path)){
            FileDeleteUtils.delete(path);
        }
        if (loadingDialog.isShowing()){
            loadingDialog.dismiss();
        }
    }



}