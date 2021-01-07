package com.bamboo.savills.base.view;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bamboo.savills.R;
import com.bamboo.savills.base.utils.SpUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;

/**
 * Created by qyj on 2019/9/29.
 */

public abstract class BaseFragment extends Fragment implements FrontShow,View.OnClickListener {
    public Context mContext;
    public LayoutInflater inflater;
    public View rootView;
    public SpUtils spUtils;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null) {
            this.mContext = mContext;
        }
        this.mContext = getContext();
        inflater = LayoutInflater.from(mContext);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.inject(this,rootView);
        spUtils = new SpUtils(mContext);
        try{
            initView();
            initListener();
            initData();
        }catch (Exception e){

        }
        return rootView;
    }

    @Override
    public void showLoading() {
        ((BaseActivity) mContext).showLoading();
    }

    @Override
    public void hideLoading() {
        ((BaseActivity) mContext).hideLoading();
    }

    @Override
    public void onClick(View v) {
        try{
            onClickNext(v);
        }catch (Exception e){

    }
    }

    public String getDate(String oldDate){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss");
            Date date = dateFormat.parse(oldDate);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return df.format(date);
        }catch (Exception e){
            return mContext.getResources().getString(R.string.string_null);
        }

    }
}
