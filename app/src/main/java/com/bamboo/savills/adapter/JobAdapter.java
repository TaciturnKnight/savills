package com.bamboo.savills.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bamboo.savills.Module.JobModule;
import com.bamboo.savills.R;
import com.bamboo.savills.activity.InspectionDetailActivity;
import com.bamboo.savills.base.utils.StringUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;


import java.util.List;

public class JobAdapter extends BaseQuickAdapter<JobModule,BaseViewHolder> implements LoadMoreModule {
    private int type= 1 ;//1,2,3,4
    private Context mContext;
    public JobAdapter( Context mContext, List<JobModule> data) {
        super(R.layout.item_job, data);
        this.mContext = mContext;
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


        switch (type){
            case 1:
//                UnAssigned
//                accept 显示
                helper.setVisible(R.id.tv_item_job_accept,true);
                helper.setGone(R.id.ll_item_job_assign_to_me,true);
                helper.setText(R.id.tv_item_jib_inspect_date,"");
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
                helper.setGone(R.id.tv_item_job_accept,true);
                helper.setGone(R.id.ll_item_job_assign_to_me,true);
                helper.setVisible(R.id.ll_item_jib_inspect_date_actual,true);

                break;
            case 3:
//                ll显示
                // Accepted
                helper.setVisible(R.id.tv_item_job_accept,true);
                helper.setGone(R.id.ll_item_job_assign_to_me,true);
                helper.setText(R.id.tv_item_job_accept,"Start Inspection");
                helper.setText(R.id.tv_item_jib_inspect_date,"10/23/2020");
                helper.setGone(R.id.ll_item_jib_inspect_date_actual,true);
                break;
            case 5:
//                onHold
                helper.setGone(R.id.tv_item_job_accept,true);
                helper.setGone(R.id.ll_item_job_assign_to_me,true);
                helper.setVisible(R.id.ll_item_jib_inspect_date_actual,true);

                break;

        }
        TextView tvStart= helper.getView(R.id.tv_item_job_accept);
//        tvStart.setTag(item);
        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                JobModule item = (JobModule)tvStart.getTag();
                if ("Start Inspection".equalsIgnoreCase(tvStart.getText().toString())){
                    Intent intent = new Intent(mContext,InspectionDetailActivity.class);
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
//                if (jobModule.isShow()){
//                    //如果是false 此时关闭 需打开
//                    llDetail.setVisibility(View.VISIBLE);
//                    tvShow.setText(mContext.getResources().getString(R.string.hide_detail));
//                    ivShow.setImageResource(R.drawable.img_arrow_up);
//                }else {
//                    llDetail.setVisibility(View.GONE);
//                    tvShow.setText(mContext.getResources().getString(R.string.show_detail));
//                    ivShow.setImageResource(R.drawable.img_arrow_down);
//                }
                jobModule.setShow(!jobModule.isShow());
                notifyDataSetChanged();

            }
        });

    }
}
