package com.bamboo.savills.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bamboo.savills.R;
import com.bamboo.savills.base.view.BaseActivity;
import com.bamboo.savills.base.view.ToastUtil;
import com.bamboo.savills.utils.FileEntity;
import com.bamboo.savills.utils.FileUtils;
import com.bamboo.savills.utils.LoanFileUtils;
import com.bamboo.savills.utils.PickerManager;
import com.bamboo.savills.view.SlectFileDialog;


import java.io.File;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import butterknife.InjectView;

public class PhotoActivity extends BaseActivity {
    @InjectView(R.id.tv_title)
    TextView tvTitle;

    @InjectView(R.id.iv_back)
    ImageView ivBack;

    @InjectView(R.id.iv_send)
    ImageView ivSend;
    @InjectView(R.id.rl_photo_out)
    RelativeLayout picLayout;
    @InjectView(R.id.show_img)
    ImageView showImage;
    @InjectView(R.id.show_videotag)
    ImageView videoTag;

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

    }

    private Uri picUri;
    private File picFile, videoFile;
    Uri videoUri;
    File selectFile;

    @Override
    public void initListener() {
        ivBack.setOnClickListener(this);
        picLayout.setOnClickListener(new View.OnClickListener() {
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
    }

    public static Bitmap getVideoThumb(String path) {

        MediaMetadataRetriever media = new MediaMetadataRetriever();

        media.setDataSource(path);

        return media.getFrameAtTime();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == Activity.RESULT_OK) {
                switch (requestCode) {
                    case 101:
//                    Log.i("文件路径", LoanFileUtils.uri2File(this, picUri).getAbsolutePath());
                        selectFile = new File(picFile.getAbsolutePath());
                        break;
                    case 102:
                        selectFile = new File(videoFile.getAbsolutePath());
                        break;
                    case 104:
//                        ArrayList<FileEntity> files = PickerManager.getInstance().files;
//                        if (files.size() > 0) {
//                            FileEntity fileEntity = files.get(0);
//                            selectFile = fileEntity.getFile();
//                        }
                        Uri uri = data.getData();
                        selectFile = new File(LoanFileUtils.uri2File(PhotoActivity.this, uri));
                        break;
                }
                boolean isPic;
                if (selectFile.getAbsolutePath().endsWith("jpg")
                        || selectFile.getAbsolutePath().endsWith("JPEG")
                        || selectFile.getAbsolutePath().endsWith("png")) {
                    isPic = true;
                } else {
                    isPic = false;
                }
                showImage.setImageBitmap(isPic ? BitmapFactory.decodeFile(selectFile.getAbsolutePath()) : getVideoThumb(selectFile.getAbsolutePath()));
                videoTag.setVisibility(isPic ? View.GONE : View.VISIBLE);
                showImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, LoadFileByWebViewActivity.class);
                        intent.putExtra("url", selectFile.getAbsolutePath());
                        intent.putExtra("ispic", isPic);
                        startActivity(intent);
                    }
                });
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
