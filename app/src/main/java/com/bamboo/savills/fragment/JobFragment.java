package com.bamboo.savills.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bamboo.savills.Module.JobModule;
import com.bamboo.savills.R;
import com.bamboo.savills.activity.InspectionDetailActivity;
import com.bamboo.savills.adapter.JobAdapter;
import com.bamboo.savills.base.view.BaseFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

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

    @InjectView(R.id.tv_title_unassigned)
    TextView tvUnAssigned;

    @InjectView(R.id.tv_title_assigned)
    TextView tvAssigned;

    @InjectView(R.id.tv_title_assign_to_me)
    TextView tvAssignToMe;

    @InjectView(R.id.tv_title_on_hold)
    TextView tvOnHold;

    @InjectView(R.id.v_title_unassigned)
    View vUnAssigned;

    @InjectView(R.id.v_title_assigned)
    View vAssigned;

    @InjectView(R.id.v_title_assign_to_me)
    View vAssignToMe;

    @InjectView(R.id.v_title_on_hold)
    View vOnHold;

    @InjectView(R.id.swipe_job)
    SwipeRefreshLayout swipeRefreshLayout;

    @InjectView(R.id.rv_job)
    RecyclerView rvJob;

    private JobAdapter adapter;
    private List<JobModule> mDatas = new ArrayList<>();

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
                getData();
            }
        });
        llUnAssined.setOnClickListener(this);
        llAssigned.setOnClickListener(this);
        llAssignToMe.setOnClickListener(this);
        llOnHold.setOnClickListener(this);
    }

    @Override
    public void initData() {
        getData();
        adapter = new JobAdapter(mDatas);
        rvJob.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Intent intent = new Intent(mContext,InspectionDetailActivity.class);
                startActivity(intent);
            }
        });

    }
    private void getData(){
        for (int i = 0;i<15;i++){
            JobModule jobModule = new JobModule();
            mDatas.add(jobModule);

        }
        swipeRefreshLayout.setRefreshing(false);
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_job;
    }

    @Override
    public void onClickNext(View v) {
        switch (v.getId()){
            case R.id.ll_unassigned:
                tvUnAssigned.setTextColor(mContext.getResources().getColor(R.color.colorTextSelect));
                vUnAssigned.setVisibility(View.VISIBLE);

                tvAssigned.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vAssigned.setVisibility(View.GONE);

                tvAssignToMe.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vAssignToMe.setVisibility(View.GONE);

                tvOnHold.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vOnHold.setVisibility(View.GONE);

                adapter.setType(1);
                break;
            case R.id.ll_assigned:
                tvUnAssigned.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vUnAssigned.setVisibility(View.GONE);

                tvAssigned.setTextColor(mContext.getResources().getColor(R.color.colorTextSelect));
                vAssigned.setVisibility(View.VISIBLE);

                tvAssignToMe.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vAssignToMe.setVisibility(View.GONE);

                tvOnHold.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vOnHold.setVisibility(View.GONE);
                adapter.setType(2);
                break;
            case R.id.ll_assign_to_me:
                tvUnAssigned.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vUnAssigned.setVisibility(View.GONE);

                tvAssigned.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vAssigned.setVisibility(View.GONE);

                tvAssignToMe.setTextColor(mContext.getResources().getColor(R.color.colorTextSelect));
                vAssignToMe.setVisibility(View.VISIBLE);

                tvOnHold.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vOnHold.setVisibility(View.GONE);
                adapter.setType(3);
                break;
            case R.id.ll_on_hold:
                tvUnAssigned.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vUnAssigned.setVisibility(View.GONE);

                tvAssigned.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vAssigned.setVisibility(View.GONE);

                tvAssignToMe.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                vAssignToMe.setVisibility(View.GONE);

                tvOnHold.setTextColor(mContext.getResources().getColor(R.color.colorTextSelect));
                vOnHold.setVisibility(View.VISIBLE);
                adapter.setType(4);
                break;
        }

    }
}
