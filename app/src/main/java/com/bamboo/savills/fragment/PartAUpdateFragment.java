package com.bamboo.savills.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bamboo.savills.Module.JobModule;
import com.bamboo.savills.Module.PartAAnswerDetail;
import com.bamboo.savills.R;
import com.bamboo.savills.activity.PhotoActivity;
import com.bamboo.savills.activity.PhotoShowActivity;
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

import butterknife.InjectView;

public class PartAUpdateFragment extends BaseFragment {

    @InjectView(R.id.v_fa_head)
    View headView;

    @InjectView(R.id.iv_fa_photo)
    ImageView ivPhoto;

    @InjectView(R.id.iv_fa_show)
    ImageView ivPhotoShow;

    @InjectView(R.id.ll_location_fa_out)
    LinearLayout llPoints;

    @InjectView(R.id.rl_fa_update_out)
    RelativeLayout rlPageOut;

    @InjectView(R.id.form_a_page1)
    View page1;

    @InjectView(R.id.form_a_page2)
    View page2;

    @InjectView(R.id.form_a_page3)
    View page3;

//第1a题
    @InjectView(R.id.rl_q1a_s1)
    RelativeLayout  rlq1as1;

    @InjectView(R.id.iv_q1a_s1)
    ImageView ivq1as1;

    @InjectView(R.id.tv_q1a_s1)
    TextView tvq1as1;

    @InjectView(R.id.rl_q1a_s2)
    RelativeLayout  rlq1as2;

    @InjectView(R.id.iv_q1a_s2)
    ImageView ivq1as2;

    @InjectView(R.id.tv_q1a_s2)
    TextView tvq1as2;

    @InjectView(R.id.rl_q1a_s3)
    RelativeLayout  rlq1as3;

    @InjectView(R.id.iv_q1a_s3)
    ImageView ivq1as3;

    @InjectView(R.id.tv_q1a_s3)
    TextView tvq1as3;

    @InjectView(R.id.rl_q1a_s4)
    RelativeLayout  rlq1as4;

    @InjectView(R.id.iv_q1a_s4)
    ImageView ivq1as4;

    @InjectView(R.id.tv_q1a_s4)
    TextView tvq1as4;

    @InjectView(R.id.rl_q1a_s5)
    RelativeLayout  rlq1as5;

    @InjectView(R.id.iv_q1a_s5)
    ImageView ivq1as5;

    @InjectView(R.id.tv_q1a_s5)
    TextView tvq1as5;

    @InjectView(R.id.rl_q1a_s6)
    RelativeLayout  rlq1as6;

    @InjectView(R.id.iv_q1a_s6)
    ImageView ivq1as6;

    @InjectView(R.id.tv_q1a_s6)
    TextView tvq1as6;

    @InjectView(R.id.rl_q1a_s7)
    RelativeLayout  rlq1as7;

    @InjectView(R.id.iv_q1a_s7)
    ImageView ivq1as7;

    @InjectView(R.id.tv_q1a_s7)
    TextView tvq1as7;

    @InjectView(R.id.rl_q1a_s8)
    RelativeLayout  rlq1as8;

    @InjectView(R.id.iv_q1a_s8)
    ImageView ivq1as8;

    @InjectView(R.id.tv_q1a_s8)
    TextView tvq1as8;

    @InjectView(R.id.et_q1a)
    EditText etq1a;
//    第1b题
    @InjectView(R.id.rl_q1b_s1)
    RelativeLayout  rlq1bs1;

    @InjectView(R.id.iv_q1b_s1)
    ImageView ivq1bs1;

    @InjectView(R.id.tv_q1b_s1)
    TextView tvq1bs1;

    @InjectView(R.id.rl_q1b_s2)
    RelativeLayout  rlq1bs2;

    @InjectView(R.id.iv_q1b_s2)
    ImageView ivq1bs2;

    @InjectView(R.id.tv_q1b_s2)
    TextView tvq1bs2;

