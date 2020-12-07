package com.bamboo.savills.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.bamboo.savills.Module.FormBList;
import com.bamboo.savills.Module.PartBFormSection;
import com.bamboo.savills.R;
import com.bamboo.savills.activity.PartBQuestionActivity;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PartBAdapter extends BaseSectionQuickAdapter<PartBFormSection,BaseViewHolder> {
    private Context mContext;
    private int jobId;
    public PartBAdapter(Context mContext,int jobId, @Nullable List<PartBFormSection> data) {
        super(R.layout.item_part_b_head, R.layout.item_part_b_content, data);
        this.mContext = mContext;
        this.jobId =jobId;
    }

    @Override
    protected void convertHeader(@NotNull BaseViewHolder holder, @NotNull PartBFormSection partBSection) {
        holder.setText(R.id.tv_item_part_b_head_title,partBSection.getFormBsBean().getTitle().split("\\.")[0]);

    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, PartBFormSection partBSection) {
        holder.setText(R.id.tv_item_part_b_content_title,partBSection.getFormBsBean().getTitle());
        holder.setVisible(R.id.iv_item_part_b_content,partBSection.getFormBsBean().isIsSubmitted());
        LinearLayout llOut = holder.getView(R.id.ll_item_part_b_content_out);
        llOut.setTag(partBSection);
        llOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PartBFormSection partBSection = (PartBFormSection)v.getTag();
                Intent intent = new Intent(mContext,PartBQuestionActivity.class);
                intent.putExtra("jobId",jobId);
                intent.putExtra("formBsBean",new Gson().toJson(partBSection.getFormBsBean(),new TypeToken<FormBList.DataBean.FormBsBean>(){}.getType()));
                mContext.startActivity(intent);
            }
        });

    }
}
