package com.bamboo.savills.base.net;


/**
 * Created by qyj on 2019/9/29.
 */

public interface NetCallback {
    /**
     * 请求成功并且已拿到数据
     */
    public void onSuccess(int tag, String result);

    /**
     * 请求成功但未拿到数据
     */
    public void onError(int tag, String msg);

    /**
     * 请求完成
     */
    public void onComplete(int tag);

}
