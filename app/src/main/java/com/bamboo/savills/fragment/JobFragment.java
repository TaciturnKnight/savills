package com.bamboo.savills.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bamboo.savills.Constant;
import com.bamboo.savills.Module.JobCountList;
import com.bamboo.savills.Module.JobList;
import com.bamboo.savills.Module.JobListParam;
import com.bamboo.savills.Module.JobModule;
import com.bamboo.savills.R;
import com.bamboo.savills.activity.InspectionDetailActivity;
import com.bamboo.savills.adapter.JobAdapter;
import com.bamboo.savills.base.net.HttpUtil;
import com.bamboo.savills.base.net.NetCallback;
import com.bamboo.savills.base.net.RequestParams;
import com.bamboo.savills.base.net.RequstList;
import com.bamboo.savills.base.utils.LogUtil;
import com.bamboo.savills.base.view.BaseApplication;
import com.bamboo.savills.base.view.BaseFragment;
import com.bamboo.savills.base.view.ToastUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.InjectView;

public class JobFragment extends BaseFragment {

    @InjectView(R.id.tv_welcome)
    TextView tvWelcome;

    @InjectView(R.id.et_search_job)
    EditText etSearch;

    @InjectView(R.id.ll_unassigned)
    LinearLayout llUnAssined;

    @InjectView(R.id.ll_assigned)
    LinearLayout llAssigned;

    @InjectView(R.id.ll_assign_to_me)
    LinearLayout llAssignToMe;

    @InjectView(R.id.ll_on_hold)
    LinearLayout llOnHold;

    @InjectView(R.id.ll_complete)
    LinearLayout llComplete;

    @InjectView(R.id.tv_title_unassigned)
    TextView tvUnAssigned;

    @InjectView(R.id.tv_title_assigned)
    TextView tvAssigned;

    @InjectView(R.id.tv_title_assign_to_me)
    TextView tvAssignToMe;

    @InjectView(R.id.tv_title_on_hold)
    TextView tvOnHold;

    @InjectView(R.id.tv_title_complete)
    TextView tvComplete;

    @InjectView(R.id.v_title_unassigned)
    View vUnAssigned;

    @InjectView(R.id.v_title_assigned)
    View vAssigned;

    @InjectView(R.id.v_title_assign_to_me)
    View vAssignToMe;

    @InjectView(R.id.v_title_on_hold)
    View vOnHold;

    @InjectView(R.id.v_title_complete)
    View vComplete;

    @InjectView(R.id.swipe_job)
    SwipeRefreshLayout swipeRefreshLayout;

    @InjectView(R.id.rv_job)
    RecyclerView rvJob;

    @InjectView(R.id.ll_sort_by)
    LinearLayout llSortBy;

    @InjectView(R.id.iv_sort_by)
    ImageView ivSortBy;

    private boolean isSort = false;

    private JobAdapter adapter;
    private List<JobModule> mDatas = new ArrayList<>();

    private int pageNo = 1;
    private int pageSize = 10;

    private int type = 1;
    private int lastType = 1;

