package com.bamboo.savills.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bamboo.savills.Module.PhotoVideo;
import com.bamboo.savills.Module.SimpleResponse;
import com.bamboo.savills.R;
import com.bamboo.savills.activity.LoadImgsOrVideosByWebviewActivity;
import com.bamboo.savills.base.net.HttpUtil;
import com.bamboo.savills.base.net.NetCallback;
import com.bamboo.savills.base.net.RequstList;
import com.bamboo.savills.base.utils.LogUtil;
import com.bamboo.savills.base.view.ToastUtil;
import com.bamboo.savills.utils.GlideUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import java.util.List;

public class PhoteVideoAdapter extends BaseQuickAdapter<PhotoVideo,BaseViewHolder> {
    private Context mContext;
    private int jobId;
    private  Handler mHandler;

    public PhoteVideoAdapter(Context mContext, int jobId, Handler mHandler,@Nullable List<PhotoVideo> data) {
        super(R.layout.item_photo_video, data);
        this.mContext = mContext;
        this.jobId = jobId;
        this.mHandler = mHandler;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, PhotoVideo photoVideo) {
        ImageView imageView = holder.findView(R.id.iv_item_photo_video);
        RelativeLayout rlOut = holder.findView(R.id.rl_item_photo_out);
        rlOut.setTag(photoVideo);

        if (photoVideo.getFileName().endsWith("mp4")){
            holder.setVisible(R.id.iv_item_photo_video_is,true);
            holder.setVisible(R.id.iv_item_photo_video,false);
            }else {
            holder.setVisible(R.id.iv_item_photo_video,true);
            holder.setGone(R.id.iv_item_photo_video_is,true);
            LogUtil.loge("-----yyy",RequstList.BASE_URL+RequstList. GET_FORM_VIDEO_IMG+jobId+"/"+photoVideo.getId());
            GlideUtil.getInstance().showImages(mContext,RequstList.BASE_URL+RequstList. GET_FORM_VIDEO_IMG+jobId+"/"+photoVideo.getId(),imageView);
        }
        RelativeLayout rlDelete = holder.getView(R.id.rl_item_photo_delete);
        rlDelete.setTag(photoVideo);
        rlDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext()).setMessage("确认删除该文件?").setNegativeButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PhotoVideo item = (PhotoVideo)v.getTag();
                        delete(item);
                    }
                }).setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();

            }
        });
        rlOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoVideo item = (PhotoVideo)v.getTag();
                Intent intent = new Intent(getContext(), LoadImgsOrVideosByWebviewActivity.class);
                intent.putExtra("url", RequstList.BASE_URL+RequstList. GET_FORM_VIDEO_IMG+jobId+"/"+item.getId());
                intent.putExtra("ispic", !item.getFileName().endsWith("mp4"));
                intent.putExtra("fileName",item.getFileName());
                getContext().startActivity(intent);
            }
        });

    }

    private void delete(PhotoVideo photoVideo){
        try{

            String path = RequstList.DELETE_IMGS_VIDEO+jobId+"/"+photoVideo.getId();
            HttpUtil.getInstance().delete(mContext, path, 210, new NetCallback() {
                @Override
                public void onSuccess(int tag, String result) {
                    LogUtil.loge("delete",result);
//                {"code":0,"codeDesc":"OK"}
                    SimpleResponse simple = new Gson().fromJson(result,new TypeToken<SimpleResponse>(){}.getType());
                    if (simple!= null && simple.getCode() == 0){
                        // 重新刷新数据
                        mHandler.sendEmptyMessage(99);
                        ToastUtil.showToast(mContext,"Successfully Deleted");
                    }

                }

                @Override
                public void onError(int tag, String msg) {
                    LogUtil.loge("delete-onError",msg);
                }

                @Override
                public void onComplete(int tag) {

                }
            });

        }catch (Exception e){
            LogUtil.loge("delete",e.getMessage());
        }


    }
}
