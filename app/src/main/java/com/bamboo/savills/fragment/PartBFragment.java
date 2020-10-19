package com.bamboo.savills.fragment;

import android.view.View;

import com.bamboo.savills.Module.PartBInfo;
import com.bamboo.savills.Module.PartBSection;
import com.bamboo.savills.R;
import com.bamboo.savills.adapter.PartBAdapter;
import com.bamboo.savills.base.view.BaseFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.InjectView;

public class PartBFragment extends BaseFragment {
    @InjectView(R.id.swipe_part_b)
    SwipeRefreshLayout swipeRefreshLayout;

    @InjectView(R.id.rv_part_b)
    RecyclerView recyclerView;

    private List<PartBSection> mDatas = new ArrayList<>();

    private PartBAdapter adapter ;

    @Override
    public void initView() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext ,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);

    }

    @Override
    public void initListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDate();
            }
        });

    }

    @Override
    public void initData() {
        adapter = new PartBAdapter(mContext,mDatas);
        recyclerView.setAdapter(adapter);
        getDate();
    }

    private void getDate(){
        swipeRefreshLayout.setRefreshing(false);
        mDatas.clear();
        PartBInfo partBInfo1 = new PartBInfo();
        partBInfo1.setTitle("Floor Plan A");
        PartBSection partBSection1 = new PartBSection(true,partBInfo1);
        mDatas.add(partBSection1);

        PartBInfo partBInfo2 = new PartBInfo();
        partBInfo2.setTitle("BedRoom1");
        partBInfo2.setSelect(true);
        PartBSection partBSection2 = new PartBSection(false,partBInfo2);
        mDatas.add(partBSection2);

        PartBInfo partBInfo3 = new PartBInfo();
        partBInfo3.setTitle("BedRoom2");
        PartBSection partBSection3 = new PartBSection(false,partBInfo3);
        mDatas.add(partBSection3);

        PartBInfo partBInfo4 = new PartBInfo();
        partBInfo4.setTitle("Toilet");
        partBInfo4.setSelect(true);
        PartBSection partBSection4 = new PartBSection(false,partBInfo4);
        mDatas.add(partBSection4);

        PartBInfo partBInfo5 = new PartBInfo();
        partBInfo5.setTitle("Floor Plan B");
        PartBSection partBSection5 = new PartBSection(true,partBInfo5);
        mDatas.add(partBSection5);

        PartBInfo partBInfo6 = new PartBInfo();
        partBInfo6.setTitle("L/D");
        partBInfo6.setSelect(true);
        PartBSection partBSection6 = new PartBSection(false,partBInfo6);
        mDatas.add(partBSection6);

        PartBInfo partBInfo7 = new PartBInfo();
        partBInfo7.setTitle("BedRoom1");
        partBInfo7.setSelect(true);
        PartBSection partBSection7 = new PartBSection(false,partBInfo7);
        mDatas.add(partBSection7);


        PartBInfo partBInfo8 = new PartBInfo();
        partBInfo8.setTitle("BedRoom2");
        PartBSection partBSection8 = new PartBSection(false,partBInfo8);
        mDatas.add(partBSection8);

        PartBInfo partBInfo9 = new PartBInfo();
        partBInfo9.setTitle("BathRoom");
        partBInfo9.setSelect(true);
        PartBSection partBSection9 = new PartBSection(false,partBInfo9);
        mDatas.add(partBSection9);
        adapter.notifyDataSetChanged();

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_part_b;
    }

    @Override
    public void onClickNext(View v) {

    }
}
