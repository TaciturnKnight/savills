package com.bamboo.savills.fragment;

import android.view.View;
import android.widget.TextView;

import com.bamboo.savills.R;
import com.bamboo.savills.activity.MainActivity;
import com.bamboo.savills.base.view.BaseFragment;

import butterknife.InjectView;

public class SettingFragment extends BaseFragment {
    @InjectView(R.id.tv_title)
    TextView tvTitle;

    @InjectView(R.id.tv_logout)
    TextView tvLogOut;

    @Override
    public void initView() {
        tvTitle.setText("Settings");
    }

    @Override
    public void initListener() {
        tvLogOut.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    public void onClickNext(View v) {
        switch (v.getId()){
            case R.id.tv_logout:
                ((MainActivity)getActivity()).logOut();
                break;
        }

    }
}
