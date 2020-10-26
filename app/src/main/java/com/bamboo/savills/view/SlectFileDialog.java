package com.bamboo.savills.view;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.bamboo.savills.R;


public class SlectFileDialog extends CustomDialog implements View.OnClickListener {
    private TextView takePic, takeVideo, selectPrivate, cancle;
    private SelectFileDialogListener listener;
    private View privateLine, publicLine;

    public SlectFileDialog(Context context, SelectFileDialogListener listener) {
        super(context, R.style.Selectfile_Dialog_style);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialog_selectfile);
        super.onCreate(savedInstanceState);
        takePic = findViewById(R.id.selectfile_takepic);
        takeVideo = findViewById(R.id.selectfile_takevideo);
        selectPrivate = findViewById(R.id.selectfile_selectprivate);
        cancle = findViewById(R.id.selectfile_cancle);
        privateLine = findViewById(R.id.selectfile_privateline);
        publicLine = findViewById(R.id.selectfile_publicline);
        takePic.setOnClickListener(this);
        takeVideo.setOnClickListener(this);
        selectPrivate.setOnClickListener(this);
        cancle.setOnClickListener(this);
    }

    @Override
    protected void onCreateView(WindowManager windowManager) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        setDialogWidth(outMetrics.widthPixels);
        setDialogHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setDialogGravity(Gravity.BOTTOM);
    }

    public void hideSelect(boolean hide) {
        selectPrivate.setVisibility(hide ? View.GONE : View.VISIBLE);
        privateLine.setVisibility(hide ? View.GONE : View.VISIBLE);
        publicLine.setVisibility(hide ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selectfile_takepic:
                //拍照
                listener.takePic();
                break;
            case R.id.selectfile_takevideo:
                //拍视频
                listener.takeVideo();
                break;
            case R.id.selectfile_selectprivate:
                //私有目录
                listener.selectPrivate();
                break;
            case R.id.selectfile_cancle:
                //取消
                break;
        }
        dismiss();
    }

    public interface SelectFileDialogListener {
        void takePic();

        void takeVideo();

        void selectPrivate();
    }
}
