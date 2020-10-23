package com.bamboo.savills.adapter;

import android.view.View;
import android.widget.TextView;

import com.bamboo.savills.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

/**
 * Created by tong on 2020/10/23.
 */
public class EditpicTextAdapter extends BaseQuickAdapter<String, EditpicTextAdapter.EditpicTextHolder> {

    public EditpicTextAdapter() {
        super(R.layout.item_editpictext);
    }

    @Override
    protected void convert(@NotNull EditpicTextHolder editpicTextHolder, String s) {
        editpicTextHolder.textView.setText(s);
    }

    public class EditpicTextHolder extends BaseViewHolder {
        TextView textView;

        public EditpicTextHolder(@NotNull View view) {
            super(view);
            textView = view.findViewById(R.id.item_editpictext_text);
        }
    }
}
