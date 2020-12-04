package com.bamboo.savills.activity;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bamboo.savills.Constant;
import com.bamboo.savills.Module.PhotoVideo;
import com.bamboo.savills.Module.SimpleResponse;
import com.bamboo.savills.R;
import com.bamboo.savills.base.net.HttpUtil;
import com.bamboo.savills.base.net.NetCallback;
import com.bamboo.savills.base.net.RequstList;
import com.bamboo.savills.base.utils.LogUtil;
import com.bamboo.savills.base.view.BaseActivity;
import com.bamboo.savills.base.view.BaseApplication;
import com.bamboo.savills.base.view.ToastUtil;
import com.bamboo.savills.utils.EditPicHelper;
import com.bamboo.savills.utils.HttpClient;
import com.bamboo.savills.utils.LoanFileUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.spi.inject.Inject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.InjectView;
import cn.hzw.doodle.imagepicker.ImageLoader;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class MarkActivity extends BaseActivity {
    @InjectView(R.id.editpic_draw)
    ImageView toDraw;
    @InjectView(R.id.editpic_finish)
    TextView toSubmmit;
    @InjectView(R.id.editpic_text)
    ImageView toText;
    @InjectView(R.id.editpic_cancle)
    ImageView cancle;
    @InjectView(R.id.editpic_pic)
    ImageView pic;
    private EditPicHelper editPicHelper = EditPicHelper.getInstance();

    private int jobId;
    private PhotoVideo photoVideo;
    //处理成功的File
    private File picFile;
    private String backPath;


    public void initView() {
        editPicHelper.reset();
        jobId = getIntent().getIntExtra("jobId", 0);
        photoVideo = new Gson().fromJson(getIntent().getStringExtra("PhotoVideo"), new TypeToken<PhotoVideo>() {
        }.getType());
    }

    @Override
    public void initListener() {
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editPicHelper.bitmap != null) {
                    startActivityForResult(new Intent(mContext, EditPicTextActivity.class), 101);
                }
            }
        });
        toDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editPicHelper.bitmap != null) {
                    startActivityForResult(new Intent(mContext, EditPicDrawActivity.class), 101);
                }
            }
        });
        toSubmmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //上传
                uploadFloorPlan();
            }
        });
    }

    private void uploadFloorPlan() {
        if (picFile == null) {
            ToastUtil.showToast(mContext, "Please edit the picture first.");
            return;
        }
        showLoading();
//        重新上传Floor Plan
        HttpUtil.getInstance().updateFloorPlanImage(mContext, 212, picFile, jobId, photoVideo.getId(), photoVideo.getFileName(), new NetCallback() {
            @Override
            public void onSuccess(int tag, String result) {
                LogUtil.loge("uploadFloorPlan", result);
                SimpleResponse simple = new Gson().fromJson(result, new TypeToken<SimpleResponse>() {
                }.getType());
                if (simple.getCode() == 0) {
                    ToastUtil.showToast(mContext, "Upload Successfully");
                    //提醒floor plan 更新数据
                    Constant.isFloorPlanRefresh = true;
                    finish();

                } else {
                    ToastUtil.showToast(mContext, simple.getCodeDesc());
                }
            }

            @Override
            public void onError(int tag, String msg) {
                LogUtil.loge("uploadFloorPlan:onError", msg);
                ToastUtil.showToast(mContext, "Upload Failed");
            }

            @Override
            public void onComplete(int tag) {
                hideLoading();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            //编辑图片完成
            if (data == null) {
                return;
            }
            if (resultCode == cn.hzw.doodle.DoodleActivity.RESULT_OK) {
                //得到编辑后的图片路径
                String path = data.getStringExtra(cn.hzw.doodle.DoodleActivity.KEY_IMAGE_PATH);
                //更新图片状态
                backPath = path;
                picFile = new File(path);
                editPicHelper.setImage(picFile);
                if (TextUtils.isEmpty(path)) {
                    return;
                }
                ImageLoader.getInstance(mContext).display(pic, path);
            } else if (resultCode == cn.hzw.doodle.DoodleActivity.RESULT_ERROR) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void initPic() {
        if (editPicHelper.bitmap == null)
            editPicHelper.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_floor_plan);
    }

    @Override
    public void initData() {
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            String picUrl = RequstList.BASE_URL+"/api/v1/Job/GetFloorPlanForTest/TestFloorPlan";

            String picUrl = RequstList.BASE_URL + RequstList.SHOW_IMGS_VIDEO + jobId + "/" + photoVideo.getId();
            LogUtil.loge("url---", picUrl);
            showLoading();
            downLoadFile(picUrl, "png");
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    //失败
                    hideLoading();
                    ToastUtil.showToast(mContext, "图片加载失败" + (String) msg.obj);
                    break;
                case 3:
                    //成功
                    hideLoading();
                    File file = (File) msg.obj;
                    editPicHelper.setImage(file);
                    pic.setImageBitmap(editPicHelper.bitmap);
                    break;
            }
        }
    };

    public void downLoadFile(String url, String type) {
        Request request = new Request.Builder().url(url).addHeader("Authorization", BaseApplication.token).build();
        HttpClient.getInstance().getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendMessage(handler.obtainMessage(1, e.getMessage()));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                File file = LoanFileUtils.createNewFile(mContext, "." + type);
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
//                        sum += len;
//                        int progress = (int) (sum * 1.0f / total * 100);
//                        //更新进度条
//                        handler.sendMessage(handler.obtainMessage(2, progress));
                    }
                    fos.flush();
                    handler.sendMessage(handler.obtainMessage(3, file));
                    //下载完成
                } catch (Exception e) {
                    //下载失败
                    handler.sendMessage(handler.obtainMessage(1, e.getMessage()));
                } finally {
                    if (is != null)
                        is.close();
                    if (fos != null)
                        fos.close();
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mark;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EditPicHelper.getInstance().reset();
    }

    @Override
    public void onClickNext(View v) {

    }
}
