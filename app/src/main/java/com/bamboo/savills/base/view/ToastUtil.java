package com.bamboo.savills.base.view;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by tong on 2019/9/29.
 */

public class ToastUtil {
    public static void showToast(Context mContext, String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
