package com.bamboo.savills.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bamboo.savills.R;
import com.bamboo.savills.base.utils.LogUtil;
import com.bamboo.savills.base.view.BaseActivity;
import com.bamboo.savills.base.view.BaseApplication;
import com.bamboo.savills.utils.GlideUtil;

import java.util.HashMap;
import java.util.Map;

public class LoadImgsOrVideosByWebviewActivity extends BaseActivity {
    private WebView webView;
    private boolean ispic;
    private ImageView bigImage;

    @Override
    public void initView() {
        webView = findViewById(R.id.loadfile_webview);
        bigImage = findViewById(R.id.loadfile_image);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        String url = getIntent().getStringExtra("url");
        ispic = getIntent().getBooleanExtra("ispic", false);
        Map<String,String> header = new HashMap<>();
        header.put("Authorization",BaseApplication.token);
        if (ispic) {
            webView.setVisibility(View.GONE);
            bigImage.setVisibility(View.VISIBLE);
            GlideUtil.getInstance().showImages(mContext,url,bigImage);
        } else {
            bigImage.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
            LogUtil.loge("webView",url);
            webView.loadUrl(url,header);
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

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
    }
}