    @InjectView(R.id.rl_q1b_s3)
    RelativeLayout  rlq1bs3;

    @InjectView(R.id.iv_q1b_s3)
    ImageView ivq1bs3;

    @InjectView(R.id.tv_q1b_s3)
    TextView tvq1bs3;

    @InjectView(R.id.et_q1b)
    EditText etq1b;
//    第1c 题
    @InjectView(R.id.rl_q1c_s1)
    RelativeLayout  rlq1cs1;

    @InjectView(R.id.iv_q1c_s1)
    ImageView ivq1cs1;

    @InjectView(R.id.tv_q1c_s1)
    TextView tvq1cs1;

    @InjectView(R.id.rl_q1c_s2)
    RelativeLayout  rlq1cs2;

    @InjectView(R.id.iv_q1c_s2)
    ImageView ivq1cs2;

    @InjectView(R.id.tv_q1c_s2)
    TextView tvq1cs2;

    @InjectView(R.id.rl_q1c_s3)
    RelativeLayout  rlq1cs3;

    @InjectView(R.id.iv_q1c_s3)
    ImageView ivq1cs3;

    @InjectView(R.id.tv_q1c_s3)
    TextView tvq1cs3;

    @InjectView(R.id.rl_q1c_s4)
    RelativeLayout  rlq1cs4;

    @InjectView(R.id.iv_q1c_s4)
    ImageView ivq1cs4;

    @InjectView(R.id.tv_q1c_s4)
    TextView tvq1cs4;

    @InjectView(R.id.rl_q1c_s5)
    RelativeLayout  rlq1cs5;

    @InjectView(R.id.iv_q1c_s5)
    ImageView ivq1cs5;

    @InjectView(R.id.tv_q1c_s5)
    TextView tvq1cs5;

    @InjectView(R.id.rl_q1c_s6)
    RelativeLayout  rlq1cs6;

    @InjectView(R.id.iv_q1c_s6)
    ImageView ivq1cs6;

    @InjectView(R.id.tv_q1c_s6)
    TextView tvq1cs6;

    @InjectView(R.id.rl_q1c_s7)
    RelativeLayout  rlq1cs7;

    @InjectView(R.id.iv_q1c_s7)
    ImageView ivq1cs7;

    @InjectView(R.id.tv_q1c_s7)
    TextView tvq1cs7;

    @InjectView(R.id.et_q1c)
    EditText etq1c;
//    第二题
    @InjectView(R.id.rl_q2_s1)
    RelativeLayout  rlq2s1;

    @InjectView(R.id.iv_q2_s1)
    ImageView ivq2s1;

    @InjectView(R.id.rl_q2_s2)
    RelativeLayout  rlq2s2;

    @InjectView(R.id.iv_q2_s2)
    ImageView ivq2s2;

    @InjectView(R.id.rl_q2_s3)
    RelativeLayout  rlq2s3;

    @InjectView(R.id.iv_q2_s3)
    ImageView ivq2s3;

    @InjectView(R.id.rl_q2_s4)
    RelativeLayout  rlq2s4;

    @InjectView(R.id.iv_q2_s4)
    ImageView ivq2s4;

    @InjectView(R.id.rl_q2_s5)
    RelativeLayout  rlq2s5;

    @InjectView(R.id.iv_q2_s5)
    ImageView ivq2s5;

    @InjectView(R.id.et_q2)
    EditText etq2;

    @InjectView(R.id.tv_page1_save)
    TextView tvPage1Save;

    @InjectView(R.id.tv_page1_complete)
    TextView tvPage1Next;

//    第三题
    @InjectView(R.id.et_q3_f1)
    EditText etq3f1;

    @InjectView(R.id.et_q3_f2)
    EditText etq3f2;

    @InjectView(R.id.et_q3_f3)
    EditText etq3f3;

    @InjectView(R.id.et_q3_f4)
    EditText etq3f4;

//    第四题
    @InjectView(R.id.et_q4)
    EditText etq4;

//    第五题
    @InjectView(R.id.et_q5_f1)
    EditText etq5f1;

