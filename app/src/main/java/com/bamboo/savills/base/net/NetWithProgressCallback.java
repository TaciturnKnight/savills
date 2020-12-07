package com.bamboo.savills.base.net;

/**
 * Created by tong on 2020/12/7.
 */
public interface NetWithProgressCallback extends NetCallback {
    public void onProgress(int progress);
}
