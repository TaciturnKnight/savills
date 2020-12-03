package com.bamboo.savills.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bamboo.savills.Module.FloorPlan;
import com.bamboo.savills.Module.PhotoVideo;
import com.bamboo.savills.R;
import com.bamboo.savills.activity.MarkActivity;
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
    public FloorPlanAdapter(Context mContext,int jobId, @Nullable List<PhotoVideo> data) {
        super(R.layout.item_floor_plan, data);
        this.mContext = mContext;
        this.jobId = jobId;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, PhotoVideo item) {
        TextView tvMark = helper.getView(R.id.tv_item_floor_plan_mark);
        int position = helper.getPosition();
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
//        switch (position){
//            case 1:
//                img.setImageResource(R.drawable.img_floor_1);
//                break;
//            case 2:
//                img.setImageResource(R.drawable.img_floor_3);
//                break;
//            case 3:
//                img.setImageResource(R.drawable.img_floor_2);
//                break;
//                default:
//                    img.setImageResource(R.drawable.img_floor_plan);
//                    break;
//        }

    }
}
