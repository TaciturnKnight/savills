package com.bamboo.savills.activity;

import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bamboo.savills.R;
import com.bamboo.savills.base.view.BaseActivity;

/**
 * Created by tong on 2020/1/11.
 */
public class LoadFileByWebViewActivity extends BaseActivity {
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
        if (ispic) {
            webView.setVisibility(View.GONE);
            bigImage.setVisibility(View.VISIBLE);
            bigImage.setImageBitmap(BitmapFactory.decodeFile(url));
        } else {
            bigImage.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
            if (!url.startsWith("http")) {
                url = "file://" + url;
            }
            Log.i("加载地址", url);
            webView.loadUrl(url);
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