    private String searchInput = "";
    private static final int MESSAGE_SEARCH = 0x1;
    private static long INTERVAL = 600; // 输入变化时间间隔

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 99:
                    pageNo = 1;
                    getJobCount();
                    getJobData();
                    break;
                case MESSAGE_SEARCH:
                    searchInput = etSearch.getText().toString().trim();
                    pageNo = 1;
                    getJobData();
                    break;
            }
        }
    };


    @Override
    public void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false);
        rvJob.setLayoutManager(manager);
        tvWelcome.setText("Hello! "+BaseApplication.userBack.getOwner().getUser().getDisplayName());
    }

    @Override
    public void initListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNo = 1;
                getJobData();

            }
        });

        llUnAssined.setOnClickListener(this);
        llAssigned.setOnClickListener(this);
        llAssignToMe.setOnClickListener(this);
        llOnHold.setOnClickListener(this);
        llComplete.setOnClickListener(this);
        llSortBy.setOnClickListener(this);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mHandler.hasMessages(MESSAGE_SEARCH)) {
                    mHandler.removeMessages(MESSAGE_SEARCH);
                }
                mHandler.sendEmptyMessageDelayed(MESSAGE_SEARCH, INTERVAL);
            }
        });
    }

    @Override
    public void initData() {
        adapter = new JobAdapter(mContext,mHandler,mDatas);
        rvJob.setAdapter(adapter);
        adapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (pageNo == 1 && mDatas.size()<pageSize){
                    // 所有数据加载完成，调用此方法
                    // 需要重置"加载完成"状态时，请调用 setNewData()
                    adapter.getLoadMoreModule().loadMoreEnd();
                    return;
                }
                pageNo ++;
                getJobData();
            }
        });
        // 当数据不满一页时，是否继续自动加载（默认为true）
        adapter.getLoadMoreModule().setEnableLoadMoreIfNotFullPage(false);

        getJobData();
        getJobCount();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (Constant.isInspectionRefresh){
            pageNo = 1;
            getJobData();
            getJobCount();
            Constant.isInspectionRefresh = false;
        }
    }

    private void getJobCount(){
        try {

            HttpUtil.getInstance().get(mContext, RequstList.JOB_LIST_NUM, HttpUtil.JSON, 101, true, new NetCallback() {
                @Override
                public void onSuccess(int tag, String result) {
                    LogUtil.e("getJobCount",result);
                    JobCountList jobCountList = new Gson().fromJson(result,new TypeToken<JobCountList>(){}.getType());
                    if (jobCountList != null && jobCountList.getCode() == 0 && jobCountList.getData() != null
                            && jobCountList.getData().size()>0){
                        for (int i = 0; i<jobCountList.getData().size();i++){
                            if ("AssignedToMe".equalsIgnoreCase(jobCountList.getData().get(i).getItem1())){
                                tvAssignToMe.setText("Assigned to Me "+jobCountList.getData().get(i).getItem2());
                            }
                            if ("Completed".equalsIgnoreCase(jobCountList.getData().get(i).getItem1())){
                                tvComplete.setText("Completed "+jobCountList.getData().get(i).getItem2());
                            }
                            if ("OnHold".equalsIgnoreCase(jobCountList.getData().get(i).getItem1())){
                                tvOnHold.setText("On Hold "+jobCountList.getData().get(i).getItem2());
                            }
                            if ("Unassigned".equalsIgnoreCase(jobCountList.getData().get(i).getItem1())){
                                tvUnAssigned.setText("Unassigned "+jobCountList.getData().get(i).getItem2());
                            }
                            if ("Accepted".equalsIgnoreCase(jobCountList.getData().get(i).getItem1())){
                                tvAssigned.setText("Accepted "+jobCountList.getData().get(i).getItem2());
                            }
                        }

                    }

                }

                @Override
                public void onError(int tag, String msg) {

                }

                @Override
                public void onComplete(int tag) {

                }
            });

        }catch (Exception e){
            LogUtil.loge("getJobCount",e.getMessage());
        }

    }
    private void getJobData(){
        try {

            if (lastType != type){
                //切换了type
                pageNo = 1;
            }
            if (pageNo ==1){
                mDatas.clear();
                adapter.notifyDataSetChanged();
                // 状态手动置为“加载中”，并且会调用加载更多监听
                // 一般情况下，不需要自己设置'加载中'状态
//            adapter.getLoadMoreModule().loadMoreToLoading();
            }
            JobListParam param = new JobListParam();
            param.setPageNumber(pageNo);
            param.setPageSize(pageSize);
            param.setSearchValue(searchInput);
            if (isSort){
                param.setOrderByColumnName("propertyName");
            }else {
                param.setOrderByColumnName("");
            }
            String json = new Gson().toJson(param,new TypeToken<JobListParam>(){}.getType());
            String path = "";
            switch (type){
                case 1:
                    path = RequstList.JOB_LIST_UNASSIGNED;
                    break;
                case 2:
                    path = RequstList.JOB_LIST_ASSIGNEDTOME;
                    break;
                case 3:
                    path = RequstList.JOB_LIST_ACCPET;
                    break;
                case 4:
                    path = RequstList.JOB_LIST_COMPLETE;
                    break;
                case 5:
                    path = RequstList.JOB_LIST_ONHOLD;
                    break;
            }

            lastType = type;

            HttpUtil.getInstance().postJson(mContext, path, 100, json, new NetCallback() {
                @Override
                public void onSuccess(int tag, String result) {
                    LogUtil.e("getJobData",result);
//                 {"code":48,"codeDesc":"UNKNOWN"}
                    JobList jobList = new Gson().fromJson(result,new TypeToken<JobList>(){}.getType());
                    if (jobList != null &&jobList.getCode() == 0&&jobList.getData()!=null && jobList.getData().getResult() != null
                            && jobList.getData().getResult().size()>0){
                        mDatas.addAll(jobList.getData().getResult());
                        if (jobList.getData().getResult().size() <pageSize){
                            adapter.getLoadMoreModule().loadMoreEnd();
                        }else {
                            adapter.getLoadMoreModule().loadMoreComplete();
                        }
                    }else {
                        adapter.getLoadMoreModule().loadMoreEnd();
                    }
                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onError(int tag, String msg) {
                    LogUtil.e("getJobData",msg);
                    // 当前这次数据加载错误，调用此方法
                    adapter.getLoadMoreModule().loadMoreFail();
                }

                @Override
                public void onComplete(int tag) {
                    LogUtil.e("getJobData",""+tag);
                    swipeRefreshLayout.setRefreshing(false);
                    adapter.getLoadMoreModule().loadMoreEnd();

                }
            });

        }catch (Exception e){
            LogUtil.loge("getJobData",e.getMessage());
        }

    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_job;
    }

    @Override
    public void onClickNext(View v) {
        switch (v.getId()){
            case R.id.ll_sort_by:
                //排序 现在没有逻辑
                isSort = !isSort;
                if (isSort){
                    ivSortBy.setImageResource(R.drawable.checkcircle_checked);

                }else {
                    ivSortBy.setImageResource(R.drawable.checkcircle_uncheck);
                }
                pageNo = 1;
                getJobData();
                break;
            case R.id.ll_unassigned:
                if (type == 1){
                    return;
                }
                tvUnAssigned.setTextColor(mContext.getResources().getColor(R.color.colorTextSelect));
                vUnAssigned.setVisibility(View.VISIBLE);

                tvAssigned.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vAssigned.setVisibility(View.GONE);

                tvAssignToMe.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vAssignToMe.setVisibility(View.GONE);

                tvOnHold.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vOnHold.setVisibility(View.GONE);

                tvComplete.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vComplete.setVisibility(View.GONE);

                adapter.setType(1);
                type = 1;
                getJobData();

                break;
            case R.id.ll_assigned:
                if (type == 3){
                    return;
                }
                tvUnAssigned.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vUnAssigned.setVisibility(View.GONE);

                tvAssigned.setTextColor(mContext.getResources().getColor(R.color.colorTextSelect));
                vAssigned.setVisibility(View.VISIBLE);

                tvAssignToMe.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vAssignToMe.setVisibility(View.GONE);

                tvOnHold.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vOnHold.setVisibility(View.GONE);

                tvComplete.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vComplete.setVisibility(View.GONE);

                adapter.setType(3);
                type = 3;
                getJobData();
                break;
            case R.id.ll_assign_to_me:
                if (type == 2){
                    return;
                }
                tvUnAssigned.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vUnAssigned.setVisibility(View.GONE);

                tvAssigned.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vAssigned.setVisibility(View.GONE);

                tvAssignToMe.setTextColor(mContext.getResources().getColor(R.color.colorTextSelect));
                vAssignToMe.setVisibility(View.VISIBLE);

                tvOnHold.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vOnHold.setVisibility(View.GONE);

                tvComplete.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vComplete.setVisibility(View.GONE);

                adapter.setType(2);
                type = 2;
                getJobData();

                break;
            case R.id.ll_on_hold:
                if (type == 5){
                    return;
                }
                tvUnAssigned.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vUnAssigned.setVisibility(View.GONE);

                tvAssigned.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vAssigned.setVisibility(View.GONE);

                tvAssignToMe.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vAssignToMe.setVisibility(View.GONE);

                tvOnHold.setTextColor(mContext.getResources().getColor(R.color.colorTextSelect));
                vOnHold.setVisibility(View.VISIBLE);

                tvComplete.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vComplete.setVisibility(View.GONE);

                adapter.setType(5);
                type = 5;
                getJobData();
                break;
            case R.id.ll_complete:
                if (type == 4){
                    return;
                }
                tvUnAssigned.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vUnAssigned.setVisibility(View.GONE);

                tvAssigned.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vAssigned.setVisibility(View.GONE);

                tvAssignToMe.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vAssignToMe.setVisibility(View.GONE);

                tvOnHold.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vOnHold.setVisibility(View.GONE);

                tvComplete.setTextColor(mContext.getResources().getColor(R.color.colorTextSelect));
                vComplete.setVisibility(View.VISIBLE);

                adapter.setType(4);
                type = 4;
                getJobData();
                break;
        }

    }
}
