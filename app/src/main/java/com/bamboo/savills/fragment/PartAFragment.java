package com.bamboo.savills.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bamboo.savills.Module.Answers;
import com.bamboo.savills.Module.JobModule;
import com.bamboo.savills.Module.PartAAnswerDetail;
import com.bamboo.savills.Module.PartAQuestion;
import com.bamboo.savills.R;
import com.bamboo.savills.activity.PhotoActivity;
import com.bamboo.savills.activity.PhotoShowActivity;
import com.bamboo.savills.adapter.PartAAdapter;
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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.InjectView;

public class PartAFragment extends BaseFragment {
    @InjectView(R.id.rv_part_a)
    RecyclerView recyclerView;

    @InjectView(R.id.ll_location_out)
    LinearLayout llPointOut;

    private PartAAdapter adapter;
    private List<PartAQuestion> questions;
    private View headView,footView;
    private TextView tvSave,tvComplete;
    private ImageView ivPhoto,ivPhotoShow;
    private List<ImageView> points = new ArrayList<>();

//    private List<String> titleCount = new ArrayList<>();
    private JobModule mJobModle;
    private String formId;



    @Override
    public void initView() {
        String jobModule = getArguments().getString("JobModule");
        if (StringUtil.isNotEmpty(jobModule)) {
            mJobModle = new Gson().fromJson(jobModule, new TypeToken<JobModule>() {
            }.getType());
            LogUtil.loge("JobModule",jobModule);
        }
        LinearLayoutManager manager = new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        headView = LayoutInflater.from(mContext).inflate(R.layout.head_view_part_a,null);
        LinearLayout llHeadOut = headView.findViewById(R.id.ll_head_view_part_a_out);
        ivPhoto = headView.findViewById(R.id.iv_head_view_photo);
        ivPhotoShow = headView.findViewById(R.id.iv_head_view_show);
        footView = LayoutInflater.from(mContext).inflate(R.layout.foot_view_part_a,null);
        tvSave = footView.findViewById(R.id.tv_foot_part_a_save);
        tvComplete = footView.findViewById(R.id.tv_foot_part_a_complete);


    }

    @Override
    public void initListener() {
        tvSave.setOnClickListener(this);
        tvComplete.setOnClickListener(this);
        ivPhoto.setOnClickListener(this);
        ivPhotoShow.setOnClickListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();//获取LayoutManager
                if (manager != null && manager instanceof LinearLayoutManager){
                    //第一个可见的位置
                    int firstPosition =  ((LinearLayoutManager) manager).findFirstVisibleItemPosition();
                    int lastPosition =  ((LinearLayoutManager) manager).findLastVisibleItemPosition();
                    LogUtil.e("lastPosition","firstPosition:"+firstPosition+"lastPosition:"+lastPosition);
                    if(recyclerView.canScrollVertically(1)){
//                       不是最底部
                        if (firstPosition == 0){
                            changePointsColor(firstPosition);
                        }else {
                            changePointsColor(firstPosition-1);
                        }
                    }else {
//                       最底部
                        changePointsColor(points.size()-1);
                    }
                }

            }
        });

    }

    @Override
    public void initData() {
        dealData();
        adapter = new PartAAdapter(mContext,questions);
        recyclerView.setAdapter(adapter);
        adapter.addHeaderView(headView);
        adapter.addFooterView(footView);
        getFormAData();

    }
    private void getFormAData(){
        HttpUtil.getInstance().get(mContext, RequstList.GET_FORM_A_DETAIL + mJobModle.getJobId() + "/" + mJobModle.getId(), HttpUtil.JSON, 306, true, new NetCallback() {
            @Override
            public void onSuccess(int tag, String result) {
                LogUtil.loge("getFormAData",result);
//                题目的数据也得 设置上 后期做
                PartAAnswerDetail partAAnswerDetail = new Gson().fromJson(result,new TypeToken<PartAAnswerDetail>(){}.getType());
                if (partAAnswerDetail != null && partAAnswerDetail.getCode() == 0){
                    //
                    formId = partAAnswerDetail.getData().getId();
                }
            }

            @Override
            public void onError(int tag, String msg) {
                LogUtil.loge("getFormAData-onError",msg);
            }

            @Override
            public void onComplete(int tag) {

            }
        });
    }
    private void initPoint(){
       int count =  questions.size();
        for (int i = 0 ;i<count;i++){
            View pointView = LayoutInflater.from(mContext).inflate(R.layout.item_point,null);
            TextView tvCount = pointView.findViewById(R.id.tv_item_count);
            ImageView ivPoint = pointView.findViewById(R.id.iv_item_point);
            View vPoint = pointView.findViewById(R.id.v_item_point);
            LinearLayout llPointInerOut = pointView.findViewById(R.id.ll_item_point_out);
            llPointInerOut.setTag(i);
            llPointInerOut.setOnClickListener(this);
            points.add(ivPoint);
            if (i== 0){
                ivPoint.setImageResource(R.drawable.img_yellow);
            }else {
                ivPoint.setImageResource(R.drawable.img_grey);
            }
            if (i == count-1 ){
                vPoint.setVisibility(View.INVISIBLE);
            }else {
                vPoint.setVisibility(View.VISIBLE);
            }
//            标题b
            tvCount.setText(questions.get(i).getqNo());

            llPointOut.addView(pointView);

        }

    }
    private void changePointsColor(int position){
        for (int i = 0;i<points.size();i++){
           ImageView imageView =  points.get(i);
           if (i == position){
               imageView.setImageResource(R.drawable.img_yellow);
           }else {
               imageView.setImageResource(R.drawable.img_grey);
           }
        }
    }
