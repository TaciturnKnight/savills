package com.bamboo.savills.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bamboo.savills.Constant;
import com.bamboo.savills.Module.FloorPlan;
import com.bamboo.savills.Module.JobModule;
import com.bamboo.savills.Module.PhotoVideo;
import com.bamboo.savills.Module.PhotoVideoList;
import com.bamboo.savills.R;
import com.bamboo.savills.adapter.FloorPlanAdapter;
import com.bamboo.savills.base.net.HttpUtil;
import com.bamboo.savills.base.net.NetCallback;
import com.bamboo.savills.base.net.RequstList;
import com.bamboo.savills.base.utils.LogUtil;
import com.bamboo.savills.base.utils.StringUtil;
import com.bamboo.savills.base.view.BaseFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.InjectView;

public class FloorPlanFragment extends BaseFragment {

    @InjectView(R.id.swipe_floor_plan)
    SwipeRefreshLayout swipeRefreshLayout;

    @InjectView(R.id.rv_floor_plan)
    RecyclerView recyclerView;

    @InjectView(R.id.tv_floor_plan)
    TextView tvUpload;

    private View headView;
    private FloorPlanAdapter adapter;
    private List<PhotoVideo> mDatas = new ArrayList<>();

    private JobModule mJobModle;

    private TextView tvJobNo,tvProName,tvStreetName,tvCreateDate;




    @Override
    public void initView() {
        String jobModule = getArguments().getString("JobModule");
        if (StringUtil.isNotEmpty(jobModule)) {
            mJobModle = new Gson().fromJson(jobModule, new TypeToken<JobModule>() {
            }.getType());
            LogUtil.loge("JobModule",jobModule);
        }
        LinearLayoutManager manager = new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        headView = LayoutInflater.from(mContext).inflate(R.layout.head_view_floor_plan,null);
        tvJobNo = headView.findViewById(R.id.tv_head_view_floor_job_no);
        tvProName = headView.findViewById(R.id.tv_head_view_floor_inspector);
        tvStreetName = headView.findViewById(R.id.tv_head_view_floor_street_name);
        tvCreateDate = headView.findViewById(R.id.tv_head_view_floor_create_date);
        if (StringUtil.isNotEmpty(mJobModle.getJob().getJobNo())){
            tvJobNo.setText(mJobModle.getJob().getJobNo());
        }
        if (StringUtil.isNotEmpty(mJobModle.getPropertyName())){
            tvProName.setText(mJobModle.getPropertyName());
        }
        if (StringUtil.isNotEmpty(mJobModle.getStreet1())){
            tvStreetName.setText(mJobModle.getStreet1());
        }
        if (StringUtil.isNotEmpty(mJobModle.getCreatedOn())){
            tvCreateDate.setText(mJobModle.getCreatedOn());
        }
        tvUpload.setVisibility(View.GONE);
    }

    @Override
    public void initListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getImgOrVideoData();
            }
        });
        tvUpload.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (Constant.isFloorPlanRefresh){
            getImgOrVideoData();
            Constant.isFloorPlanRefresh = false;
        }
    }

    private void getImgOrVideoData(){
        try {

            swipeRefreshLayout.setRefreshing(false);
            mDatas.clear();
            String path = RequstList.JOB_GET_IMGS+mJobModle.getJobId();
            HttpUtil.getInstance().get(mContext, path, HttpUtil.JSON, 211, true, new NetCallback() {
                @Override
                public void onSuccess(int tag, String result) {
                    LogUtil.loge("getImgOrVideoData",result);
                    PhotoVideoList photoVideoList = new Gson().fromJson(result,new TypeToken<PhotoVideoList>(){}.getType());
                    if (photoVideoList!= null && photoVideoList.getData()!= null && photoVideoList.getData().size()>0){

//                 fileType 是0 的拿出来
                        for(int i = 0;i<photoVideoList.getData().size();i++){
                            if (photoVideoList.getData().get(i).getFileType()== 1){
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

        }catch (Exception e){
            LogUtil.loge("getImgOrVideoData",e.getMessage());
        }

    }

    @Override
    public void initData() {
        adapter = new FloorPlanAdapter(mContext,mJobModle.getJobId(),mJobModle.getId(),mDatas);
        recyclerView.setAdapter(adapter);
        adapter.addHeaderView(headView);
        getImgOrVideoData();
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_floor_plan;
    }
    @Override
    public void onClickNext(View v) {

    }
}
