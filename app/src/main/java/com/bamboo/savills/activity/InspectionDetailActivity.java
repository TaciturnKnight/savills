package com.bamboo.savills.activity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.InjectView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bamboo.savills.Constant;
import com.bamboo.savills.Module.JobModule;
import com.bamboo.savills.Module.SimpleResponse;
import com.bamboo.savills.R;
import com.bamboo.savills.base.net.HttpUtil;
import com.bamboo.savills.base.net.NetCallback;
import com.bamboo.savills.base.net.RequstList;
import com.bamboo.savills.base.utils.LogUtil;
import com.bamboo.savills.base.utils.StringUtil;
import com.bamboo.savills.base.view.BaseActivity;
import com.bamboo.savills.base.view.ToastUtil;
import com.bamboo.savills.fragment.FloorPlanFragment;
import com.bamboo.savills.fragment.PartAFragment;
import com.bamboo.savills.fragment.PartAUpdateFragment;
import com.bamboo.savills.fragment.PartBFragment;
import com.bamboo.savills.inter.SubmitCallBack;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class InspectionDetailActivity extends BaseActivity implements SubmitCallBack {
    @InjectView(R.id.tv_title)
    TextView tvTitle;

    @InjectView(R.id.iv_back)
    ImageView ivBack;

    @InjectView(R.id.iv_send)
    ImageView ivSend;

    @InjectView(R.id.rl_part_a_out)
    RelativeLayout rlPartA;

    @InjectView(R.id.rl_floor_plan_out)
    RelativeLayout rlFloorPlan;

    @InjectView(R.id.rl_part_b_out)
    RelativeLayout rlPartB;

    @InjectView(R.id.iv_part_a)
    ImageView ivPartA;

    @InjectView(R.id.iv_part_a_ok)
    ImageView ivPartAOk;

    @InjectView(R.id.v_part_a)
    View vPartA;

    @InjectView(R.id.iv_floor_plan)
    ImageView ivFoorPlan;

    @InjectView(R.id.v_floor_plan)
    View vFloorPlan;

    @InjectView(R.id.iv_part_b)
    ImageView ivPartB;

    @InjectView(R.id.iv_part_b_ok)
    ImageView ivPartBOk;

    @InjectView(R.id.v_part_b)
    View vPartB;

    private PartAUpdateFragment partAFragment;
    private FloorPlanFragment floorPlanFragment;
    private PartBFragment partBFragment;

    private FragmentManager fragmentManager;
    private String jobModule = "";
    private JobModule mJobModule ;


    @Override
    public void initView() {
        jobModule =  getIntent().getStringExtra("JobModule");
        mJobModule = new Gson().fromJson(jobModule,new TypeToken<JobModule>(){}.getType());
        fragmentManager = getSupportFragmentManager();
        tvTitle.setText(mContext.getResources().getString(R.string.title_part_a));
        setFragmentTag(0);
        ivPartAOk.setVisibility(View.GONE);
        ivPartBOk.setVisibility(View.GONE);
    }

    @Override
    public void initListener() {
        rlPartA.setOnClickListener(this);
        rlFloorPlan.setOnClickListener(this);
        rlPartB.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        ivSend.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }
    private void setFragmentTag(int position) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideAllFragment(transaction);
        Bundle bundle = new Bundle();
        bundle.putString("JobModule",jobModule);
        switch (position) {
            case 0:
                if (partAFragment == null) {
                    partAFragment = new PartAUpdateFragment();
                    partAFragment.setArguments(bundle);
                    transaction.add(R.id.inspection_fragment, partAFragment, "partAFragment");
                } else {
                    partAFragment.setArguments(bundle);
                    transaction.show(partAFragment);
                }
                break;
            case 1:
                if (floorPlanFragment == null) {
                    floorPlanFragment = new FloorPlanFragment();
                    floorPlanFragment.setArguments(bundle);
                    transaction.add(R.id.inspection_fragment, floorPlanFragment, "floorPlanFragment");
                } else {
                    floorPlanFragment.setArguments(bundle);
                    transaction.show(floorPlanFragment);
                }
                break;
            case 2:
                if (partBFragment == null) {
                    partBFragment = new PartBFragment();
                    partBFragment.setArguments(bundle);
                    transaction.add(R.id.inspection_fragment, partBFragment, "partBFragment");
                } else {
                    partBFragment.setArguments(bundle);
                    transaction.show(partBFragment);
                }
                break;
        }
        transaction.commit();

    }
    private void hideAllFragment(FragmentTransaction transaction) {

        if (partAFragment != null) {
            transaction.hide(partAFragment);
        }
        if (floorPlanFragment != null) {
            transaction.hide(floorPlanFragment);
        }
        if (partBFragment != null) {
            transaction.hide(partBFragment);
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspection_detail;
    }

    @Override
    public void onSubmitCallBack(int type, boolean isShow) {
        switch (type){
            case 0:
                if (isShow){
                    ivPartAOk.setVisibility(View.VISIBLE);
                }else {
                    ivPartAOk.setVisibility(View.GONE);
                }
                break;
            case 1:
                if (isShow){
                    ivPartBOk.setVisibility(View.VISIBLE);
                }else {
                    ivPartBOk.setVisibility(View.GONE);
                }
                break;
        }
    }

    private void submitInspection(){
        showLoading();
        HttpUtil.getInstance().post(mContext, RequstList.SUBMIT_INSPECTION_FORM + mJobModule.getJobId() + "/" + mJobModule.getId(), 700, null, new NetCallback() {
            @Override
            public void onSuccess(int tag, String result) {
                LogUtil.loge("submitInspection",result);
                SimpleResponse simple = new Gson().fromJson(result,new TypeToken<SimpleResponse>(){}.getType());
                if (simple != null && simple.getCode() == 0){
                    //成功
                    ToastUtil.showToast(mContext,"Submitted successfully");
                    Constant.isInspectionRefresh = true;
                    finish();
                }else {
                    ToastUtil.showToast(mContext,StringUtil.isNotEmpty(simple.getCodeDesc())?simple.getCodeDesc():"Network anomaly");
                }
            }

            @Override
            public void onError(int tag, String msg) {

            }

            @Override
            public void onComplete(int tag) {
                hideLoading();
            }
        });
    }

    @Override
    public void onClickNext(View v) {
        switch (v.getId()){
            case R.id.rl_part_a_out:
                ivPartA.setImageResource(R.drawable.icon_q_a);
                vPartA.setVisibility(View.VISIBLE);

                ivFoorPlan.setImageResource(R.drawable.floor_plan_select_un);
                vFloorPlan.setVisibility(View.GONE);

                ivPartB.setImageResource(R.drawable.icon_q_b_grey);
                vPartB.setVisibility(View.GONE);

                setFragmentTag(0);
                tvTitle.setText(mContext.getResources().getString(R.string.title_part_a));

                break;
            case R.id.rl_floor_plan_out:
                ivPartA.setImageResource(R.drawable.icon_q_a_grey);
                vPartA.setVisibility(View.GONE);

                ivFoorPlan.setImageResource(R.drawable.floor_plan_select);
                vFloorPlan.setVisibility(View.VISIBLE);

                ivPartB.setImageResource(R.drawable.icon_q_b_grey);
                vPartB.setVisibility(View.GONE);

                setFragmentTag(1);
                tvTitle.setText(mContext.getResources().getString(R.string.title_floor_plan));

                break;
            case R.id.rl_part_b_out:
                ivPartA.setImageResource(R.drawable.icon_q_a_grey);
                vPartA.setVisibility(View.GONE);

                ivFoorPlan.setImageResource(R.drawable.floor_plan_select_un);
                vFloorPlan.setVisibility(View.GONE);

                ivPartB.setImageResource(R.drawable.icon_q_b);
                vPartB.setVisibility(View.VISIBLE);

                setFragmentTag(2);
                tvTitle.setText(mContext.getResources().getString(R.string.title_part_b));

                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_send:
                submitInspection();
                break;
        }

    }
}
