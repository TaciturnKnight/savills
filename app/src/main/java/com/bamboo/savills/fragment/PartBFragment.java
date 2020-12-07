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
    }

//    private void getDate(){
//        swipeRefreshLayout.setRefreshing(false);
//        mDatas.clear();
//        PartBInfo partBInfo1 = new PartBInfo();
//        partBInfo1.setTitle("Floor Plan A");
//        PartBSection partBSection1 = new PartBSection(true,partBInfo1);
//        mDatas.add(partBSection1);
//
//        PartBInfo partBInfo2 = new PartBInfo();
//        partBInfo2.setTitle("BedRoom1");
//        partBInfo2.setSelect(true);
//        PartBSection partBSection2 = new PartBSection(false,partBInfo2);
//        mDatas.add(partBSection2);
//
//        PartBInfo partBInfo3 = new PartBInfo();
//        partBInfo3.setTitle("BedRoom2");
//        PartBSection partBSection3 = new PartBSection(false,partBInfo3);
//        mDatas.add(partBSection3);
//
//        PartBInfo partBInfo4 = new PartBInfo();
//        partBInfo4.setTitle("Toilet");
//        partBInfo4.setSelect(true);
//        PartBSection partBSection4 = new PartBSection(false,partBInfo4);
//        mDatas.add(partBSection4);
//
//        PartBInfo partBInfo5 = new PartBInfo();
//        partBInfo5.setTitle("Floor Plan B");
//        PartBSection partBSection5 = new PartBSection(true,partBInfo5);
//        mDatas.add(partBSection5);
//
//        PartBInfo partBInfo6 = new PartBInfo();
//        partBInfo6.setTitle("L/D");
//        partBInfo6.setSelect(true);
//        PartBSection partBSection6 = new PartBSection(false,partBInfo6);
//        mDatas.add(partBSection6);
//
//        PartBInfo partBInfo7 = new PartBInfo();
//        partBInfo7.setTitle("BedRoom1");
//        partBInfo7.setSelect(true);
//        PartBSection partBSection7 = new PartBSection(false,partBInfo7);
//        mDatas.add(partBSection7);
//
//
//        PartBInfo partBInfo8 = new PartBInfo();
//        partBInfo8.setTitle("BedRoom2");
//        PartBSection partBSection8 = new PartBSection(false,partBInfo8);
//        mDatas.add(partBSection8);
//
//        PartBInfo partBInfo9 = new PartBInfo();
//        partBInfo9.setTitle("BathRoom");
//        partBInfo9.setSelect(true);
//        PartBSection partBSection9 = new PartBSection(false,partBInfo9);
//        mDatas.add(partBSection9);
//        adapter.notifyDataSetChanged();
//
//    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_part_b;
    }

    @Override
    public void onClickNext(View v) {

    }
}
