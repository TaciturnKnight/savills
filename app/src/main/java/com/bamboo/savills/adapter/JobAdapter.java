package com.bamboo.savills.adapter;

import com.bamboo.savills.Module.JobModule;
import com.bamboo.savills.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;


import java.util.List;

public class JobAdapter extends BaseQuickAdapter<JobModule,BaseViewHolder> {
    private int type= 1 ;//1,2,3,4
    public JobAdapter( List<JobModule> data) {
        super(R.layout.item_job, data);
    }
    public void setType(int type){
        this.type = type;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, JobModule item) {
//        helper.setText(R.id.tv_item_jib_name,item.getName());
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

    }
}
