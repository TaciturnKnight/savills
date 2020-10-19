package com.bamboo.savills.base.view;


import android.view.View;

/**
 * Created by qyj on 2019/9/29.
 * Activity和Fragment的共同接口
 */

public interface FrontShow {
    /**
     * 只做view相关的初始化
     */
    public abstract void initView();

    /**
     * 只做监听
     */
    public abstract void initListener();

    /**
     * 只做数据初始化
     */
    public abstract void initData();

    public abstract int getLayoutId();

    public abstract void showLoading();

    public abstract void hideLoading();

    void onClickNext(View v);

}
