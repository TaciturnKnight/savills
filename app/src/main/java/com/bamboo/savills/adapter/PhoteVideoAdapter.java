package com.bamboo.savills.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bamboo.savills.Module.PhotoVideo;
import com.bamboo.savills.Module.SimpleResponse;
import com.bamboo.savills.R;
import com.bamboo.savills.base.net.HttpUtil;
import com.bamboo.savills.base.net.NetCallback;
import com.bamboo.savills.base.net.RequstList;
import com.bamboo.savills.base.utils.LogUtil;
import com.bamboo.savills.base.view.ToastUtil;
import com.bamboo.savills.utils.GlideUtil;
import com.bumptech.glide.load.model.GlideUrl;
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
        GlideUtil.getInstance().showImages(mContext,RequstList.BASE_URL+RequstList.SHOW_IMGS_VIDEO+jobId+"/"+photoVideo.getId(),imageView);
        if (photoVideo.getFileName().endsWith("mp4")){
            holder.setVisible(R.id.iv_item_photo_video_is,true);
        }else {
            holder.setGone(R.id.iv_item_photo_video_is,true);
        }
        RelativeLayout rlDelete = holder.getView(R.id.rl_item_photo_delete);
        rlDelete.setTag(photoVideo);
        rlDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoVideo item = (PhotoVideo)v.getTag();
                delete(item);
            }
        });

    }
    private void delete(PhotoVideo photoVideo){
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

    }
}
