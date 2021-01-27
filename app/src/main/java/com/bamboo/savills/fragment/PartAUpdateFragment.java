package com.bamboo.savills.fragment;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bamboo.savills.Module.JobModule;
import com.bamboo.savills.Module.PartAAnswer;
import com.bamboo.savills.Module.PartAAnswerDetail;
import com.bamboo.savills.Module.SimpleResponse;
import com.bamboo.savills.R;
import com.bamboo.savills.activity.PhotoActivity;
import com.bamboo.savills.activity.PhotoShowActivity;
import com.bamboo.savills.base.net.HttpUtil;
import com.bamboo.savills.base.net.NetCallback;
import com.bamboo.savills.base.net.RequstList;
import com.bamboo.savills.base.utils.LogUtil;
import com.bamboo.savills.base.utils.StringUtil;
import com.bamboo.savills.base.view.BaseFragment;
import com.bamboo.savills.base.view.ToastUtil;
import com.bamboo.savills.inter.SubmitCallBack;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
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

    @InjectView(R.id.rl_q12_s4)
    RelativeLayout  rlq12s4;

    @InjectView(R.id.iv_q12_s4)
    ImageView ivq12s4;

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
//   选择题的选项进行归类
    private List<RelativeLayout> rlQ1a = new ArrayList<>();
    private List<ImageView> ivQ1a = new ArrayList<>();

    private List<RelativeLayout> rlQ1b = new ArrayList<>();
    private List<ImageView> ivQ1b = new ArrayList<>();

    private List<RelativeLayout> rlQ1c = new ArrayList<>();
    private List<ImageView> ivQ1c = new ArrayList<>();

    private List<RelativeLayout> rlQ2 = new ArrayList<>();
    private List<ImageView> ivQ2 = new ArrayList<>();

//7题比较特殊 分成7a 7b 两部分
    private List<RelativeLayout> rlQ7a = new ArrayList<>();
    private List<ImageView> ivQ7a = new ArrayList<>();

    private List<RelativeLayout> rlQ7b = new ArrayList<>();
    private List<ImageView> ivQ7b = new ArrayList<>();

    private List<RelativeLayout> rlQ8 = new ArrayList<>();
    private List<ImageView> ivQ8 = new ArrayList<>();

    private List<RelativeLayout> rlQ9 = new ArrayList<>();
    private List<ImageView> ivQ9 = new ArrayList<>();

    private List<RelativeLayout> rlQ12 = new ArrayList<>();
    private List<ImageView> ivQ12 = new ArrayList<>();
//    当前问卷的 答案对象
    private PartAAnswer mAnswer = new PartAAnswer();
//    选项单独处理 后期要转成2进制传给后台
    private List<Integer> q1aSelect = new ArrayList<>();
    private List<Integer> q1bSelect = new ArrayList<>();
    private List<Integer> q1cSelect = new ArrayList<>();
