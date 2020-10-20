package com.bamboo.savills.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bamboo.savills.R;
import com.bamboo.savills.adapter.EditPicColorAdapter;
import com.bamboo.savills.base.view.BaseActivity;
import com.bamboo.savills.utils.EditPicHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.InjectView;

/**
 * Created by tong on 2020/10/20.
 */
public abstract class EditPicCommonActivity extends BaseActivity {
    @InjectView(R.id.editpic_undo)
    public TextView undo;
    @InjectView(R.id.editpic_done)
    public TextView done;
    @InjectView(R.id.editpic_seekbar)
    public SeekBar seekBar;
    @InjectView(R.id.editpic_color_recycler)
    public RecyclerView colorRecyclerView;

    @Override
    public void initView() {
        colorRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        EditPicColorAdapter adapter = new EditPicColorAdapter();
        colorRecyclerView.setAdapter(adapter);
        adapter.setDiffNewData(EditPicHelper.getInstance().colors);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                onSelectColor(EditPicHelper.getInstance().colors.get(position));
            }
        });
    }

    @Override
    public void initListener() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                onProgressChange(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public abstract void onProgressChange(int progress);

    public abstract void onSelectColor(String color);

    @Override
    public int getLayoutId() {
        return R.layout.activity_editpic_common;
    }

    @Override
    public void onClickNext(View v) {

    }
}
