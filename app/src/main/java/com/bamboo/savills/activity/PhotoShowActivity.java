package com.bamboo.savills.activity;


import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bamboo.savills.Module.JobModule;
import com.bamboo.savills.Module.PhotoVideo;
import com.bamboo.savills.Module.PhotoVideoList;
import com.bamboo.savills.R;
import com.bamboo.savills.adapter.FileListAdapter;
import com.bamboo.savills.adapter.PhoteVideoAdapter;
import com.bamboo.savills.base.net.HttpUtil;
import com.bamboo.savills.base.net.NetCallback;
import com.bamboo.savills.base.net.RequstList;
import com.bamboo.savills.base.utils.LogUtil;
import com.bamboo.savills.base.view.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.InjectView;

public class PhotoShowActivity extends BaseActivity {
    @InjectView(R.id.tv_title)
    TextView tvTitle;

    @InjectView(R.id.iv_back)
    ImageView ivBack;

    @InjectView(R.id.iv_send)
    ImageView ivSend;

    @InjectView(R.id.rv_photo_show)
    RecyclerView recyclerView;

    private JobModule mJobModule;
    private PhoteVideoAdapter adapter;
    private List<PhotoVideo> mDatas = new ArrayList<>();

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 99:
                    getImgOrVideoData();
                    break;
            }
        }
    };


    @Override
    public void initView() {
        tvTitle.setText("Photo&Video");
        ivSend.setVisibility(View.GONE);
        mJobModule =  new Gson().fromJson(getIntent().getStringExtra("JobModule"),new TypeToken<JobModule>(){}.getType());
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
        adapter = new PhoteVideoAdapter(mContext,mJobModule.getJobId(),mHandler,mDatas);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initListener() {
        ivBack.setOnClickListener(this);

    }

    @Override
    public void initData() {
        getImgOrVideoData();
    }
    private void getImgOrVideoData(){
        String path = RequstList.JOB_GET_IMGS+mJobModule.getJobId();
        HttpUtil.getInstance().get(mContext, path, HttpUtil.JSON, 200, true, new NetCallback() {
            @Override
            public void onSuccess(int tag, String result) {
                LogUtil.loge("getImgOrVideoData",result);
                PhotoVideoList photoVideoList = new Gson().fromJson(result,new TypeToken<PhotoVideoList>(){}.getType());
                if (photoVideoList!= null && photoVideoList.getData()!= null && photoVideoList.getData().size()>0){
                    mDatas.clear();
//                 fileType 是0 的拿出来
                    for(int i = 0;i<photoVideoList.getData().size();i++){
                        if (photoVideoList.getData().get(i).getFileType()== 0){
                            mDatas.add(photoVideoList.getData().get(i));
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(int tag, String msg) {

            }

            @Override
            public void onComplete(int tag) {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_photo_show;
    }

    @Override
    public void onClickNext(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