//    8，9题是后台的8a 8b
    private List<Integer> q9Select = new ArrayList<>();
    private int viewType = 0;//0 可编辑状态 1为不可编辑状态

    @Override
    public void initView() {
        String jobModule = getArguments().getString("JobModule");
        viewType = getArguments().getInt("View",0);
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
        initChoiceView();
        setDisEditState();

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
        initChoiceListener();
        initQ1aEditListener();
        initQ1bEditListener();
        initQ1cEditListener();
        initQ2EditListener();
        initQ3EditListener();
        initQ4EditListener();
        initQ5EditListener();
        initQ6EditListener();
        initQ7EditListener();
        initQ10EditListener();
        initQ11EditListener();
        tvPage1Save.setOnClickListener(this);
        tvPage2Save.setOnClickListener(this);
        tvPage3Save.setOnClickListener(this);
        tvPage3Complete.setOnClickListener(this);
    }


    @Override
    public void initData() {
        initPoint();
        getFormAData();
    }
    private void initQ11EditListener(){
        etq11f1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String answer = etq11f1.getText().toString().trim();
                mAnswer.setQuestion10RetailShopFrontage(answer);
            }
        });
        etq11f2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String answer = etq11f2.getText().toString().trim();
                mAnswer.setQuestion10RetailShopDepth(answer);
            }
        });
        etq11f3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String answer = etq11f3.getText().toString().trim();
                mAnswer.setQuestion10RetailShopTradesOfSubjectShop(answer);
            }
        });
        etq11f4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String answer = etq11f4.getText().toString().trim();
                mAnswer.setQuestion10RetailShopNeighbouringShops(answer);
            }
        });
    }
    private void initQ10EditListener(){
        etq10f1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String answer = etq10f1.getText().toString().trim();
                mAnswer.setQuestion9FactoryFeaturesLoadingCapacityAnswer1(answer);
            }
        });
        etq10f2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String answer = etq10f2.getText().toString().trim();
                mAnswer.setQuestion9FactoryFeaturesLoadingCapacityAnswer2(answer);
            }
        });
        etq10f3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String answer = etq10f3.getText().toString().trim();
                mAnswer.setQuestion9FactoryFeaturesCeilingHeight(answer);
            }
        });
    }
    private void initQ7EditListener(){
        etq7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String answer = etq7.getText().toString().trim();
                mAnswer.setQuestion7TotalNo(answer);
            }
        });
    }
    private void initQ6EditListener(){
        etq6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String answer = etq6.getText().toString().trim();
                mAnswer.setQuestion6StaircaseRemark(answer);
            }
        });

    }
    private void initQ5EditListener(){
        etq5f1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String answer = etq5f1.getText().toString().trim();
                mAnswer.setQuestion5LiftsPassengerRemark(answer);
            }
        });
        etq5f2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String answer = etq5f2.getText().toString().trim();
                mAnswer.setQuestion5LiftsServiceRemark(answer);
            }
        });
        etq5f3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String answer = etq5f3.getText().toString().trim();
                mAnswer.setQuestion5LiftsVehicleRemark(answer);
            }
        });
    }
    private void initQ4EditListener(){
        etq4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String answer = etq4.getText().toString().trim();
                mAnswer.setQuestion4FacilitiesProvided(answer);
            }
        });
    }
    private void initQ3EditListener(){
        etq3f1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String answer = etq3f1.getText().toString().trim();
                mAnswer.setQuestion3Fill1(answer);
            }
        });
        etq3f2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String answer = etq3f2.getText().toString().trim();
                mAnswer.setQuestion3Fill2(answer);
            }
        });
        etq3f3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String answer = etq3f3.getText().toString().trim();
                mAnswer.setQuestion3Fill3(answer);
            }
        });
        etq3f4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String answer = etq3f4.getText().toString().trim();
                mAnswer.setQuestion3Fill4(answer);
            }
        });
    }
    private void initQ2EditListener(){
        etq2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String answer = etq2.getText().toString().trim();
                mAnswer.setQuestion2Remark(answer);
            }
        });
    }
    private void initQ1cEditListener(){
        etq1c.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String answer = etq1c.getText().toString().trim();
                mAnswer.setQuestion1COthersAnswer(answer);
            }
        });
    }
    private void initQ1bEditListener(){
        etq1b.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String answer = etq1b.getText().toString().trim();
                mAnswer.setQuestion1BOthersAnswer(answer);
            }
        });
    }
    private void initQ1aEditListener(){
        etq1a.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String answer = etq1a.getText().toString().trim();
                mAnswer.setQuestion1AOthersAnswer(answer);
            }
        });
    }
    private void initChoiceListener(){
        rlq1as1.setOnClickListener(this);
        rlq1as2.setOnClickListener(this);
        rlq1as3.setOnClickListener(this);
        rlq1as4.setOnClickListener(this);
        rlq1as5.setOnClickListener(this);
        rlq1as6.setOnClickListener(this);
        rlq1as7.setOnClickListener(this);
        rlq1as8.setOnClickListener(this);
        rlq1bs1.setOnClickListener(this);
        rlq1bs2.setOnClickListener(this);
        rlq1bs3.setOnClickListener(this);
        rlq1cs1.setOnClickListener(this);
        rlq1cs2.setOnClickListener(this);
        rlq1cs3.setOnClickListener(this);
        rlq1cs4.setOnClickListener(this);
        rlq1cs5.setOnClickListener(this);
        rlq1cs6.setOnClickListener(this);
        rlq1cs7.setOnClickListener(this);
        rlq2s1.setOnClickListener(this);
        rlq2s2.setOnClickListener(this);
        rlq2s3.setOnClickListener(this);
        rlq2s4.setOnClickListener(this);
        rlq2s5.setOnClickListener(this);
        rlq7s1.setOnClickListener(this);
        rlq7s2.setOnClickListener(this);
        rlq7s3.setOnClickListener(this);
        rlq7s4.setOnClickListener(this);

        rlq8s1.setOnClickListener(this);
        rlq8s2.setOnClickListener(this);
        rlq8s3.setOnClickListener(this);
        rlq8s4.setOnClickListener(this);

        rlq9s1.setOnClickListener(this);
        rlq9s2.setOnClickListener(this);
        rlq9s3.setOnClickListener(this);

        rlq12s1.setOnClickListener(this);
        rlq12s2.setOnClickListener(this);
        rlq12s3.setOnClickListener(this);
        rlq12s4.setOnClickListener(this);
    }
    private void initChoiceView(){
        rlQ1a.add(rlq1as1);
        ivQ1a.add(ivq1as1);

        rlQ1a.add(rlq1as2);
        ivQ1a.add(ivq1as2);

        rlQ1a.add(rlq1as3);
        ivQ1a.add(ivq1as3);

        rlQ1a.add(rlq1as4);
        ivQ1a.add(ivq1as4);

        rlQ1a.add(rlq1as5);
        ivQ1a.add(ivq1as5);

        rlQ1a.add(rlq1as6);
        ivQ1a.add(ivq1as6);

        rlQ1a.add(rlq1as7);
        ivQ1a.add(ivq1as7);

        rlQ1a.add(rlq1as8);
        ivQ1a.add(ivq1as8);

        rlQ1b.add(rlq1bs1);
        ivQ1b.add(ivq1bs1);

        rlQ1b.add(rlq1bs2);
        ivQ1b.add(ivq1bs2);

        rlQ1b.add(rlq1bs3);
        ivQ1b.add(ivq1bs3);

        rlQ1c.add(rlq1cs1);
        ivQ1c.add(ivq1cs1);

        rlQ1c.add(rlq1cs2);
        ivQ1c.add(ivq1cs2);

        rlQ1c.add(rlq1cs3);
        ivQ1c.add(ivq1cs3);

        rlQ1c.add(rlq1cs4);
        ivQ1c.add(ivq1cs4);

        rlQ1c.add(rlq1cs5);
        ivQ1c.add(ivq1cs5);

        rlQ1c.add(rlq1cs6);
        ivQ1c.add(ivq1cs6);

        rlQ1c.add(rlq1cs7);
        ivQ1c.add(ivq1cs7);

        rlQ2.add(rlq2s1);
        ivQ2.add(ivq2s1);

        rlQ2.add(rlq2s2);
        ivQ2.add(ivq2s2);

        rlQ2.add(rlq2s3);
        ivQ2.add(ivq2s3);

        rlQ2.add(rlq2s4);
        ivQ2.add(ivq2s4);

        rlQ2.add(rlq2s5);
        ivQ2.add(ivq2s5);

        rlQ7a.add(rlq7s1);
        ivQ7a.add(ivq7s1);

        rlQ7a.add(rlq7s2);
        ivQ7a.add(ivq7s2);

        rlQ7b.add(rlq7s3);
        ivQ7b.add(ivq7s3);

        rlQ7b.add(rlq7s4);
        ivQ7b.add(ivq7s4);

        rlQ8.add(rlq8s1);
        ivQ8.add(ivq8s1);

        rlQ8.add(rlq8s2);
        ivQ8.add(ivq8s2);

        rlQ8.add(rlq8s3);
        ivQ8.add(ivq8s3);

        rlQ8.add(rlq8s4);
        ivQ8.add(ivq8s4);

        rlQ9.add(rlq9s1);
        ivQ9.add(ivq9s1);

        rlQ9.add(rlq9s2);
        ivQ9.add(ivq9s2);

        rlQ9.add(rlq9s3);
        ivQ9.add(ivq9s3);

        rlQ12.add(rlq12s1);
        ivQ12.add(ivq12s1);

        rlQ12.add(rlq12s2);
        ivQ12.add(ivq12s2);

        rlQ12.add(rlq12s3);
        ivQ12.add(ivq12s3);

        rlQ12.add(rlq12s4);
        ivQ12.add(ivq12s4);

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


    }

    private void setDisEditState(){
        switch (viewType){
            case 0:
//                正常可编辑
                rlq1as1.setClickable(true);
                rlq1as2.setClickable(true);
                rlq1as3.setClickable(true);
                rlq1as4.setClickable(true);
                rlq1as5.setClickable(true);
                rlq1as6.setClickable(true);
                rlq1as7.setClickable(true);
                rlq1as8.setClickable(true);
                etq1a.setEnabled(true);
                rlq1bs1.setClickable(true);
                rlq1bs2.setClickable(true);
                rlq1bs3.setClickable(true);
                etq1b.setEnabled(true);
                rlq1cs1.setClickable(true);
                rlq1cs2.setClickable(true);
                rlq1cs3.setClickable(true);
                rlq1cs4.setClickable(true);
                rlq1cs5.setClickable(true);
                rlq1cs6.setClickable(true);
                rlq1cs7.setClickable(true);
                etq1c.setEnabled(true);
                rlq2s1.setClickable(true);
                rlq2s2.setClickable(true);
                rlq2s3.setClickable(true);
                rlq2s4.setClickable(true);
                rlq2s5.setClickable(true);
                etq2.setEnabled(true);
                etq3f1.setEnabled(true);
                etq3f2.setEnabled(true);
                etq3f3.setEnabled(true);
                etq3f4.setEnabled(true);
                etq4.setEnabled(true);
                etq5f1.setEnabled(true);
                etq5f2.setEnabled(true);
                etq5f3.setEnabled(true);
                etq6.setEnabled(true);
                rlq7s1.setClickable(true);
                rlq7s2.setClickable(true);
                rlq7s3.setClickable(true);
                rlq7s4.setClickable(true);
                etq7.setEnabled(true);
                rlq8s1.setClickable(true);
                rlq8s2.setClickable(true);
                rlq8s3.setClickable(true);
                rlq8s4.setClickable(true);
                rlq9s1.setClickable(true);
                rlq9s2.setClickable(true);
                rlq9s3.setClickable(true);
                etq10f1.setEnabled(true);
                etq10f2.setEnabled(true);
                etq10f3.setEnabled(true);
                etq11f1.setEnabled(true);
                etq11f2.setEnabled(true);
                etq11f3.setEnabled(true);
                etq11f4.setEnabled(true);
                rlq12s1.setClickable(true);
                rlq12s2.setClickable(true);
                rlq12s3.setClickable(true);
                rlq12s4.setClickable(true);
                tvPage1Save.setVisibility(View.VISIBLE);
                tvPage2Save.setVisibility(View.VISIBLE);
                tvPage3Save.setVisibility(View.VISIBLE);
                tvPage3Complete.setVisibility(View.VISIBLE);
                ivPhoto.setVisibility(View.VISIBLE);

                break;
            case 1:
//                不可编辑
                rlq1as1.setClickable(false);
                rlq1as2.setClickable(false);
                rlq1as3.setClickable(false);
                rlq1as4.setClickable(false);
                rlq1as5.setClickable(false);
                rlq1as6.setClickable(false);
                rlq1as7.setClickable(false);
                rlq1as8.setClickable(false);
                etq1a.setEnabled(false);
                rlq1bs1.setClickable(false);
                rlq1bs2.setClickable(false);
                rlq1bs3.setClickable(false);
                etq1b.setEnabled(false);
                rlq1cs1.setClickable(false);
                rlq1cs2.setClickable(false);
                rlq1cs3.setClickable(false);
                rlq1cs4.setClickable(false);
                rlq1cs5.setClickable(false);
                rlq1cs6.setClickable(false);
                rlq1cs7.setClickable(false);
                etq1c.setEnabled(false);
                rlq2s1.setClickable(false);
                rlq2s2.setClickable(false);
                rlq2s3.setClickable(false);
                rlq2s4.setClickable(false);
                rlq2s5.setClickable(false);
                etq2.setEnabled(false);
                etq3f1.setEnabled(false);
                etq3f2.setEnabled(false);
                etq3f3.setEnabled(false);
                etq3f4.setEnabled(false);
                etq4.setEnabled(false);
                etq5f1.setEnabled(false);
                etq5f2.setEnabled(false);
                etq5f3.setEnabled(false);
                etq6.setEnabled(false);
                rlq7s1.setClickable(false);
                rlq7s2.setClickable(false);
                rlq7s3.setClickable(false);
                rlq7s4.setClickable(false);
                etq7.setEnabled(false);
                rlq8s1.setClickable(false);
                rlq8s2.setClickable(false);
                rlq8s3.setClickable(false);
                rlq8s4.setClickable(false);
                rlq9s1.setClickable(false);
                rlq9s2.setClickable(false);
                rlq9s3.setClickable(false);
                etq10f1.setEnabled(false);
                etq10f2.setEnabled(false);
                etq10f3.setEnabled(false);
                etq11f1.setEnabled(false);
                etq11f2.setEnabled(false);
                etq11f3.setEnabled(false);
                etq11f4.setEnabled(false);
                rlq12s1.setClickable(false);
                rlq12s2.setClickable(false);
                rlq12s3.setClickable(false);
                rlq12s4.setClickable(false);
                tvPage1Save.setVisibility(View.GONE);
                tvPage2Save.setVisibility(View.GONE);
                tvPage3Save.setVisibility(View.GONE);
                tvPage3Complete.setVisibility(View.GONE);
                ivPhoto.setVisibility(View.GONE);

                break;
        }
    }

    private void getFormAData(){
        showLoading();
        q1aSelect.clear();
        q1bSelect.clear();
        q1cSelect.clear();
        q9Select.clear();
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
                        mAnswer = partAAnswerDetail.getData();
//                        mAnswer.setJobPropertyId(partAAnswerDetail.getData().getJobPropertyId());
//                        获取到的数据 如何设置到 表格上
                        dealRealData();

                    }
                }

                @Override
                public void onError(int tag, String msg) {
                    LogUtil.loge("getFormAData-onError",msg);
                }

                @Override
                public void onComplete(int tag) {
                    hideLoading();
                }
            });
        }catch (Exception e){
            LogUtil.loge("getFormAData",e.getMessage());
        }

    }
    private void useWhichData(){
        if (mAnswer.isSubmitted()){
            viewType = 1;
            setDisEditState();
            spUtils.setIsSaveFormA(mAnswer.getId(),false);
            ((SubmitCallBack)mContext).onSubmitCallBack(0,true);
        }else {
            viewType = 0;
            setDisEditState();
            ((SubmitCallBack)mContext).onSubmitCallBack(0,false);
        }
        try {
            boolean isSave = spUtils.getIsSaveFormA(mAnswer.getId());
            if (isSave){
                //判断时间
                String json = spUtils.getSaveFormADetail(mAnswer.getId());
                PartAAnswer spAnswer = new Gson().fromJson(json,new TypeToken<PartAAnswer>(){}.getType());
                LogUtil.loge("useWhichData",json);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                Date date = sdf.parse(mAnswer.getUpdatedOn());
                LogUtil.loge("useWhichData",spAnswer.getUpdatedOn()+"///"+date.getTime());
                if (date.getTime() > Long.parseLong(spAnswer.getUpdatedOn())){
//                网络获取的就是最新的
                    spUtils.setIsSaveFormA(mAnswer.getId(),false);
                }else {
                    //本地的是最新的数据
//                step1保存 --->成功后 改成false ---》重新获取数据
                    mAnswer = spAnswer;
                    saveFormA(false);
                }

            }

        }catch (Exception e){
            LogUtil.loge("useWhichData",e.getMessage());
        }
    }
    private void dealRealData(){
        useWhichData();
// 设置数据到表格上
//       第1a题
        char[] chars1a = reserve(Integer.toBinaryString(mAnswer.getQuestion1ATypeOfBuilding()).toCharArray());
        for (int i = 0;i<chars1a.length;i++){
//            LogUtil.loge("char",i+":"+chars1a[i]);
            if (chars1a[i]=='1'){
                //
               dealChoicesShow(i,q1aSelect,ivQ1a,rlQ1a);
            }
        }
        if (StringUtil.isNotEmpty(mAnswer.getQuestion1AOthersAnswer()))
            etq1a.setText(mAnswer.getQuestion1AOthersAnswer());

//        第1b题
        char[] chars1b =reserve( Integer.toBinaryString(mAnswer.getQuestion1BModeOfConstruction()).toCharArray());
//        List<String > mList = Collections.reverse(Arrays.asList(chars1b));
        for (int i = 0;i<chars1b.length;i++){
            if (chars1b[i]=='1'){
                dealChoicesShow(i,q1bSelect,ivQ1b,rlQ1b);
            }
        }
        if (StringUtil.isNotEmpty(mAnswer.getQuestion1BOthersAnswer()))
            etq1b.setText(mAnswer.getQuestion1BOthersAnswer());
//        第1c 题
        char[] chars1c = reserve( Integer.toBinaryString(mAnswer.getQuestion1CExternalWallFinishes()).toCharArray());
        for (int i = 0;i<chars1c.length;i++){
            if (chars1c[i]=='1'){
                dealChoicesShow(i,q1cSelect,ivQ1c,rlQ1c);
            }
        }
        if (StringUtil.isNotEmpty(mAnswer.getQuestion1COthersAnswer()))
            etq1c.setText(mAnswer.getQuestion1COthersAnswer());

//        第二题
        dealSingleChoicesShow(mAnswer.getQuestion2ExternalCondition()-1,ivQ2,rlQ2);

        if (StringUtil.isNotEmpty(mAnswer.getQuestion2Remark()))
            etq2.setText(mAnswer.getQuestion2Remark());

//        第三题
        if (StringUtil.isNotEmpty(mAnswer.getQuestion3Fill1()))
            etq3f1.setText(mAnswer.getQuestion3Fill1());

        if (StringUtil.isNotEmpty(mAnswer.getQuestion3Fill2()))
            etq3f2.setText(mAnswer.getQuestion3Fill2());

        if (StringUtil.isNotEmpty(mAnswer.getQuestion3Fill3()))
            etq3f3.setText(mAnswer.getQuestion3Fill3());

        if (StringUtil.isNotEmpty(mAnswer.getQuestion3Fill4()))
            etq3f4.setText(mAnswer.getQuestion3Fill4());
//        第四题
        if (StringUtil.isNotEmpty(mAnswer.getQuestion4FacilitiesProvided()))
            etq4.setText(mAnswer.getQuestion4FacilitiesProvided());
//        第5题

        if (StringUtil.isNotEmpty(mAnswer.getQuestion5LiftsPassengerRemark()))
            etq5f1.setText(mAnswer.getQuestion5LiftsPassengerRemark());

        if (StringUtil.isNotEmpty(mAnswer.getQuestion5LiftsServiceRemark()))
            etq5f2.setText(mAnswer.getQuestion5LiftsServiceRemark());

        if (StringUtil.isNotEmpty(mAnswer.getQuestion5LiftsVehicleRemark()))
            etq5f3.setText(mAnswer.getQuestion5LiftsVehicleRemark());

//        第六题
        if (StringUtil.isNotEmpty(mAnswer.getQuestion6StaircaseRemark()))
            etq6.setText(mAnswer.getQuestion6StaircaseRemark());
//        第七题 其实是两部分
        dealSingleChoicesShow(mAnswer.getQuestion7AOption()-1,ivQ7a,rlQ7a);
        dealSingleChoicesShow(mAnswer.getQuestion7BOption()-1,ivQ7b,rlQ7b);

        if (StringUtil.isNotEmpty(mAnswer.getQuestion7TotalNo()))
            etq7.setText(mAnswer.getQuestion7TotalNo());
//        第8题 接口的8a
        dealSingleChoicesShow(mAnswer.getQuestion8AAirConditioning()-1,ivQ8,rlQ8);

//        第九题 接口的8b
        char[] chars9 = reserve( Integer.toBinaryString(mAnswer.getQuestion8BFireFighting()).toCharArray());
        for (int i = 0;i<chars9.length;i++){
            if (chars9[i]=='1'){
                dealChoicesShow(i,q9Select,ivQ9,rlQ9);
            }
        }
//        第十题 接口的9
        if (StringUtil.isNotEmpty(mAnswer.getQuestion9FactoryFeaturesLoadingCapacityAnswer1()))
            etq10f1.setText(mAnswer.getQuestion9FactoryFeaturesLoadingCapacityAnswer1());

        if (StringUtil.isNotEmpty(mAnswer.getQuestion9FactoryFeaturesLoadingCapacityAnswer2()))
            etq10f2.setText(mAnswer.getQuestion9FactoryFeaturesLoadingCapacityAnswer2());

        if (StringUtil.isNotEmpty(mAnswer.getQuestion9FactoryFeaturesCeilingHeight()))
            etq10f3.setText(mAnswer.getQuestion9FactoryFeaturesCeilingHeight());

//        第十一题 接口的10

        if (StringUtil.isNotEmpty(mAnswer.getQuestion10RetailShopFrontage()))
            etq11f1.setText(mAnswer.getQuestion10RetailShopFrontage());

        if (StringUtil.isNotEmpty(mAnswer.getQuestion10RetailShopDepth()))
            etq11f2.setText(mAnswer.getQuestion10RetailShopDepth());

        if (StringUtil.isNotEmpty(mAnswer.getQuestion10RetailShopTradesOfSubjectShop()))
            etq11f3.setText(mAnswer.getQuestion10RetailShopTradesOfSubjectShop());

        if (StringUtil.isNotEmpty(mAnswer.getQuestion10RetailShopNeighbouringShops()))
            etq11f4.setText(mAnswer.getQuestion10RetailShopNeighbouringShops());

//        第十二题 接口的11

        dealSingleChoicesShow(mAnswer.getQuestion11PedestrainFlow()-1,ivQ12,rlQ12);

    }
    public static char[] reserve( char[] arr ){
        char[] arr1 = new char[arr.length];
        for( int x=0;x<arr.length;x++ ){
            arr1[x] = arr[arr.length-x-1];
        }
        return arr1 ;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_part_a_update;
    }

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
    private boolean dealChoicesShow(int position,List<Integer> choices,List<ImageView> ivImgs,List<RelativeLayout> rlOuts){
//        存起来还是删除
        boolean isHave = false;
        for (int i = 0;i<choices.size();i++){
            if (choices.get(i)== position){
                isHave = true;
            }
        }

        if (isHave){
            ivImgs.get(position).setVisibility(View.GONE);
            rlOuts.get(position).setBackgroundResource(R.drawable.bg_item_selector);
            choices.remove((Integer) position);
        }else {
            ivImgs.get(position).setVisibility(View.VISIBLE);
            rlOuts.get(position).setBackgroundResource(R.drawable.bg_item_selector_select);
            choices.add(position);
        }
        return  isHave;
    }
    private void dealQ1aAnswer(boolean isHave,int position){
        int answer = mAnswer.getQuestion1ATypeOfBuilding();
        if (isHave){
           answer = (int)(answer-Math.pow(2,position));
        }else {
            answer = (int)(answer+Math.pow(2,position));
        }
        mAnswer.setQuestion1ATypeOfBuilding(answer);
//        LogUtil.loge("dealQ1aAnswer","dealQ1aAnswer:"+answer);
    }

    private void dealQ1bAnswer(boolean isHave,int position){
        int answer = mAnswer.getQuestion1BModeOfConstruction();
        if (isHave){
            answer = (int)(answer-Math.pow(2,position));
        }else {
            answer = (int)(answer+Math.pow(2,position));
        }
        mAnswer.setQuestion1BModeOfConstruction(answer);
//        LogUtil.loge("dealQ1aAnswer","dealQ1aAnswer:"+answer);
    }

    private void dealQ1cAnswer(boolean isHave,int position){
        int answer = mAnswer.getQuestion1CExternalWallFinishes();
        if (isHave){
            answer = (int)(answer-Math.pow(2,position));
        }else {
            answer = (int)(answer+Math.pow(2,position));
        }
        mAnswer.setQuestion1CExternalWallFinishes(answer);
//        LogUtil.loge("dealQ1aAnswer","dealQ1aAnswer:"+answer);
    }

    private void dealQ9Answer(boolean isHave,int position){
        int answer = mAnswer.getQuestion8BFireFighting();
        if (isHave){
            answer = (int)(answer-Math.pow(2,position));
        }else {
            answer = (int)(answer+Math.pow(2,position));
        }
        mAnswer.setQuestion8BFireFighting(answer);
//        LogUtil.loge("dealQ1aAnswer","dealQ1aAnswer:"+answer);
    }

    private void dealSingleChoicesShow(int position,List<ImageView> ivImgs,List<RelativeLayout> rlOuts){
        for (int i = 0;i<ivImgs.size();i++){
            if (position == i){
                ivImgs.get(i).setVisibility(View.VISIBLE);
                rlOuts.get(i).setBackgroundResource(R.drawable.bg_item_selector_select);
            }else {
                ivImgs.get(i).setVisibility(View.GONE);
                rlOuts.get(i).setBackgroundResource(R.drawable.bg_item_selector);
            }
        }
    }

    private void saveFormA(boolean isSubmit){
        showLoading();
        String json = new Gson().toJson(mAnswer,new TypeToken<PartAAnswer>(){}.getType());
        LogUtil.loge("saveFormA-json",json);
        HttpUtil.getInstance().postJson(mContext, RequstList.SAVE_FORM_A + mJobModle.getJobId(), 500, json, new NetCallback() {
            @Override
            public void onSuccess(int tag, String result) {
                LogUtil.loge("saveFormA",result);
//                 {"data":"27a868ed-7b8c-41c2-b581-08d8a7df3f73","code":0,"codeDesc":"OK"}
                SimpleResponse simple= new Gson().fromJson(result,new TypeToken<SimpleResponse>(){}.getType());
                if (simple!= null &&  simple.getCode() == 0){
                    //保存成功
                    ToastUtil.showToast(mContext,"Saved Successfully");
                    spUtils.setIsSaveFormA(mAnswer.getId(),false);
                    if (isSubmit){
                        submitFormA();
                    }else {
                        getFormAData();
                    }
                }else {
                    //失败了 要保存在本地
                    saveLocal();
                }
            }

            @Override
            public void onError(int tag, String msg) {
                //失败了 要保存在本地
                saveLocal();
            }

            @Override
            public void onComplete(int tag) {
                hideLoading();
            }
        });

    }
    private void saveLocal(){
        //数据保存到本地 -- 这个最后写
        mAnswer.setUpdatedOn(String.valueOf(System.currentTimeMillis()));
        spUtils.setIsSaveFormA(mAnswer.getId(),true);
        spUtils.setSaveFormADetail(mAnswer.getId(),new Gson().toJson(mAnswer,new TypeToken<PartAAnswer>(){}.getType()));
        ToastUtil.showToast(mContext,"Network anomaly data saved locally");
    }

    private void submitFormA(){
       showLoading();
        HttpUtil.getInstance().post(mContext, RequstList.SUBMIT_FORM + mJobModle.getJobId() + "/" + mAnswer.getJobPropertyId(), 600, null, new NetCallback() {
            @Override
            public void onSuccess(int tag, String result) {
                LogUtil.loge("submitFormA",result);
//                {"code":0,"codeDesc":"OK"}
                SimpleResponse simple = new Gson().fromJson(result,new TypeToken<SimpleResponse>(){}.getType());
                if (simple.getCode() == 0){
                    ToastUtil.showToast(mContext,"Submitted successfully");
                    //
                    viewType = 1;
                    setDisEditState();
                    spUtils.setIsSaveFormA(mAnswer.getId(),false);
                    ((SubmitCallBack)mContext).onSubmitCallBack(0,true);
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
    private void testComplete(){
        //1a 1b 1c 2 必填
        if (q1aSelect.size()<=0 || mAnswer.getQuestion1ATypeOfBuilding()<=0){
            ToastUtil.showToast(mContext,"Please answer question 1a first.");
            return;
        }

        if (q1bSelect.size()<=0 ||mAnswer.getQuestion1BModeOfConstruction() <=0){
            ToastUtil.showToast(mContext,"Please answer question 1b first.");
            return;
        }

        if (q1cSelect.size()<=0 ||mAnswer.getQuestion1CExternalWallFinishes() <=0){
            ToastUtil.showToast(mContext,"Please answer question 1c first.");
            return;
        }


        if (mAnswer.getQuestion2ExternalCondition()==0){
            ToastUtil.showToast(mContext,"Please answer question 2 first.");
            return;
        }

//        1a 答案包括indus(2的2方) 8，9，10 必填
        if (isIaContains(2)){
            if (mAnswer.getQuestion8AAirConditioning() == 0){
                ToastUtil.showToast(mContext,"Please answer question 8 first.");
                return;
            }
            if (q9Select.size() <=0 || mAnswer.getQuestion8BFireFighting() <=0){
                ToastUtil.showToast(mContext,"Please answer question 9 first.");
                return;
            }
            if (StringUtil.isEmpty(mAnswer.getQuestion9FactoryFeaturesCeilingHeight()) ||
                    StringUtil.isEmpty(mAnswer.getQuestion9FactoryFeaturesLoadingCapacityAnswer1() )||
                    StringUtil.isEmpty(mAnswer.getQuestion9FactoryFeaturesLoadingCapacityAnswer2())){
                ToastUtil.showToast(mContext,"Please answer question 10 first.");
                return;
            }
        }
//        1a 答案包括retail(2的4方) 11,12 必填
        if (isIaContains(4)){
            if (StringUtil.isEmpty(mAnswer.getQuestion10RetailShopFrontage())||
                    StringUtil.isEmpty(mAnswer.getQuestion10RetailShopDepth())||
                    StringUtil.isEmpty(mAnswer.getQuestion10RetailShopTradesOfSubjectShop()) ||
                    StringUtil.isEmpty(mAnswer.getQuestion10RetailShopNeighbouringShops())){
                ToastUtil.showToast(mContext,"Please answer question 11 first.");
                return;
            }
            if (mAnswer.getQuestion11PedestrainFlow() == 0){
                ToastUtil.showToast(mContext,"Please answer question 12 first.");
                return;
            }
        }

//        1a 答案包括carpark(2的6方) 7 必填
        if (isIaContains(6)){
//            对应的7a 7b 填空
            if (mAnswer.getQuestion7AOption()==0){
                ToastUtil.showToast(mContext,"Please answer question 7 first.\n Select Private Car or Lorry.");
                return;
            }
            if (mAnswer.getQuestion7BOption() == 0){
                ToastUtil.showToast(mContext,"Please answer question 7 first.\n Select Covered or Uncovered.");
                return;
            }
            if (StringUtil.isEmpty(mAnswer.getQuestion7TotalNo())){
                ToastUtil.showToast(mContext,"Please answer question 7 first.\n Input the Total No.");
                return;
            }
        }
//        1a 1b 1c 如果选择了others 填空必填
        if (isIaContains(7) && StringUtil.isEmpty(mAnswer.getQuestion1AOthersAnswer())){
            ToastUtil.showToast(mContext,"Please answer question 1a first.\n Fill in the blank.");
            return;
        }
        if (isIbContains(2)&& StringUtil.isEmpty(mAnswer.getQuestion1BOthersAnswer())){
            ToastUtil.showToast(mContext,"Please answer question 1b first.\n Fill in the blank.");
            return;
        }

        if (isIcContains(6)&& StringUtil.isEmpty(mAnswer.getQuestion1COthersAnswer())){
            ToastUtil.showToast(mContext,"Please answer question 1c first.\n Fill in the blank.");
            return;
        }
//      提交前 要先 保存 别问为什么 问就是后台让这么做的
        saveFormA(true);
    }
    private boolean isIcContains(int position){
        char[] chars1a = reserve(Integer.toBinaryString(mAnswer.getQuestion1CExternalWallFinishes()).toCharArray());
        if (chars1a.length >position){
            return  chars1a[position] == '1';
        }else {
            return false;
        }

    }
    private boolean isIbContains(int position){
        char[] chars1a = reserve(Integer.toBinaryString(mAnswer.getQuestion1BModeOfConstruction()).toCharArray());
        if (chars1a.length >position){
            return  chars1a[position] == '1';
        }else {
            return false;
        }

    }

    private boolean isIaContains(int position){
        char[] chars1a = reserve(Integer.toBinaryString(mAnswer.getQuestion1ATypeOfBuilding()).toCharArray());
        if (chars1a.length >position){
            return  chars1a[position] == '1';
        }else {
            return false;
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
            case R.id.rl_q1a_s1:
                boolean isHave = dealChoicesShow(0,q1aSelect,ivQ1a,rlQ1a);
                dealQ1aAnswer(isHave,0);
                break;
            case R.id.rl_q1a_s2:
                boolean isHave1 = dealChoicesShow(1,q1aSelect,ivQ1a,rlQ1a);
                dealQ1aAnswer(isHave1,1);
                break;
            case R.id.rl_q1a_s3:
                boolean isHave2 = dealChoicesShow(2,q1aSelect,ivQ1a,rlQ1a);
                dealQ1aAnswer(isHave2,2);
                break;
            case R.id.rl_q1a_s4:
                boolean isHave3 = dealChoicesShow(3,q1aSelect,ivQ1a,rlQ1a);
                dealQ1aAnswer(isHave3,3);
                break;
            case R.id.rl_q1a_s5:
                boolean isHave4 = dealChoicesShow(4,q1aSelect,ivQ1a,rlQ1a);
                dealQ1aAnswer(isHave4,4);
                break;
            case R.id.rl_q1a_s6:
                boolean isHave5 = dealChoicesShow(5,q1aSelect,ivQ1a,rlQ1a);
                dealQ1aAnswer(isHave5,5);
                break;
            case R.id.rl_q1a_s7:
                boolean isHave6 = dealChoicesShow(6,q1aSelect,ivQ1a,rlQ1a);
                dealQ1aAnswer(isHave6,6);
                break;
            case R.id.rl_q1a_s8:
                boolean isHave7 = dealChoicesShow(7,q1aSelect,ivQ1a,rlQ1a);
                dealQ1aAnswer(isHave7,7);
                break;
            case R.id.rl_q1b_s1:
                boolean isHave1b = dealChoicesShow(0,q1bSelect,ivQ1b,rlQ1b);
                dealQ1bAnswer(isHave1b,0);
                break;
            case R.id.rl_q1b_s2:
                boolean isHave1b1 = dealChoicesShow(1,q1bSelect,ivQ1b,rlQ1b);
                dealQ1bAnswer(isHave1b1,1);
                break;
            case R.id.rl_q1b_s3:
                boolean isHave1b2 = dealChoicesShow(2,q1bSelect,ivQ1b,rlQ1b);
                dealQ1bAnswer(isHave1b2,2);
                break;
            case R.id.rl_q1c_s1:
                boolean isHave1c = dealChoicesShow(0,q1cSelect,ivQ1c,rlQ1c);
                dealQ1cAnswer(isHave1c,0);
                break;
            case R.id.rl_q1c_s2:
                boolean isHave1c1 = dealChoicesShow(1,q1cSelect,ivQ1c,rlQ1c);
                dealQ1cAnswer(isHave1c1,1);
                break;
            case R.id.rl_q1c_s3:
                boolean isHave1c2 = dealChoicesShow(2,q1cSelect,ivQ1c,rlQ1c);
                dealQ1cAnswer(isHave1c2,2);
                break;
            case R.id.rl_q1c_s4:
                boolean isHave1c3 = dealChoicesShow(3,q1cSelect,ivQ1c,rlQ1c);
                dealQ1cAnswer(isHave1c3,3);
                break;
            case R.id.rl_q1c_s5:
                boolean isHave1c4 = dealChoicesShow(4,q1cSelect,ivQ1c,rlQ1c);
                dealQ1cAnswer(isHave1c4,4);
                break;
            case R.id.rl_q1c_s6:
                boolean isHave1c5 = dealChoicesShow(5,q1cSelect,ivQ1c,rlQ1c);
                dealQ1cAnswer(isHave1c5,5);
                break;
            case R.id.rl_q1c_s7:
                boolean isHave1c6 = dealChoicesShow(6,q1cSelect,ivQ1c,rlQ1c);
                dealQ1cAnswer(isHave1c6,6);
                break;
            case R.id.rl_q2_s1:
//
                dealSingleChoicesShow(0,ivQ2,rlQ2);
                mAnswer.setQuestion2ExternalCondition(1);

                break;
            case R.id.rl_q2_s2:

                dealSingleChoicesShow(1,ivQ2,rlQ2);
                mAnswer.setQuestion2ExternalCondition(2);

                break;

            case R.id.rl_q2_s3:

                dealSingleChoicesShow(2,ivQ2,rlQ2);
                mAnswer.setQuestion2ExternalCondition(3);

                break;

            case R.id.rl_q2_s4:

                dealSingleChoicesShow(3,ivQ2,rlQ2);
                mAnswer.setQuestion2ExternalCondition(4);

                break;

            case R.id.rl_q2_s5:

                dealSingleChoicesShow(4,ivQ2,rlQ2);
                mAnswer.setQuestion2ExternalCondition(5);

                break;
            case R.id.rl_q7_s1:

                dealSingleChoicesShow(0,ivQ7a,rlQ7a);
                mAnswer.setQuestion7AOption(1);

                break;

            case R.id.rl_q7_s2:

                dealSingleChoicesShow(1,ivQ7a,rlQ7a);
                mAnswer.setQuestion7AOption(2);

                break;
            case R.id.rl_q7_s3:

                dealSingleChoicesShow(0,ivQ7b,rlQ7b);
                mAnswer.setQuestion7BOption(1);

                break;
            case R.id.rl_q7_s4:

                dealSingleChoicesShow(1,ivQ7b,rlQ7b);
                mAnswer.setQuestion7BOption(2);

                break;
            case R.id.rl_q8_s1:
                dealSingleChoicesShow(0,ivQ8,rlQ8);
                mAnswer.setQuestion8AAirConditioning(1);
                break;

            case R.id.rl_q8_s2:
                dealSingleChoicesShow(1,ivQ8,rlQ8);
                mAnswer.setQuestion8AAirConditioning(2);
                break;

            case R.id.rl_q8_s3:
                dealSingleChoicesShow(2,ivQ8,rlQ8);
                mAnswer.setQuestion8AAirConditioning(3);
                break;

            case R.id.rl_q8_s4:
                dealSingleChoicesShow(3,ivQ8,rlQ8);
                mAnswer.setQuestion8AAirConditioning(4);
                break;

            case R.id.rl_q9_s1:

                boolean isHaveQ9 = dealChoicesShow(0,q9Select,ivQ9,rlQ9);
                dealQ9Answer(isHaveQ9,0);
                break;

            case R.id.rl_q9_s2:

                boolean isHaveQ91 = dealChoicesShow(1,q9Select,ivQ9,rlQ9);
                dealQ9Answer(isHaveQ91,1);
                break;
            case R.id.rl_q9_s3:

                boolean isHaveQ92 = dealChoicesShow(2,q9Select,ivQ9,rlQ9);
                dealQ9Answer(isHaveQ92,2);
                break;

            case R.id.rl_q12_s1:
                dealSingleChoicesShow(0,ivQ12,rlQ12);
                mAnswer.setQuestion11PedestrainFlow(1);
                break;

            case R.id.rl_q12_s2:
                dealSingleChoicesShow(1,ivQ12,rlQ12);
                mAnswer.setQuestion11PedestrainFlow(2);
                break;

            case R.id.rl_q12_s3:
                dealSingleChoicesShow(2,ivQ12,rlQ12);
                mAnswer.setQuestion11PedestrainFlow(3);
                break;

            case R.id.rl_q12_s4:
                dealSingleChoicesShow(3,ivQ12,rlQ12);
                mAnswer.setQuestion11PedestrainFlow(4);
                break;
            case R.id.tv_page1_save:
            case R.id.tv_page2_save:
            case R.id.tv_page3_save:
                saveFormA(false);
                break;
            case R.id.tv_page3_complete:
//                提交前要验证一下必填项是否都填了
                testComplete();

                break;

        }

    }

}