    @InjectView(R.id.et_q5_f2)
    EditText etq5f2;

    @InjectView(R.id.et_q5_f3)
    EditText etq5f3;

//    第六题

    @InjectView(R.id.et_q6)
    EditText etq6;

//    第七题
    @InjectView(R.id.rl_q7_s1)
    RelativeLayout  rlq7s1;

    @InjectView(R.id.iv_q7_s1)
    ImageView ivq7s1;

    @InjectView(R.id.rl_q7_s2)
    RelativeLayout  rlq7s2;

    @InjectView(R.id.iv_q7_s2)
    ImageView ivq7s2;

    @InjectView(R.id.rl_q7_s3)
    RelativeLayout  rlq7s3;

    @InjectView(R.id.iv_q7_s3)
    ImageView ivq7s3;

    @InjectView(R.id.rl_q7_s4)
    RelativeLayout  rlq7s4;

    @InjectView(R.id.iv_q7_s4)
    ImageView ivq7s4;

    @InjectView(R.id.et_q7)
    EditText etq7;

    @InjectView(R.id.tv_page2_save)
    TextView tvPage2Save;

    @InjectView(R.id.tv_page2_complete)
    TextView tvPage2Next;

//    第8题

    @InjectView(R.id.rl_q8_s1)
    RelativeLayout  rlq8s1;

    @InjectView(R.id.iv_q8_s1)
    ImageView ivq8s1;

    @InjectView(R.id.rl_q8_s2)
    RelativeLayout  rlq8s2;

    @InjectView(R.id.iv_q8_s2)
    ImageView ivq8s2;

    @InjectView(R.id.rl_q8_s3)
    RelativeLayout  rlq8s3;

    @InjectView(R.id.iv_q8_s3)
    ImageView ivq8s3;

    @InjectView(R.id.rl_q8_s4)
    RelativeLayout  rlq8s4;

    @InjectView(R.id.iv_q8_s4)
    ImageView ivq8s4;

//    第九题

    @InjectView(R.id.rl_q9_s1)
    RelativeLayout  rlq9s1;

    @InjectView(R.id.iv_q9_s1)
    ImageView ivq9s1;

    @InjectView(R.id.rl_q9_s2)
    RelativeLayout  rlq9s2;

    @InjectView(R.id.iv_q9_s2)
    ImageView ivq9s2;

    @InjectView(R.id.rl_q9_s3)
    RelativeLayout  rlq9s3;

    @InjectView(R.id.iv_q9_s3)
    ImageView ivq9s3;

//    第十题

    @InjectView(R.id.et_q10_f1)
    EditText etq10f1;

    @InjectView(R.id.et_q10_f2)
    EditText etq10f2;

    @InjectView(R.id.et_q10_f3)
    EditText etq10f3;

//    第十一题

    @InjectView(R.id.et_q11_f1)
    EditText etq11f1;

    @InjectView(R.id.et_q11_f2)
    EditText etq11f2;

    @InjectView(R.id.et_q11_f3)
    EditText etq11f3;

    @InjectView(R.id.et_q11_f4)
    EditText etq11f4;

//    第十二题

    @InjectView(R.id.rl_q12_s1)
    RelativeLayout  rlq12s1;

    @InjectView(R.id.iv_q12_s1)
    ImageView ivq12s1;

    @InjectView(R.id.rl_q12_s2)
    RelativeLayout  rlq12s2;

    @InjectView(R.id.iv_q12_s2)
    ImageView ivq12s2;

    @InjectView(R.id.rl_q12_s3)
    RelativeLayout  rlq12s3;

    @InjectView(R.id.iv_q12_s3)
    ImageView ivq12s3;

    @InjectView(R.id.tv_page3_save)
    TextView tvPage3Save;

    @InjectView(R.id.tv_page3_complete)
    TextView tvPage3Complete;

    @InjectView(R.id.ll_location_fa_page1)
    LinearLayout llPoints1;