//    在
//    1a, 1b, 1c and 2 are mandatory
//If 1a = Industrial, then 8 & 9 &10 and mandatory
//If 1a = Retail, then 11 & 12 are mandatory
//If 1a = Carpark, then 7 is mandatory
//3, 4, 5, 6 are optional to fill in

    private void dealData(){
        questions = new ArrayList<>();
//第一题 1a
        PartAQuestion q1a = new PartAQuestion();
        q1a.setTitle("1a. Type of Building/Land Use");
        q1a.setqNo("1a");
        q1a.setType(1);
        q1a.setSimgle(false);
        q1a.setRequired(true);
        List<Answers> mAnswers = new ArrayList<>();

        Answers ans1 = new Answers();
        ans1.setAnswer("Residential");
        mAnswers.add(ans1);

        Answers ans2 = new Answers();
        ans2.setAnswer("Composite");
        mAnswers.add(ans2);

        Answers ans3 = new Answers();
        ans3.setAnswer("Industrial");
        mAnswers.add(ans3);

        Answers ans4 = new Answers();
        ans4.setAnswer("Office");
        mAnswers.add(ans4);

        Answers ans5 = new Answers();
        ans5.setAnswer("Retail");
        mAnswers.add(ans5);

        Answers ans6 = new Answers();
        ans6.setAnswer("Agricultural");
        mAnswers.add(ans6);

        Answers ans8 = new Answers();
        ans8.setAnswer("Carpark");
        mAnswers.add(ans8);

        Answers ans7 = new Answers();
        ans7.setAnswer("Others");
        ans7.setEdit(true);
        mAnswers.add(ans7);

        q1a.setAnswers(mAnswers);
        questions.add(q1a);
//第二题 1b
        PartAQuestion q1b = new PartAQuestion();
        q1b.setTitle("1b. Mode of Construction");
        q1b.setqNo("1b");
        q1b.setType(1);
        q1b.setSimgle(false);
        q1b.setRequired(true);
        List<Answers> mAnswersb = new ArrayList<>();

        Answers ans1b = new Answers();
        ans1b.setAnswer("Reinforced Concrete");
        mAnswersb.add(ans1b);

        Answers ans3b = new Answers();
        ans3b.setAnswer("Structural Steel");
        mAnswersb.add(ans3b);


        Answers ans2b = new Answers();
        ans2b.setAnswer("Others");
        ans2b.setEdit(true);
        mAnswersb.add(ans2b);

        q1b.setAnswers(mAnswersb);
        questions.add(q1b);
//第三题 1c
        PartAQuestion q1c = new PartAQuestion();
        q1c.setTitle("1c. External Wall Finished");
        q1c.setqNo("1c");
        q1c.setType(1);
        q1c.setSimgle(false);
        q1c.setRequired(true);

        List<Answers> mAnswersc = new ArrayList<>();

        Answers ans1c = new Answers();
        ans1c.setAnswer("Painted");
        mAnswersc.add(ans1c);

        Answers ans2c = new Answers();
        ans2c.setAnswer("Tiled");
        mAnswersc.add(ans2c);

        Answers ans3c = new Answers();
        ans3c.setAnswer("Mosaic-tiled");
        mAnswersc.add(ans3c);

        Answers ans4c = new Answers();
        ans4c.setAnswer("Curtain Walling");
        mAnswersc.add(ans4c);

        Answers ans5c = new Answers();
        ans5c.setAnswer("Rendering");
        mAnswersc.add(ans5c);

        Answers ans6c = new Answers();
        ans6c.setAnswer("Repairing Works");
        mAnswersc.add(ans6c);

        Answers ans7c = new Answers();
        ans7c.setAnswer("Others");
        ans7c.setEdit(true);
        mAnswersc.add(ans7c);

        q1c.setAnswers(mAnswersc);
        questions.add(q1c);
//第四题 2
        PartAQuestion q2 = new PartAQuestion();
        q2.setTitle("2. External Condition:");
        q2.setqNo("2");
        q2.setType(1);
        q2.setSimgle(true);
        q2.setRequired(true);
        List<Answers> mAnswers2 = new ArrayList<>();

        Answers ans24 = new Answers();
        ans24.setAnswer("Very Good");
        mAnswers2.add(ans24);

        Answers ans21 = new Answers();
        ans21.setAnswer("Good");
        mAnswers2.add(ans21);


        Answers ans22 = new Answers();
        ans22.setAnswer("Reasonable");
        mAnswers2.add(ans22);

        Answers ans25 = new Answers();
        ans25.setAnswer("Fair");
        mAnswers2.add(ans25);

        Answers ans23 = new Answers();
        ans23.setAnswer("Poor");
        ans23.setEdit(true);
        mAnswers2.add(ans23);

        q2.setAnswers(mAnswers2);
        questions.add(q2);
//第五题 3
        PartAQuestion q3 = new PartAQuestion();
        q3.setTitle("3. Composition");
        q3.setqNo("3");
        q3.setType(3);
        q3.setRequired(false);
        questions.add(q3);

//第六题 4
        PartAQuestion q4 = new PartAQuestion();
        q4.setTitle("4. Facilities Provided");
        q4.setqNo("4");
        q4.setType(2);
        q4.setRequired(false);
        questions.add(q4);
//第七题 5
        PartAQuestion q5 = new PartAQuestion();
        q5.setTitle("5. Lifts");
        q5.setqNo("5");
        q5.setType(4);
        q5.setRequired(false);
        questions.add(q5);
//第八题 6
        PartAQuestion q6 = new PartAQuestion();
        q6.setTitle("6. Staircase");
        q6.setqNo("6");
        q6.setType(5);
        q6.setRequired(false);
        questions.add(q6);
//第九题 7
        PartAQuestion q7 = new PartAQuestion();
        q7.setTitle("7. Car Parking Space with the Property");
        q7.setqNo("7");
        q7.setType(8);
        List<Answers> mAnswers7 = new ArrayList<>();

        Answers ans71 = new Answers();
        ans71.setAnswer("Private Car");
        mAnswers7.add(ans71);

        Answers ans72 = new Answers();
        ans72.setAnswer("Lorry");
        mAnswers7.add(ans72);

        Answers ans73 = new Answers();
        ans73.setAnswer("Covered");
        mAnswers7.add(ans73);

        Answers ans74 = new Answers();
        ans74.setAnswer("Uncovered");
        mAnswers7.add(ans74);

        q7.setAnswers(mAnswers7);
        questions.add(q7);
//第十题 8
        PartAQuestion q8 = new PartAQuestion();
        q8.setTitle("8. Air Conditioning:");
        q8.setqNo("8");
        q8.setType(1);
        q8.setSimgle(true);
        List<Answers> mAnswers8 = new ArrayList<>();

        Answers ans81 = new Answers();
        ans81.setAnswer("Central");
        mAnswers8.add(ans81);

        Answers ans82 = new Answers();
        ans82.setAnswer("Window Unit");
        mAnswers8.add(ans82);

        Answers ans83 = new Answers();
        ans83.setAnswer("Split");
        mAnswers8.add(ans83);

        Answers ans84 = new Answers();
        ans84.setAnswer("No A/C");
        mAnswers8.add(ans84);

        q8.setAnswers(mAnswers8);
        questions.add(q8);
//第十一题 9
        PartAQuestion q9 = new PartAQuestion();
        q9.setTitle("9. Fire-fighting");
        q9.setqNo("9");
        q9.setType(1);
        q9.setSimgle(false);
        List<Answers> mAnswers9 = new ArrayList<>();

        Answers ans91 = new Answers();
        ans91.setAnswer("Sprinkler");
        mAnswers9.add(ans91);

        Answers ans92 = new Answers();
        ans92.setAnswer("Hose&Reel");
        mAnswers9.add(ans92);

        Answers ans93 = new Answers();
        ans93.setAnswer("Fire Extinguisher");
        mAnswers9.add(ans93);


        q9.setAnswers(mAnswers9);
        questions.add(q9);
//第十二题 10
        PartAQuestion q10 = new PartAQuestion();
        q10.setTitle("10. Factory Features");
        q10.setqNo("10");
        q10.setType(6);
        questions.add(q10);
//第十三题 11
        PartAQuestion q11 = new PartAQuestion();
        q11.setTitle("11. Retail/Shop");
        q11.setqNo("11");
        q11.setType(7);
        questions.add(q11);
//第十四题 12
        PartAQuestion q12 = new PartAQuestion();
        q12.setTitle("12. Pedestrian Flow");
        q12.setqNo("12");
        q12.setType(1);
        q12.setSimgle(true);
        List<Answers> mAnswers12 = new ArrayList<>();

        Answers ans121 = new Answers();
        ans121.setAnswer("Heavy");
        mAnswers12.add(ans121);

        Answers ans122 = new Answers();
        ans122.setAnswer("Moderate");
        mAnswers12.add(ans122);

        Answers ans123 = new Answers();
        ans123.setAnswer("Limited");
        mAnswers12.add(ans123);


        q12.setAnswers(mAnswers12);
        questions.add(q12);
        initPoint();

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_part_a;
    }

    private void saveFormA(String partAJson){
        showLoading();

        HttpUtil.getInstance().postJson(mContext, RequstList.SAVE_FORM_A + mJobModle.getJobId(), 401, partAJson, new NetCallback() {
            @Override
            public void onSuccess(int tag, String result) {
                LogUtil.loge("saveFormA",result);
//                保存成功 不用关闭窗口
            }

            @Override
            public void onError(int tag, String msg) {
//                失败了 就存一下数据 并且 保存有数据待上传

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
            case R.id.tv_foot_part_a_save:
                String json = new Gson().toJson(questions,new TypeToken<List<PartAQuestion>>(){}.getType());
                LogUtil.e("---Questions---",json);
//                这里要把 PartAQuestion 转成 PartAAnswer 然后调接口 传给后台
                break;
            case R.id.tv_foot_part_a_complete:
                break;
            case R.id.iv_head_view_photo:
                Intent intent = new Intent(mContext,PhotoActivity.class);
                intent.putExtra("jobId",mJobModle.getJobId());
                intent.putExtra("formId",formId);
                startActivity(intent);
                break;
            case R.id.ll_item_point_out:
                int position = (int)v.getTag();
                recyclerView.scrollToPosition(position+1);
                LinearLayoutManager mLayoutManager =
                            (LinearLayoutManager) recyclerView.getLayoutManager();

                mLayoutManager.scrollToPositionWithOffset(position+1, 0);
                //变色
                changePointsColor(position);
                break;
            case R.id.iv_head_view_show:
                Intent intent1 = new Intent(mContext,PhotoShowActivity.class);
                intent1.putExtra("jobId",mJobModle.getJobId());
                intent1.putExtra("formId",formId);
                startActivity(intent1);
                break;
        }

    }
}
