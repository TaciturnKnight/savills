package com.bamboo.savills.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.bamboo.savills.Module.FloorPlan;
import com.bamboo.savills.Module.JobModule;
import com.bamboo.savills.R;
import com.bamboo.savills.activity.MainActivity;
import com.bamboo.savills.adapter.FloorPlanAdapter;
import com.bamboo.savills.base.utils.LogUtil;
import com.bamboo.savills.base.utils.StringUtil;
import com.bamboo.savills.base.view.BaseFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.InjectView;

public class FloorPlanFragment extends BaseFragment {

    @InjectView(R.id.swipe_floor_plan)
    SwipeRefreshLayout swipeRefreshLayout;

    @InjectView(R.id.rv_floor_plan)
    RecyclerView recyclerView;

    private View headView;
    private FloorPlanAdapter adapter;
    private List<FloorPlan> mDatas;

    private JobModule mJobModle;


    @Override
    public void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        headView = LayoutInflater.from(mContext).inflate(R.layout.head_view_floor_plan,null);

    }

    @Override
    public void initListener() {
        String jobModule = getArguments().getString("JobModule");
        if (StringUtil.isNotEmpty(jobModule)) {
            mJobModle = new Gson().fromJson(jobModule, new TypeToken<JobModule>() {
            }.getType());
            LogUtil.loge("JobModule",jobModule);
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });

    }

    @Override
    public void initData() {
        getData();
        adapter = new FloorPlanAdapter(mContext,mDatas);
        recyclerView.setAdapter(adapter);
        adapter.addHeaderView(headView);
    }
    private void getData(){
        swipeRefreshLayout.setRefreshing(false);
        mDatas = new ArrayList<>();
        for (int i = 0;i<4;i++){
            FloorPlan plan = new FloorPlan();
            plan.setName("Floor Plan A");
            mDatas.add(plan);
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_floor_plan;
    }

    @Override
    public void onClickNext(View v) {

    }
}