    @InjectView(R.id.ll_location_fa_page2)
    LinearLayout llPoints2;

    @InjectView(R.id.ll_location_fa_page3)
    LinearLayout llPoints3;

    private List<ImageView> ivPoints1 = new ArrayList<>();
    private List<ImageView> ivPoints2 = new ArrayList<>();
    private List<ImageView> ivPoints3 = new ArrayList<>();



    private JobModule mJobModle;
    private String formId;
    private int page = 1;//当前显示的是第几页
    private TextView tvJobNo,tvProName,tvStreetName,tvCreateDate;

    @Override
    public void initView() {
        String jobModule = getArguments().getString("JobModule");
        if (StringUtil.isNotEmpty(jobModule)) {
            mJobModle = new Gson().fromJson(jobModule, new TypeToken<JobModule>() {
            }.getType());
            LogUtil.loge("JobModule",jobModule);
        }
        tvJobNo = headView.findViewById(R.id.tv_head_view_floor_job_no);
        tvProName = headView.findViewById(R.id.tv_head_view_floor_inspector);
        tvStreetName = headView.findViewById(R.id.tv_head_view_floor_street_name);
        tvCreateDate = headView.findViewById(R.id.tv_head_view_floor_create_date);
        if (StringUtil.isNotEmpty(mJobModle.getJob().getJobNo())){
            tvJobNo.setText(mJobModle.getJob().getJobNo());
        }
        if (StringUtil.isNotEmpty(mJobModle.getPropertyName())){
            tvProName.setText(mJobModle.getPropertyName());
        }
        if (StringUtil.isNotEmpty(mJobModle.getStreet1())){
            tvStreetName.setText(mJobModle.getStreet1());
        }
        if (StringUtil.isNotEmpty(mJobModle.getCreatedOn())){
            tvCreateDate.setText(mJobModle.getCreatedOn());
        }

    }

    @Override
    public void initListener() {
        ivPhoto.setOnClickListener(this);
        ivPhotoShow.setOnClickListener(this);
        tvPage1Next.setOnClickListener(this);
        tvPage2Next.setOnClickListener(this);
        llPoints1.setOnClickListener(this);
        llPoints2.setOnClickListener(this);
        llPoints3.setOnClickListener(this);
    }


