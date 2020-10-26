package com.bamboo.savills.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bamboo.savills.R;
import com.bamboo.savills.base.view.BaseActivity;
import com.bamboo.savills.utils.FileUtils;
import com.bamboo.savills.utils.OnUpdateDataListener;
import com.bamboo.savills.utils.PickerManager;
import com.bamboo.savills.utils.selectfile.FileAllFragment;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class FilePickerActivity extends BaseActivity implements View.OnClickListener, OnUpdateDataListener {
    private Button btn_common, btn_all;
    private TextView tv_size, tv_confirm;
    private Fragment allFileFragment;
    private boolean isConfirm = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initEvent();
        setFragment(2);
    }

    private void initEvent() {
        btn_common.setOnClickListener(this);
        btn_all.setOnClickListener(this);
        tv_confirm.setOnClickListener(this);
    }

    public void initView() {
        btn_common = (Button) findViewById(R.id.btn_common);
        btn_all = (Button) findViewById(R.id.btn_all);
        tv_size = (TextView) findViewById(R.id.tv_size);
        tv_confirm = (TextView) findViewById(R.id.tv_confirm);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_file_picker;
    }

    @Override
    public void onClickNext(View v) {

    }

    private void setFragment(int type) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        switch (type) {
            case 1:
                break;
            case 2:
                if (allFileFragment == null) {
                    allFileFragment = FileAllFragment.newInstance();
                    ((FileAllFragment) allFileFragment).setOnUpdateDataListener(this);
                    fragmentTransaction.add(R.id.fl_content, allFileFragment);
                } else {
                    fragmentTransaction.show(allFileFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (allFileFragment != null) {
            transaction.hide(allFileFragment);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_common:
                setFragment(1);
                btn_common.setBackgroundResource(R.drawable.no_read_pressed);
                btn_common.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
                btn_all.setBackgroundResource(R.drawable.already_read);
                btn_all.setTextColor(ContextCompat.getColor(this, R.color.blue));
                break;
            case R.id.btn_all:
                setFragment(2);
                btn_common.setBackgroundResource(R.drawable.no_read);
                btn_common.setTextColor(ContextCompat.getColor(this, R.color.blue));
                btn_all.setBackgroundResource(R.drawable.already_read_pressed);
                btn_all.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
                break;
            case R.id.tv_confirm:
                isConfirm = true;
                setResult(RESULT_OK);
                finish();
                break;
        }
    }

    private long currentSize;

    @Override
    public void update(long size) {
        currentSize += size;
        tv_size.setText(getString(R.string.already_select, FileUtils.getReadableFileSize(currentSize)));
        String res = "(" + PickerManager.getInstance().files.size() + "/" + PickerManager.getInstance().maxCount + ")";
        tv_confirm.setText(getString(R.string.file_select_res, res));
    }

    @Override
    public void onBackPressed() {
        ((FileAllFragment) allFileFragment).back();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PickerManager.getInstance().files.clear();
    }
    //    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        if (!isConfirm) {
//            PickerManager.getInstance().files.clear();
//        }
//    }
}
