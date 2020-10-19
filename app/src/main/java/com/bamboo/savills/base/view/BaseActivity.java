package com.bamboo.savills.base.view;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;


import com.bamboo.savills.R;
import com.bamboo.savills.base.utils.SpUtils;
import com.bamboo.savills.base.utils.UtilHelpers;

import java.util.Locale;

import butterknife.ButterKnife;


/**
 * Created by qyj on 2019/9/29.
 */
public abstract class BaseActivity extends FragmentActivity implements FrontShow ,View.OnClickListener {
    public Context mContext;
    //换成自己的loading
    public Dialog loadingDialog;
    private float x1 ,x2;
    public String tag = "";
    public SpUtils spUtils;
    public View emptyView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        this.mContext = this;
        loadingDialog = new LoadingDialog(mContext);//换成自己的loading
        setContentView(getLayoutId());
        ButterKnife.inject(this);
        spUtils = new SpUtils(mContext);
        emptyView = LayoutInflater.from(mContext).inflate(R.layout.empty_view,null);
        try{
            initView();
            initListener();
            initData();
        }catch (Exception e){

        }

    }

    @Override
    public void showLoading() {
        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        try{
            onClickNext(v);
     }catch (Exception e){}
    }
    public void setLan(){
        String lan =  spUtils.getLanguage();
        // 获得res资源对象
        Resources resources = mContext.getResources();
        // 获得屏幕参数：主要是分辨率，像素等。
        DisplayMetrics metrics = resources.getDisplayMetrics();
        // 获得配置对象
//        Configuration config = resources.getConfiguration();
//        switch (lan){
//            case "1":
//                config.locale = Locale.SIMPLIFIED_CHINESE;//设置简体中文
//                BaseApplication.lanCookie = "lang=zh-cn";
//                break;
//            case "2":
//                config.locale = Locale.ENGLISH ;//设置英文
//                BaseApplication.lanCookie = "lang=en";
//                break;
//            case "3":
//                config.locale = Locale.TRADITIONAL_CHINESE;
//                BaseApplication.lanCookie = "lang=zh-hk";
//                break;
//        }
//        resources.updateConfiguration(config, metrics);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }


    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                View view = getCurrentFocus();
                UtilHelpers.hideKeyboard(ev, view, BaseActivity.this);//调用方法判断是否需要隐藏键盘
                break;

            default:
                break;
        }
        return super.dispatchTouchEvent(ev);

    }



}
