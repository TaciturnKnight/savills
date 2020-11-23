package com.bamboo.savills.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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



    @Override
    public void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false);
        rvJob.setLayoutManager(manager);
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
    }

    @Override
    public void initData() {
        adapter = new JobAdapter(mContext,mDatas);
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

    }
    private void getJobData(){
        if (lastType != type){
            //切换了type
            pageNo = 1;
        }
        if (pageNo ==1){
            mDatas.clear();
            // 状态手动置为“加载中”，并且会调用加载更多监听
            // 一般情况下，不需要自己设置'加载中'状态
//            adapter.getLoadMoreModule().loadMoreToLoading();
        }
        JobListParam param = new JobListParam();
        param.setPageNumber(pageNo);
        param.setPageSize(pageSize);
        param.setSearchValue("");
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
                if (isSort){
                    ivSortBy.setImageResource(R.drawable.checkcircle_checked);

                }else {
                    ivSortBy.setImageResource(R.drawable.checkcircle_uncheck);
                }
                isSort = !isSort;
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
