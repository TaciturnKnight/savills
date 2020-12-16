package com.bamboo.savills.fragment;

import android.view.View;

import com.bamboo.savills.Module.FormBList;
import com.bamboo.savills.Module.JobModule;
import com.bamboo.savills.Module.PartBFormSection;
import com.bamboo.savills.R;
import com.bamboo.savills.adapter.PartBAdapter;
import com.bamboo.savills.base.net.HttpUtil;
import com.bamboo.savills.base.net.NetCallback;
import com.bamboo.savills.base.net.RequstList;
import com.bamboo.savills.base.utils.LogUtil;
import com.bamboo.savills.base.utils.StringUtil;
import com.bamboo.savills.base.view.BaseFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.InjectView;

public class PartBFragment extends BaseFragment {
    @InjectView(R.id.swipe_part_b)
    SwipeRefreshLayout swipeRefreshLayout;

    @InjectView(R.id.rv_part_b)
    RecyclerView recyclerView;

    private PartBAdapter adapter ;
    private JobModule mJobModle;
    private List<PartBFormSection> mPartBDatas = new ArrayList<>();

    @Override
    public void initView() {
        String jobModule = getArguments().getString("JobModule");
        if (StringUtil.isNotEmpty(jobModule)) {
            mJobModle = new Gson().fromJson(jobModule, new TypeToken<JobModule>() {
            }.getType());
            LogUtil.loge("JobModule",jobModule);
        }
        RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext ,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);

    }

    @Override
    public void initListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getFormBList();
            }
        });

    }

    @Override
    public void initData() {
        adapter = new PartBAdapter(mContext,mJobModle.getJobId(),mPartBDatas);
        recyclerView.setAdapter(adapter);
        getFormBList();
    }

    private void getFormBList(){
        try {

            swipeRefreshLayout.setRefreshing(false);
            HttpUtil.getInstance().get(mContext, RequstList.GET_FORM_B_LIST + mJobModle.getJobId() + "/" + mJobModle.getId(), HttpUtil.JSON, 305, true, new NetCallback() {
                @Override
                public void onSuccess(int tag, String result) {
                    LogUtil.loge("getFormBList",result);
                    mPartBDatas.clear();
                    FormBList formBList = new Gson().fromJson(result,new TypeToken<FormBList>(){}.getType());
                    //数据要处理一下
                    if (formBList != null && formBList.getData()!= null && formBList.getData().size()>0){
                        for (int i = 0;i<formBList.getData().size();i++){
                            formBList.getData().get(i);
                            FormBList.DataBean.FormBsBean formBsBean = new FormBList.DataBean.FormBsBean();
                            formBsBean.setTitle(formBList.getData().get(i).getFileName());
                            PartBFormSection partBFormSection = new PartBFormSection(formBsBean,true);
                            mPartBDatas.add(partBFormSection);
                            for (int j = 0;j<formBList.getData().get(i).getFormBs().size();j++){
                                PartBFormSection formSection = new PartBFormSection(formBList.getData().get(i).getFormBs().get(j),false);
                                mPartBDatas.add(formSection);
                            }

                        }
                    }
                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onError(int tag, String msg) {
                    LogUtil.loge("getFormBList-onError",msg);
                }

                @Override
                public void onComplete(int tag) {

                }
            });

        }catch (Exception e){
            LogUtil.loge("getFormBList",e.getMessage());
        }

    }



    @Override
    public int getLayoutId() {
        return R.layout.fragment_part_b;
    }

    @Override
    public void onClickNext(View v) {

    }
}
