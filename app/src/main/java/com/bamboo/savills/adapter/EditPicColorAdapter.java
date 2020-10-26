package com.bamboo.savills.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bamboo.savills.R;
import com.bamboo.savills.view.PointView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

/**
 * Created by tong on 2020/10/20.
 */
public class EditPicColorAdapter extends BaseQuickAdapter<String, EditPicColorAdapter.EditPicColorHolder> {
    public EditPicColorAdapter() {
        super(R.layout.item_editpic_color);
    }

    @Override
    protected void convert(@NotNull EditPicColorHolder editPicColorHolder, String s) {
        editPicColorHolder.pointView.setColor(s);
    }

    public class EditPicColorHolder extends BaseViewHolder {
        PointView pointView;

        public EditPicColorHolder(@NotNull View view) {
            super(view);
            pointView = view.findViewById(R.id.item_editpic_point);
        }
    }

}
