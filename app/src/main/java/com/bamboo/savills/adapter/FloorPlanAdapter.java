package com.bamboo.savills.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.bamboo.savills.Module.FloorPlan;
import com.bamboo.savills.R;
import com.bamboo.savills.activity.MarkActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FloorPlanAdapter extends BaseQuickAdapter<FloorPlan,BaseViewHolder> {
    private Context mContext;
    public FloorPlanAdapter(Context mContext, @Nullable List<FloorPlan> data) {
        super(R.layout.item_floor_plan, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, FloorPlan floorPlan) {
        TextView tvMark = helper.getView(R.id.tv_item_floor_plan_mark);
        tvMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,MarkActivity.class);
                mContext.startActivity(intent);
            }
        });

    }
}
