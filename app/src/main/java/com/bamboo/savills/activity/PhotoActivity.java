package com.bamboo.savills.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bamboo.savills.Module.FileBean;
import com.bamboo.savills.R;
import com.bamboo.savills.adapter.FileListAdapter;
import com.bamboo.savills.base.view.BaseActivity;
import com.bamboo.savills.base.view.ToastUtil;
import com.bamboo.savills.utils.FileEntity;
import com.bamboo.savills.utils.FileUtils;
import com.bamboo.savills.utils.LoanFileUtils;
import com.bamboo.savills.utils.PickerManager;
import com.bamboo.savills.view.SlectFileDialog;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.InjectView;

public class PhotoActivity extends BaseActivity {
    @InjectView(R.id.tv_title)
    TextView tvTitle;

    @InjectView(R.id.iv_back)
    ImageView ivBack;

    @InjectView(R.id.iv_send)
    ImageView ivSend;
    @InjectView(R.id.showimg_recycler)
    RecyclerView recyclerView;

    @Override
    public void onClickNext(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }

    }

    @Override
    public void initView() {
        tvTitle.setText("Photo");
        ivSend.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
        fileListAdapter = new FileListAdapter();
        recyclerView.setAdapter(fileListAdapter);
        View headerView = LayoutInflater.from(mContext).inflate(R.layout.head_filerecycler, null);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出对话框
                new SlectFileDialog(mContext, new SlectFileDialog.SelectFileDialogListener() {
                    @Override
                    public void takePic() {
                        //拍照
                        picFile = LoanFileUtils.createNewFile(mContext, LoanFileUtils.FILETYPE_PICTURE);
                        picUri = LoanFileUtils.fileToUri(mContext, picFile);
                        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
                        startActivityForResult(intent, 101);
                    }

                    @Override
                    public void takeVideo() {
                        //拍视频
                        videoFile = LoanFileUtils.createNewFile(mContext, LoanFileUtils.FILETYPE_VIDEO);
                        videoUri = LoanFileUtils.fileToUri(mContext, videoFile);
                        Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
                        //设置最大拍摄时长
                        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 300);
                        startActivityForResult(intent, 102);
                    }

                    @Override
                    public void selectPrivate() {
//                        Intent intent = new Intent(mContext, FilePickerActivity.class);
//                        startActivityForResult(intent, 104);
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        //intent.setType(“image/*”);//选择图片
                        //intent.setType(“audio/*”); //选择音频
                        //intent.setType(“video/*”); //选择视频 （mp4 3gp 是android支持的视频格式）
                        intent.setType("video/*;image/*");//同时选择视频和图片
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        startActivityForResult(intent, 104);
                    }

                }).show();
            }
        });
        fileListAdapter.addHeaderView(headerView);
        View footView = LayoutInflater.from(mContext).inflate(R.layout.foot_filerecycler, null);
        fileListAdapter.addFooterView(footView);
    }

    private Uri picUri;
    private File picFile, videoFile;
    Uri videoUri;
    File selectFile;

    @Override
    public void initListener() {
        ivBack.setOnClickListener(this);
    }

    private FileListAdapter fileListAdapter;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == Activity.RESULT_OK) {
                switch (requestCode) {
                    case 101:
                        selectFile = new File(picFile.getAbsolutePath());
                        break;
                    case 102:
                        selectFile = new File(videoFile.getAbsolutePath());
                        break;
                    case 104:
                        Uri uri = data.getData();
                        selectFile = new File(LoanFileUtils.uri2File(PhotoActivity.this, uri));
                        break;
                }
                String path = selectFile.getAbsolutePath();
                String[] split = path.split("\\.");
                if (split.length < 2) {
                    ToastUtil.showToast(mContext, "只能选择图片和视频文件");
                    return;
                }
                String type = split[split.length - 1];
                if (!(type.toLowerCase().equals("jpg")
                        || type.equals("jpeg")
                        || type.equals("png")
                        || type.equals("mp4")
                        || type.equals("avi")
                        || type.equals("flv")
                )) {
                    ToastUtil.showToast(mContext, "只能选择图片和视频文件");
                    return;
                }
                FileBean fileBean = new FileBean();
                fileBean.setFilePath(selectFile.getAbsolutePath());
                fileListAdapter.addData(fileBean);
            }
        } catch (OutOfMemoryError error) {
            //内存溢出
            ToastUtil.showToast(mContext, "文件过大");
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_photo;
    }
}
