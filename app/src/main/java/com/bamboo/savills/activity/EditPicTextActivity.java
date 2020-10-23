package com.bamboo.savills.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bamboo.savills.R;

import cn.hzw.doodle.DoodleColor;
import cn.hzw.doodle.DoodlePen;
import cn.hzw.doodle.DoodleText;

/**
 * Created by tong on 2020/10/22.
 */
public class EditPicTextActivity extends DoodleActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDoodle.setPen(DoodlePen.TEXT);
        TextView seekbarTitle = findViewById(R.id.editpic_seekbartitle);
        seekbarTitle.setText("Edit");
        seekbarTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDoodleText((DoodleText) mTouchGestureListener.getSelectedItem(), -1, -1);
            }
        });
    }

    @Override
    public void onSelectColor(String color) {
        mDoodle.setColor(new DoodleColor(Color.parseColor(color)));
    }

    @Override
    public void onSizeChange(int size) {

    }
}
