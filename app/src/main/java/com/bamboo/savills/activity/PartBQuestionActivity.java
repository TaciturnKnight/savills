package com.bamboo.savills.activity;


import android.content.BroadcastReceiver;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bamboo.savills.Module.Answers;
import com.bamboo.savills.Module.PartAQuestion;
import com.bamboo.savills.Module.PartBQuestion;
import com.bamboo.savills.R;
import com.bamboo.savills.adapter.PartBAdapter;
import com.bamboo.savills.adapter.PartBQuestionAdapter;
import com.bamboo.savills.base.utils.LogUtil;
import com.bamboo.savills.base.view.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.InjectView;

public class PartBQuestionActivity extends BaseActivity {

    @InjectView(R.id.rv_part_b_question)
    RecyclerView recyclerView;

    @InjectView(R.id.ll_location_out_part_b)
    LinearLayout llPointOut;

    private View headView,footView;
    private TextView tvQuestionTitle;

    private List<PartBQuestion> mDatas;

    private PartBQuestionAdapter adapter;

    @InjectView(R.id.tv_title)
    TextView tvTitle;

    @InjectView(R.id.iv_back)
    ImageView ivBack;

    @InjectView(R.id.iv_send)
    ImageView ivSend;

    private ImageView ivPhoto;
    private TextView tvSave,tvComplete;

    private List<ImageView> points = new ArrayList<>();

    @Override
    public void initView() {
        tvTitle.setText("Floor Plan A - BedRoom 2");
        ivSend.setVisibility(View.INVISIBLE);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext ,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        headView = LayoutInflater.from(mContext).inflate(R.layout.head_view_part_a,null);
        ivPhoto = headView.findViewById(R.id.iv_head_view_photo);
        tvQuestionTitle = headView.findViewById(R.id.tv_head_view_question_title);
        tvQuestionTitle.setText("Part B: The Subject Property");
        footView = LayoutInflater.from(mContext).inflate(R.layout.foot_view_part_a,null);
        tvSave = footView.findViewById(R.id.tv_foot_part_a_save);
        tvComplete = footView.findViewById(R.id.tv_foot_part_a_complete);

    }

