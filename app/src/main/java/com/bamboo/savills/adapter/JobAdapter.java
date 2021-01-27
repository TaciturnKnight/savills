package com.bamboo.savills.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bamboo.savills.Constant;
import com.bamboo.savills.Module.JobModule;
import com.bamboo.savills.Module.SimpleResponse;
import com.bamboo.savills.R;
import com.bamboo.savills.activity.InspectionDetailActivity;
import com.bamboo.savills.base.net.HttpUtil;
import com.bamboo.savills.base.net.NetCallback;
import com.bamboo.savills.base.net.RequstList;
import com.bamboo.savills.base.utils.LogUtil;
import com.bamboo.savills.base.utils.StringUtil;
import com.bamboo.savills.base.view.ToastUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.util.List;

public class JobAdapter extends BaseQuickAdapter<JobModule,BaseViewHolder> implements LoadMoreModule {
    private int type= 1 ;//1,2,3,4
    private Context mContext;
    private Handler mHandler;
    public JobAdapter( Context mContext,Handler mHandler, List<JobModule> data) {
        super(R.layout.item_job, data);
        this.mContext = mContext;
        this.mHandler = mHandler;
    }
    public void setType(int type){
        this.type = type;
        notifyDataSetChanged();
    }
    private void setTextContent(BaseViewHolder helper,int id,String content){
        if (StringUtil.isNotEmpty(content)){
            helper.setText(id,content);
        }else {
            helper.setText(id,"--");
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, JobModule item) {
//        helper.setText(R.id.tv_item_jib_name,item.getName());
        setTextContent(helper,R.id.tv_item_job_no,item.getJob().getJobNo());
        setTextContent(helper,R.id.tv_item_job_property_name,item.getPropertyName());
        setTextContent(helper,R.id.tv_item_job_street,item.getStreet1());
        setTextContent(helper,R.id.tv_item_job_create_time,item.getCreatedOn());
        setTextContent(helper,R.id.tv_item_job_instruction_date,item.getJob().getInstructionDate());
        setTextContent(helper,R.id.tv_item_jib_inspect_date,item.getJob().getInspectionDate());
        setTextContent(helper,R.id.tv_item_job_company_name,item.getJob().getJobCompany().getName());
        setTextContent(helper,R.id.tv_item_job_client_name,item.getJob().getJobCompany().getClient().getName());
        setTextContent(helper,R.id.tv_item_job_phone_no,item.getJob().getJobCompany().getClient().getPhoneNo());
        setTextContent(helper,R.id.tv_item_job_assigned_officer,item.getInspectorAssignedOfficerUserName());
        setTextContent(helper,R.id.tv_item_jib_inspect_date_actual,item.getActualInspectionDate());
        if (item.getBuildings()!= null && item.getBuildings().size()>0&& item.getBuildings().get(0)!=null
                &&StringUtil.isNotEmpty(item.getBuildings().get(0).getBuildingName())){
            helper.setText(R.id.tv_item_job_building_name,item.getBuildings().get(0).getBuildingName());
        }else {
            helper.setText(R.id.tv_item_job_building_name,"--");
        }
        if (item.getBuildings()!= null && item.getBuildings().size()>0&& item.getBuildings().get(0)!=null
                &&item.getBuildings().get(0).getUnits() != null && item.getBuildings().get(0).getUnits().size()>0
                &&item.getBuildings().get(0).getUnits().get(0)!= null && StringUtil.isNotEmpty(item.getBuildings().get(0).getUnits().get(0).getFloor())){
            helper.setText(R.id.tv_item_job_unit_name,item.getBuildings().get(0).getUnits().get(0).getFloor());
        }else {
            helper.setText(R.id.tv_item_job_unit_name,"--");
        }

        switch (type){
            case 1:
//                UnAssigned
//                accept 显示
                helper.setVisible(R.id.tv_item_job_accept,true);
                helper.setGone(R.id.ll_item_job_assign_to_me,true);
//                helper.setText(R.id.tv_item_jib_inspect_date,"");
                helper.setGone(R.id.ll_item_jib_inspect_date_actual,true);
                helper.setText(R.id.tv_item_job_accept,"Accept");
                break;
            case 2:
//                Assign to me
                helper.setVisible(R.id.ll_item_job_assign_to_me,true);
                helper.setGone(R.id.tv_item_job_accept,true);
                helper.setGone(R.id.ll_item_jib_inspect_date_actual,true);
                break;
            case 4:
//                complete
//        都不显示
                helper.setVisible(R.id.tv_item_job_accept,true);
                helper.setGone(R.id.ll_item_job_assign_to_me,true);
                helper.setVisible(R.id.ll_item_jib_inspect_date_actual,true);
                helper.setText(R.id.tv_item_job_accept,"View");

                break;
            case 3:
//                ll显示
                // Accepted
                helper.setVisible(R.id.tv_item_job_accept,true);
                helper.setGone(R.id.ll_item_job_assign_to_me,true);
                if (item.getSiteVisitStatus() == 2){
                    helper.setText(R.id.tv_item_job_accept,"Start Inspection");
                }else {
                    helper.setText(R.id.tv_item_job_accept,"Continue Inspection");
                }

//                helper.setText(R.id.tv_item_jib_inspect_date,"10/23/2020");
                helper.setGone(R.id.ll_item_jib_inspect_date_actual,true);
                break;
            case 5:
//                onHold
                helper.setGone(R.id.tv_item_job_accept,true);
                helper.setGone(R.id.ll_item_job_assign_to_me,true);
                helper.setVisible(R.id.ll_item_jib_inspect_date_actual,true);

                break;

        }
        TextView tvUnAssign = helper.getView(R.id.tv_item_job_assign_to_me_un);
        tvUnAssign.setTag(item);
        tvUnAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JobModule item = (JobModule)v.getTag();
                unAssignJob(item);
            }
        });
        TextView tvAccept = helper.getView(R.id.tv_item_job_assign_to_me_start);
        tvAccept.setTag(item);
        tvAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JobModule item = (JobModule)v.getTag();
                acceptJob(item);
            }
        });
        TextView tvStart= helper.getView(R.id.tv_item_job_accept);
        tvStart.setTag(item);
        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JobModule item = (JobModule)v.getTag();
                if ("Start Inspection".equalsIgnoreCase(tvStart.getText().toString())){
                    //这里要通知后台
                    Constant.isInspectionRefresh = true;
                    startInspection(item);

                }
                if ("Accept".equalsIgnoreCase(tvStart.getText().toString())){
                    //accept job
                    acceptJob(item);
                }
                if ("Continue Inspection".equalsIgnoreCase(tvStart.getText().toString())){
                    Intent intent = new Intent(mContext,InspectionDetailActivity.class);
                    intent.putExtra("JobModule",new Gson().toJson(item,new TypeToken<JobModule>(){}.getType()));
                    mContext.startActivity(intent);
                }
                if ("View".equalsIgnoreCase(tvStart.getText().toString())){
                    Intent intent = new Intent(mContext,InspectionDetailActivity.class);
                    intent.putExtra("JobModule",new Gson().toJson(item,new TypeToken<JobModule>(){}.getType()));
                    intent.putExtra("View",1);
                    mContext.startActivity(intent);
                }

            }
        });

        LinearLayout llShow = helper.getView(R.id.ll_item_job_show_hide);
        LinearLayout llDetail = helper.getView(R.id.ll_item_job_detail);
        TextView tvShow = helper.getView(R.id.tv_item_job_show_hide);
        ImageView ivShow = helper.getView(R.id.iv_item_job_show_hide);
        tvShow.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        llShow.setTag(item);
        if (item.isShow()){
            llDetail.setVisibility(View.VISIBLE);
            tvShow.setText(mContext.getResources().getString(R.string.hide_detail));
            ivShow.setImageResource(R.drawable.img_arrow_up);
        }
        else{
            llDetail.setVisibility(View.GONE);
            tvShow.setText(mContext.getResources().getString(R.string.show_detail));
            ivShow.setImageResource(R.drawable.img_arrow_down);

        }
        llShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                点击事件
                JobModule jobModule = (JobModule)llShow.getTag();
                jobModule.setShow(!jobModule.isShow());
                notifyDataSetChanged();

            }
        });

    }
    private void startInspection(JobModule item){
        try{
            String path = RequstList.JOB_START_INSPECTION+item.getJobId()+"/"+item.getId();
            HttpUtil.getInstance().post(mContext, path, 104, null, new NetCallback() {
                @Override
                public void onSuccess(int tag, String result) {
                    Log.e("startInspection",result);
                    //接收成功 需要刷新数据
                    SimpleResponse response = new Gson().fromJson(result,new TypeToken<SimpleResponse>(){}.getType());
                    if (response != null && response.getCode() == 0){
                        Intent intent = new Intent(mContext,InspectionDetailActivity.class);
                        intent.putExtra("JobModule",new Gson().toJson(item,new TypeToken<JobModule>(){}.getType()));
                        mContext.startActivity(intent);
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
            LogUtil.loge("startInspection",e.getMessage());
        }
    }

    private void unAssignJob(JobModule item){

        try {

            String path = RequstList.JOB_UNASSIGN+item.getJobId()+"/"+item.getId();
            HttpUtil.getInstance().post(mContext, path, 103, null, new NetCallback() {
                @Override
                public void onSuccess(int tag, String result) {
                    Log.e("unAssignJob",result);
                    //接收成功 需要刷新数据
                    SimpleResponse response = new Gson().fromJson(result,new TypeToken<SimpleResponse>(){}.getType());
                    if (response != null && response.getCode() == 0){
                        ToastUtil.showToast(mContext,"Successfully UnAssigned");
                        mHandler.sendEmptyMessage(99);
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
            LogUtil.loge("unAssignJob",e.getMessage());
        }

    }

    private void acceptJob(JobModule item){
        try{

            String path = RequstList.JOB_ACCEPT+item.getJobId()+"/"+item.getId();
            HttpUtil.getInstance().post(mContext, path, 102, null, new NetCallback() {
                @Override
                public void onSuccess(int tag, String result) {
                    Log.e("acceptJob",result);
                    //接收成功 需要刷新数据
                    SimpleResponse response = new Gson().fromJson(result,new TypeToken<SimpleResponse>(){}.getType());
                    if (response != null && response.getCode() == 0){
                        ToastUtil.showToast(mContext,"Successfully Accepted");
                        mHandler.sendEmptyMessage(99);
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
            LogUtil.loge("acceptJob",e.getMessage());
        }


    }
}
