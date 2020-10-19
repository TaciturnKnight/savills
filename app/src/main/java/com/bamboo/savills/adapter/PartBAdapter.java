package com.bamboo.savills.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.bamboo.savills.Module.PartBSection;
import com.bamboo.savills.R;
import com.bamboo.savills.activity.PartBQuestionActivity;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PartBAdapter extends BaseSectionQuickAdapter<PartBSection,BaseViewHolder> {
    private Context mContext;
    public PartBAdapter(Context mContext, @Nullable List<PartBSection> data) {
        super(R.layout.item_part_b_head, R.layout.item_part_b_content, data);
        this.mContext = mContext;
    }

    @Override
    protected void convertHeader(@NotNull BaseViewHolder holder, @NotNull PartBSection partBSection) {
        holder.setText(R.id.tv_item_part_b_head_title,partBSection.getInfo().getTitle());

    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, PartBSection partBSection) {
        holder.setText(R.id.tv_item_part_b_content_title,partBSection.getInfo().getTitle());
        holder.setVisible(R.id.iv_item_part_b_content,partBSection.getInfo().isSelect());
        LinearLayout llOut = holder.getView(R.id.ll_item_part_b_content_out);
        llOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,PartBQuestionActivity.class);
                mContext.startActivity(intent);
            }
        });

    }
}