    @Override
    public void initData() {
        initPoint();
        getFormAData();
    }
    private void initPoint(){
//        分成三部分小圆点
        for (int i = 0;i<4;i++){
            View pointView = LayoutInflater.from(mContext).inflate(R.layout.item_point,null);
            TextView tvCount = pointView.findViewById(R.id.tv_item_count);
            ImageView ivPoint = pointView.findViewById(R.id.iv_item_point);
            View vPoint = pointView.findViewById(R.id.v_item_point);
            ivPoint.setImageResource(R.drawable.img_yellow);
            ivPoints1.add(ivPoint);
            String title = "1a";
            switch (i){
                case 0:
                    title = "1a";
                    break;
                case 1:
                    title = "1b";
                    break;
                case 2:
                    title = "1c";
                    break;
                case 3:
                    title = "2";
                    break;
            }
            tvCount.setText(title);
            llPoints1.addView(pointView);
        }
        for (int i = 0;i<5;i++){
            View pointView = LayoutInflater.from(mContext).inflate(R.layout.item_point,null);
            TextView tvCount = pointView.findViewById(R.id.tv_item_count);
            ImageView ivPoint = pointView.findViewById(R.id.iv_item_point);
            View vPoint = pointView.findViewById(R.id.v_item_point);
            ivPoint.setImageResource(R.drawable.img_grey);
            ivPoints2.add(ivPoint);
            String title = "1a";
            switch (i){
                case 0:
                    title = "3";
                    break;
                case 1:
                    title = "4";
                    break;
                case 2:
                    title = "5";
                    break;
                case 3:
                    title = "6";
                    break;
                case 4:
                    title = "7";
                    break;
            }
            tvCount.setText(title);
            llPoints2.addView(pointView);
        }
        for (int i = 0;i<5;i++){
            View pointView = LayoutInflater.from(mContext).inflate(R.layout.item_point,null);
            TextView tvCount = pointView.findViewById(R.id.tv_item_count);
            ImageView ivPoint = pointView.findViewById(R.id.iv_item_point);
            View vPoint = pointView.findViewById(R.id.v_item_point);
            ivPoint.setImageResource(R.drawable.img_grey);
            ivPoints3.add(ivPoint);
            if (i == 4 ){
                vPoint.setVisibility(View.INVISIBLE);
            }else {
                vPoint.setVisibility(View.VISIBLE);
            }
            String title = "1a";
            switch (i){
                case 0:
                    title = "8";
                    break;
                case 1:
                    title = "9";
                    break;
                case 2:
                    title = "10";
                    break;
                case 3:
                    title = "11";
                    break;
                case 4:
                    title = "12";
                    break;
            }
            tvCount.setText(title);
            llPoints3.addView(pointView);
        }

//
//        for (int i = 0 ;i<14;i++){
//            View pointView = LayoutInflater.from(mContext).inflate(R.layout.item_point,null);
//            TextView tvCount = pointView.findViewById(R.id.tv_item_count);
//            ImageView ivPoint = pointView.findViewById(R.id.iv_item_point);
//            View vPoint = pointView.findViewById(R.id.v_item_point);
//            LinearLayout llPointInerOut = pointView.findViewById(R.id.ll_item_point_out);
//            llPointInerOut.setTag(i);
//            ivPoints.add(ivPoint);
//            if (i == 13 ){
//                vPoint.setVisibility(View.INVISIBLE);
//            }else {
//                vPoint.setVisibility(View.VISIBLE);
//            }
//            switch (page){
//                case 1:
////                    第一页 前4个 亮 0-3
//                    if (i <4){
//                        ivPoint.setImageResource(R.drawable.img_yellow);
//                    }else {
//                        ivPoint.setImageResource(R.drawable.img_grey);
//                    }
//                    break;
//                case 2:
////                    第2页 4-8
//                    if (i >3 && i <9){
//                        ivPoint.setImageResource(R.drawable.img_yellow);
//                    }else {
//                        ivPoint.setImageResource(R.drawable.img_grey);
//                    }
//
//                    break;
//                case 3:
////                    9-13
//                    if (i >8){
//                        ivPoint.setImageResource(R.drawable.img_yellow);
//                    }else {
//                        ivPoint.setImageResource(R.drawable.img_grey);
//                    }
//
//                    break;
//
//            }
////            标题b
////            tvCount.setText(questions.get(i).getqNo());
//            String title = "1a";
//            switch (i){
//                case 0:
//                    title = "1a";
//                    break;
//                case 1:
//                    title = "1b";
//                    break;
//                case 2:
//                    title = "1c";
//                    break;
//                case 3:
//                    title = "2";
//                    break;
//                case 4:
//                    title = "3";
//                    break;
//                case 5:
//                    title = "4";
//                    break;
//                case 6:
//                    title = "5";
//                    break;
//                case 7:
//                    title = "6";
//                    break;
//                case 8:
//                    title = "7";
//                    break;
//                case 9:
//                    title = "8";
//                    break;
//                case 10:
//                    title = "9";
//                    break;
//                case 11:
//                    title = "10";
//                    break;
//                case 12:
//                    title = "11";
//                    break;
//                case 13:
//                    title = "12";
//                    break;
//            }
//            tvCount.setText(title);
//            llPoints.addView(pointView);

//        }

    }