    @Override
    public void initListener() {
        ivBack.setOnClickListener(this);
        ivPhoto.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        tvComplete.setOnClickListener(this);
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
        getData();
        adapter = new PartBQuestionAdapter(mContext,mDatas);
        recyclerView.setAdapter(adapter);
        adapter.addHeaderView(headView);
        adapter.addFooterView(footView);
    }
    private void initPoint(){
        int count =  mDatas.size();
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
            tvCount.setText(mDatas.get(i).getNo());
            llPointOut.addView(pointView);

        }

    }
    private void getData(){
        mDatas = new ArrayList<>();
//        第一题 1a
        PartBQuestion question1a = new PartBQuestion();
        question1a.setTitle("1a. Finishes");
        question1a.setNo("1a");
        question1a.setType(1);

        List<Answers> answers1a = new ArrayList<>();

        Answers answers1a1 = new Answers();
        answers1a1.setAnswer("Parquet");
        answers1a.add(answers1a1);

        Answers answers1a2 = new Answers();
        answers1a2.setAnswer("Carpeted");
        answers1a.add(answers1a2);

        Answers answers1a3 = new Answers();
        answers1a3.setAnswer("Vinyl-tired");
        answers1a.add(answers1a3);

        Answers answers1a4 = new Answers();
        answers1a4.setAnswer("Tiled");
        answers1a.add(answers1a4);

        Answers answers1a5 = new Answers();
        answers1a5.setAnswer("Ct.Screed");
        answers1a.add(answers1a5);

        Answers answers1a6 = new Answers();
        answers1a6.setAnswer("Marble");
        answers1a.add(answers1a6);

        Answers answers1a7 = new Answers();
        answers1a7.setAnswer("Others");
        answers1a7.setEdit(true);
        answers1a.add(answers1a7);
        question1a.setAnswers(answers1a);
        mDatas.add(question1a);
//    第2题 1b
        PartBQuestion question1b = new PartBQuestion();
        question1b.setTitle("1b. Wall");
        question1b.setNo("1b");
        question1b.setType(1);

        List<Answers> answers1b = new ArrayList<>();

        Answers answers1b1 = new Answers();
        answers1b1.setAnswer("Painted");
        answers1b.add(answers1b1);

        Answers answers1b2 = new Answers();
        answers1b2.setAnswer("Papered");
        answers1b.add(answers1b2);

        Answers answers1b3 = new Answers();
        answers1b3.setAnswer("Paneled");
        answers1b.add(answers1b3);

        Answers answers1b4 = new Answers();
        answers1b4.setAnswer("Tiled");
        answers1b.add(answers1b4);

        Answers answers1b5 = new Answers();
        answers1b5.setAnswer("Others");
        answers1b5.setEdit(true);
        answers1b.add(answers1b5);

        question1b.setAnswers(answers1b);
        mDatas.add(question1b);
//     第3题 1c
        PartBQuestion question1c = new PartBQuestion();
        question1c.setTitle("1c. Ceiling");
        question1c.setNo("1c");
        question1c.setType(1);

        List<Answers> answers1c = new ArrayList<>();

        Answers answers1c1 = new Answers();
        answers1c1.setAnswer("Painted");
        answers1c.add(answers1c1);

        Answers answers1c2 = new Answers();
        answers1c2.setAnswer("Papered");
        answers1c.add(answers1c2);

        Answers answers1c3 = new Answers();
        answers1c3.setAnswer("Suspended");
        answers1c.add(answers1c3);


        Answers answers1c4 = new Answers();
        answers1c4.setAnswer("Others");
        answers1c4.setEdit(true);
        answers1c.add(answers1c4);

        question1c.setAnswers(answers1c);
        mDatas.add(question1c);
//     第4题 2
        PartBQuestion question2 = new PartBQuestion();
        question2.setTitle("2. Internal Condition");
        question2.setNo("2");
        question2.setType(1);

        List<Answers> answers2 = new ArrayList<>();

        Answers answers21 = new Answers();
        answers21.setAnswer("Good");
        answers2.add(answers21);

        Answers answers22 = new Answers();
        answers22.setAnswer("Reasonable");
        answers2.add(answers22);


        Answers answers24 = new Answers();
        answers24.setAnswer("Poor");
        answers24.setEdit(true);
        answers2.add(answers24);

        question2.setAnswers(answers2);
        mDatas.add(question2);
//    第5题 3
        PartBQuestion question3 = new PartBQuestion();
        question3.setTitle("3. Unauthorized Structure/Alteration:");
        question3.setNo("3");
        question3.setType(2);
        mDatas.add(question3);
//    第6题 4
        PartBQuestion question4 = new PartBQuestion();
        question4.setTitle("4. View & Aspect");
        question4.setNo("4");
        question4.setType(3);
        mDatas.add(question4);
//    第7题 5
        PartBQuestion question5 = new PartBQuestion();
        question5.setTitle("5. Usage (as inspected)");
        question5.setNo("5");
        question5.setType(2);
        mDatas.add(question5);
//    第8题 6
        PartBQuestion question6 = new PartBQuestion();
        question6.setTitle("6. Ancillary Accommodation");
        question6.setNo("6");
        question6.setType(1);

        List<Answers> answers6 = new ArrayList<>();

        Answers answers61 = new Answers();
        answers61.setAnswer("Cockloft");
        answers6.add(answers61);

        Answers answers62 = new Answers();
        answers62.setAnswer("Flat Roof");
        answers6.add(answers62);

        Answers answers63 = new Answers();
        answers63.setAnswer("Garden");
        answers6.add(answers63);

        Answers answers64 = new Answers();
        answers64.setAnswer("Yard");
        answers6.add(answers64);

        Answers answers65 = new Answers();
        answers65.setAnswer("Others");
        answers65.setEdit(true);
        answers6.add(answers65);

        question6.setAnswers(answers6);
        mDatas.add(question6);
//     第9题 7
        PartBQuestion question7 = new PartBQuestion();
        question7.setTitle("7. Occupation Status");
        question7.setNo("7");
        question7.setType(4);

        List<Answers> answers7 = new ArrayList<>();

        Answers answers71 = new Answers();
        answers71.setAnswer("Owner-occupied");
        answers7.add(answers71);

        Answers answers72 = new Answers();
        answers72.setAnswer("Under decoration");
        answers7.add(answers72);

        Answers answers73 = new Answers();
        answers73.setAnswer("Vacant");
        answers7.add(answers73);

        Answers answers74 = new Answers();
        answers74.setAnswer("Tenanted*");
        answers7.add(answers74);

        Answers answers75 = new Answers();
        answers75.setAnswer("Sub-tenanted*");
        answers7.add(answers75);


        Answers answers76 = new Answers();
        answers76.setAnswer("Others*");
        answers7.add(answers76);

        question7.setAnswers(answers7);
        mDatas.add(question7);
//       第10题 8
        PartBQuestion question8 = new PartBQuestion();
        question8.setTitle("8. Remarks");
        question8.setNo("8");
        question8.setType(2);
        mDatas.add(question8);
        initPoint();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_part_bquestion;
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
    @Override
    public void onClickNext(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_head_view_photo:
                Intent intent = new Intent(mContext,PhotoActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_foot_part_a_save:
                String json = new Gson().toJson(mDatas,new TypeToken<List<PartBQuestion>>(){}.getType());
                LogUtil.e("---Questions---",json);
                break;
            case R.id.tv_foot_part_a_complete:
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
        }

    }
}
