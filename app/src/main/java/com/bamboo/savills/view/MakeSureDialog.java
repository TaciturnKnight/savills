package com.bamboo.savills.view;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;

import com.bamboo.savills.R;

/**
 * Created by tong on 2020/10/30.
 */
public class MakeSureDialog extends CustomDialog {
    public MakeSureDialog(Context context) {
        super(context, R.style.Selectfile_Dialog_style);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialog_makesure);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onCreateView(WindowManager windowManager) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        setDialogWidth((int) (outMetrics.widthPixels * 0.7));
        setDialogHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setDialogGravity(Gravity.CENTER);
    }
}
