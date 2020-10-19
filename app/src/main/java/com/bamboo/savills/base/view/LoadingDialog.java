package com.bamboo.savills.base.view;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;

import com.bamboo.savills.R;

/**
 * 加载等待弹窗
 * Created by qyj on 2017/6/16.
 */

public class LoadingDialog extends CustomDialog{

    public LoadingDialog(Context context) {
        this(context, false);
    }

    public LoadingDialog(Context context, boolean dimEnabled) {
        super(context, dimEnabled);
    }

    @Override
    protected void onCreateView(WindowManager windowManager) {
        setDialogWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setDialogHeight(WindowManager.LayoutParams.MATCH_PARENT);
        setContentView(R.layout.dialog_loding);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void onClickNext(View v) {

    }
}