    private void getFormAData(){
        try {

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
        }catch (Exception e){
            LogUtil.loge("getFormAData",e.getMessage());
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_part_a_update;
    }

//    private void dealPointsAndPages(int position){
//        if (position <4){
//            //第一页
//            page = 1;
//        }else if (position >3 && position <9){
//            //第二页
//            page = 2;
//        }else {
//            //第三页
//            page = 3;
//        }
//        dealPoints();
//        //加载正确的页
//        switch (page){
//            case 1:
//                page1.setVisibility(View.VISIBLE);
//                page2.setVisibility(View.GONE);
//                page3.setVisibility(View.GONE);
//                break;
//            case 2:
//                page1.setVisibility(View.GONE);
//                page2.setVisibility(View.VISIBLE);
//                page3.setVisibility(View.GONE);
//                break;
//            case 3:
//                page1.setVisibility(View.GONE);
//                page2.setVisibility(View.GONE);
//                page3.setVisibility(View.VISIBLE);
//                break;
//        }
//    }
//    private void dealPoints(){
//        for (int i = 0;i<ivPoints.size();i++){
//            ImageView ivPoint = ivPoints.get(i);
//            switch (page){
//                case 1:
////                    第一页 前4个 亮 0-3
//                    if (i <4){
//                        ivPoint.setImageResource(R.drawable.img_yellow);
//                    }else {
//                        ivPoint.setImageResource(R.drawable.img_grey);
//                    }
//                    break;
//                case 2:
////                    第2页 4-8
//                    if (i >3 && i <9){
//                        ivPoint.setImageResource(R.drawable.img_yellow);
//                    }else {
//                        ivPoint.setImageResource(R.drawable.img_grey);
//                    }
//
//                    break;
//                case 3:
////                    9-13
//                    if (i >8){
//                        ivPoint.setImageResource(R.drawable.img_yellow);
//                    }else {
//                        ivPoint.setImageResource(R.drawable.img_grey);
//                    }
//
//                    break;
//
//            }
//        }
//    }
    private void changePoints(){
//        让所有的球都置灰
        for (int i = 0;i<ivPoints1.size();i++){
            ivPoints1.get(i).setImageResource(R.drawable.img_grey);
        }
        for (int i = 0;i<ivPoints2.size();i++){
            ivPoints2.get(i).setImageResource(R.drawable.img_grey);
        }
        for (int i = 0;i<ivPoints3.size();i++){
            ivPoints3.get(i).setImageResource(R.drawable.img_grey);
        }

        switch (page){
            case 1:
                for (int i = 0;i<ivPoints1.size();i++){
                    ivPoints1.get(i).setImageResource(R.drawable.img_yellow);
                }
                break;
            case 2:
                for (int i = 0;i<ivPoints2.size();i++){
                    ivPoints2.get(i).setImageResource(R.drawable.img_yellow);
                }
                break;
            case 3:
                for (int i = 0;i<ivPoints3.size();i++){
                    ivPoints3.get(i).setImageResource(R.drawable.img_yellow);
                }
                break;
        }
    }

    @Override
    public void onClickNext(View v) {
        switch (v.getId()){
            case R.id.iv_fa_photo:
                Intent intent = new Intent(mContext,PhotoActivity.class);
                intent.putExtra("jobId",mJobModle.getJobId());
                intent.putExtra("formId",formId);
                startActivity(intent);
                break;
            case R.id.iv_fa_show:
                Intent intent1 = new Intent(mContext,PhotoShowActivity.class);
                intent1.putExtra("jobId",mJobModle.getJobId());
                intent1.putExtra("formId",formId);
                startActivity(intent1);
                break;
            case R.id.tv_page1_complete:
            case R.id.ll_location_fa_page2:
                page = 2;
                page1.setVisibility(View.GONE);
                page2.setVisibility(View.VISIBLE);
                page3.setVisibility(View.GONE);
                changePoints();
                break;
            case R.id.tv_page2_complete:
            case R.id.ll_location_fa_page3:
                page = 3;
                page1.setVisibility(View.GONE);
                page2.setVisibility(View.GONE);
                page3.setVisibility(View.VISIBLE);
                changePoints();
                break;
            case R.id.ll_location_fa_page1:
                page = 1;
                page1.setVisibility(View.VISIBLE);
                page2.setVisibility(View.GONE);
                page3.setVisibility(View.GONE);
                changePoints();
                break;
        }

    }
}
