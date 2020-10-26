package com.bamboo.savills.activity;

import android.graphics.Color;

import cn.hzw.doodle.DoodleColor;
import cn.hzw.doodle.DoodleView;

/**
 * Created by tong on 2020/10/20.
 */
public class EditPicDrawActivity extends DoodleActivity {

    @Override
    public void onSelectColor(String color) {
        //选择颜色时调用
        mDoodle.setColor(new DoodleColor(Color.parseColor(color)));
    }

    @Override
    public void onSizeChange(int size) {
        //拖动size时调用
        mDoodle.setSize((DoodleView.DEFAULT_SIZE + size) / 10);
    }

}
