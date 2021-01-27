package com.bamboo.savills.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bamboo.savills.Module.FileBean;
import com.bamboo.savills.Module.JobModule;
import com.bamboo.savills.R;
import com.bamboo.savills.adapter.FileListAdapter;
import com.bamboo.savills.base.net.HttpUtil;
import com.bamboo.savills.base.net.NetCallback;
import com.bamboo.savills.base.net.NetWithProgressCallback;
import com.bamboo.savills.base.net.RequstList;
import com.bamboo.savills.base.utils.LogUtil;
import com.bamboo.savills.base.view.BaseActivity;
import com.bamboo.savills.base.view.LoadingDialog;
import com.bamboo.savills.base.view.ToastUtil;
import com.bamboo.savills.utils.FileEntity;
import com.bamboo.savills.utils.FileUtils;
import com.bamboo.savills.utils.LoanFileUtils;
import com.bamboo.savills.utils.PickerManager;
import com.bamboo.savills.view.SlectFileDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONException;
import org.json.JSONObject;

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

    private int jobId;
    private String formId;
    private String uploadUrl;
    private int totalCount, currentCount;
    private List<FileBean> files;

    @Override
    public void onClickNext(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_send:
//                上传 所选的图片、视频
                showLoading();
                startUpload();
                break;
        }

    }

    private void startUpload() {
        files = fileListAdapter.getData();
        totalCount = files.size();
        currentCount = 1;
        uploadFile();
    }

    private void uploadFile() {
        try {

            FileBean fileBean = files.get(currentCount - 1);
            File file = new File(fileBean.getFilePath());
            HttpUtil.getInstance().updateFormFile(mContext, uploadUrl, 101, file, file.getName(), new NetWithProgressCallback() {
                @Override
                public void onProgress(int progress) {
                    LogUtil.loge("upload", progress + "");
                    showLoading();
                    LoadingDialog progressDialog = (LoadingDialog) loadingDialog;
                    progressDialog.setTipText("Uploading..." + progress + "%(" + currentCount + "/" + totalCount + ")");
                }

                @Override
                public void onSuccess(int tag, String result) {
                    LogUtil.loge("upload", "成功" + result);
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        int code = jsonObject.getInt("code");
                        if (code == 0) {
                            //上传成功
                            if (currentCount != totalCount) {
                                currentCount++;
                                uploadFile();
                            } else {
                                hideLoading();
                                ToastUtil.showToast(mContext, "Upload Successfully");
                                finish();
                            }
                        } else {
                            //上传失败
                            ToastUtil.showToast(mContext, jsonObject.getString("codeDesc"));
                            hideLoading();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        ToastUtil.showToast(mContext, "Network Anomaly");
                        hideLoading();
                    }
                }

                @Override
                public void onError(int tag, String msg) {
                    LogUtil.loge("upload", "失败" + msg);
                    ToastUtil.showToast(mContext, "Upload fail:" + msg);
                    hideLoading();
                }

                @Override
                public void onComplete(int tag) {
                    LogUtil.loge("upload", "完成");
                }
            });

        } catch (Exception e) {
            LogUtil.loge("uploadFile", e.getMessage());
        }

    }

    @Override
    public void initView() {
        tvTitle.setText("Photo");
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
        ivSend.setOnClickListener(this);
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
        jobId = getIntent().getIntExtra("jobId", 0);
        formId = getIntent().getStringExtra("formId");
        uploadUrl = RequstList.BASE_URL + RequstList.UPLOAD_FORM_IMG_VIDEO + jobId + "/" + formId;

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_photo;
    }
}
