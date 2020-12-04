package com.bamboo.savills.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bamboo.savills.Constant;
import com.bamboo.savills.Module.PhotoVideo;
import com.bamboo.savills.R;
import com.bamboo.savills.activity.MarkActivity;
import com.bamboo.savills.base.net.HttpUtil;
import com.bamboo.savills.base.net.NetCallback;
import com.bamboo.savills.base.net.RequestParams;
import com.bamboo.savills.base.net.RequstList;
import com.bamboo.savills.base.utils.LogUtil;
import com.bamboo.savills.utils.GlideUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FloorPlanAdapter extends BaseQuickAdapter<PhotoVideo,BaseViewHolder> {
    private Context mContext;
    private int jobId;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Constant.FLOOR_PLAN_NAME_CHANGE:
                    //改名称
                    PhotoVideo item = (PhotoVideo)msg.obj;
                    changeName(item);
                    break;
            }
        }
    };
    public FloorPlanAdapter(Context mContext,int jobId, @Nullable List<PhotoVideo> data) {
        super(R.layout.item_floor_plan, data);
        this.mContext = mContext;
        this.jobId = jobId;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, PhotoVideo item) {
        TextView tvMark = helper.getView(R.id.tv_item_floor_plan_mark);
        tvMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,MarkActivity.class);
                intent.putExtra("PhotoVideo",new Gson().toJson(item,new TypeToken<PhotoVideo>(){}.getType()));
                intent.putExtra("jobId",jobId);
                mContext.startActivity(intent);
            }
        });
        ImageView img = helper.getView(R.id.iv_item_floor_plan);
        GlideUtil.getInstance().showImages(mContext,RequstList.BASE_URL+RequstList.SHOW_IMGS_VIDEO+jobId+"/"+item.getId(),img);
        LogUtil.loge("url---adapter",RequstList.BASE_URL+RequstList.SHOW_IMGS_VIDEO+jobId+"/"+item.getId());
        helper.setText(R.id.tv_item_floor_plan_name,item.getFileName().split("\\.")[0]);
        EditText etName = helper.getView(R.id.tv_item_floor_plan_name);
        etName.setTag(item);
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mHandler.hasMessages(Constant.FLOOR_PLAN_NAME_CHANGE)){
                    mHandler.removeMessages(Constant.FLOOR_PLAN_NAME_CHANGE);
                }
                Message msg = Message.obtain();
                msg.what = Constant.FLOOR_PLAN_NAME_CHANGE;
                PhotoVideo item = (PhotoVideo) etName.getTag();
                item.setNewName(etName.getText().toString().trim());
                msg.obj = item;
                mHandler.sendMessageDelayed(msg,Constant.FLOOR_PLAN_NAME_INTERNAL);

            }
        });

    }
    private void changeName(PhotoVideo photoVideo){
        RequestParams params = new RequestParams();
        params.addBody("newFileName",photoVideo.getNewName()+"."+photoVideo.getFileName().split("\\.")[1]);
        HttpUtil.getInstance().post(mContext, RequstList.CHANGE_FLOOR_PLAN_NAME + jobId + "/" + photoVideo.getId(), 301, params, new NetCallback() {
            @Override
            public void onSuccess(int tag, String result) {
                LogUtil.loge("changeName",result);
                photoVideo.setFileName(photoVideo.getNewName()+"."+photoVideo.getFileName().split("\\.")[1]);
            }

            @Override
            public void onError(int tag, String msg) {
                LogUtil.loge("changeName-onError",msg);
            }

            @Override
            public void onComplete(int tag) {

            }
        });
    }
}
