package com.bamboo.savills.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Create by Tong on 2020/4/21/021 16:18
 */
public class AutoSizeRecyclerview extends RecyclerView {
    public AutoSizeRecyclerview(Context context) {
        super(context);
    }

    public AutoSizeRecyclerview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoSizeRecyclerview(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //Integer.MAX_VALUE:表示int类型能够表示的最大值，值为2的31次方-1
        //>>2:右移N位相当于除以2的N的幂
        //MeasureSpec.AT_MOST：子布局可以根据自己的大小选择任意大小的模式
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);
    }
}
