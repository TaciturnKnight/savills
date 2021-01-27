package com.bamboo.savills.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.InjectView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bamboo.savills.Constant;
import com.bamboo.savills.Module.FormBList;
import com.bamboo.savills.Module.JobModule;
import com.bamboo.savills.Module.PartAAnswer;
import com.bamboo.savills.Module.PartBAnswer;
import com.bamboo.savills.Module.PartBDetail;
import com.bamboo.savills.Module.SimpleResponse;
import com.bamboo.savills.R;
import com.bamboo.savills.base.net.HttpUtil;
import com.bamboo.savills.base.net.NetCallback;
import com.bamboo.savills.base.net.RequstList;
import com.bamboo.savills.base.utils.LogUtil;
import com.bamboo.savills.base.utils.StringUtil;
import com.bamboo.savills.base.view.BaseActivity;
import com.bamboo.savills.base.view.ToastUtil;
import com.bamboo.savills.inter.SubmitCallBack;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.microsoft.graph.requests.extensions.IIosDeviceFeaturesConfigurationRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PartBQuestionUpdateActivity extends BaseActivity {

    @InjectView(R.id.iv_back)
    ImageView ivBack;

    @InjectView(R.id.tv_title)
    TextView tvTitle;

    @InjectView(R.id.iv_send)
    ImageView ivSend;

    @InjectView(R.id.tv_head_view_floor_job_no)
    TextView tvJobNo;

    @InjectView(R.id.tv_head_view_floor_inspector)
    TextView tvInspector;

    @InjectView(R.id.tv_head_view_floor_street_name)
    TextView tvAdd;

    @InjectView(R.id.tv_head_view_floor_create_date)
    TextView tvDate;

    @InjectView(R.id.iv_fb_photo)
    ImageView ivPhoto;

    @InjectView(R.id.iv_fb_show)
    ImageView ivPhotoShow;

    @InjectView(R.id.rl_fb_q1a_s1)
    RelativeLayout rlq1as1;

    @InjectView(R.id.iv_fb_q1a_s1)
    ImageView ivq1as1;

    @InjectView(R.id.rl_fb_q1a_s2)
    RelativeLayout rlq1as2;

    @InjectView(R.id.iv_fb_q1a_s2)
    ImageView ivq1as2;

    @InjectView(R.id.rl_fb_q1a_s3)
    RelativeLayout rlq1as3;

    @InjectView(R.id.iv_fb_q1a_s3)
    ImageView ivq1as3;

    @InjectView(R.id.rl_fb_q1a_s4)
    RelativeLayout rlq1as4;

    @InjectView(R.id.iv_fb_q1a_s4)
    ImageView ivq1as4;

    @InjectView(R.id.rl_fb_q1a_s5)
    RelativeLayout rlq1as5;

    @InjectView(R.id.iv_fb_q1a_s5)
    ImageView ivq1as5;

    @InjectView(R.id.rl_fb_q1a_s6)
    RelativeLayout rlq1as6;

    @InjectView(R.id.iv_fb_q1a_s6)
    ImageView ivq1as6;

    @InjectView(R.id.rl_fb_q1a_s7)
    RelativeLayout rlq1as7;

    @InjectView(R.id.iv_fb_q1a_s7)
    ImageView ivq1as7;

    @InjectView(R.id.et_fb_q1a)
    EditText etq1a;

    @InjectView(R.id.rl_fb_q1b_s1)
    RelativeLayout rlq1bs1;

    @InjectView(R.id.iv_fb_q1b_s1)
    ImageView ivq1bs1;

    @InjectView(R.id.rl_fb_q1b_s2)
    RelativeLayout rlq1bs2;

    @InjectView(R.id.iv_fb_q1b_s2)
    ImageView ivq1bs2;

    @InjectView(R.id.rl_fb_q1b_s3)
    RelativeLayout rlq1bs3;

    @InjectView(R.id.iv_fb_q1b_s3)
    ImageView ivq1bs3;

    @InjectView(R.id.rl_fb_q1b_s4)
    RelativeLayout rlq1bs4;

    @InjectView(R.id.iv_fb_q1b_s4)
    ImageView ivq1bs4;

    @InjectView(R.id.rl_fb_q1b_s5)
    RelativeLayout rlq1bs5;

    @InjectView(R.id.iv_fb_q1b_s5)
    ImageView ivq1bs5;

    @InjectView(R.id.et_fb_q1b)
    EditText etq1b;

    @InjectView(R.id.rl_fb_q1c_s1)
    RelativeLayout rlq1cs1;

    @InjectView(R.id.iv_fb_q1c_s1)
    ImageView ivq1cs1;

    @InjectView(R.id.rl_fb_q1c_s2)
    RelativeLayout rlq1cs2;

    @InjectView(R.id.iv_fb_q1c_s2)
    ImageView ivq1cs2;

    @InjectView(R.id.rl_fb_q1c_s3)
    RelativeLayout rlq1cs3;

    @InjectView(R.id.iv_fb_q1c_s3)
    ImageView ivq1cs3;

    @InjectView(R.id.rl_fb_q1c_s4)
    RelativeLayout rlq1cs4;

    @InjectView(R.id.iv_fb_q1c_s4)
    ImageView ivq1cs4;

    @InjectView(R.id.et_fb_q1c)
    EditText etq1c;

    @InjectView(R.id.rl_fb_q2_s1)
    RelativeLayout rlq2s1;

    @InjectView(R.id.iv_fb_q2_s1)
    ImageView ivq2s1;

    @InjectView(R.id.rl_fb_q2_s2)
    RelativeLayout rlq2s2;

    @InjectView(R.id.iv_fb_q2_s2)
    ImageView ivq2s2;

    @InjectView(R.id.rl_fb_q2_s3)
    RelativeLayout rlq2s3;

    @InjectView(R.id.iv_fb_q2_s3)
    ImageView ivq2s3;

    @InjectView(R.id.rl_fb_q2_s4)
    RelativeLayout rlq2s4;

    @InjectView(R.id.iv_fb_q2_s4)
    ImageView ivq2s4;

    @InjectView(R.id.rl_fb_q2_s5)
    RelativeLayout rlq2s5;

    @InjectView(R.id.iv_fb_q2_s5)
    ImageView ivq2s5;

    @InjectView(R.id.et_fb_q2)
    EditText etq2;

    @InjectView(R.id.et_fb_q3)
    EditText etq3;

    @InjectView(R.id.et_fb_q4)
    EditText etq4;

    @InjectView(R.id.rl_fb_q4b_s1)
    RelativeLayout rlq4bs1;

    @InjectView(R.id.iv_fb_q4b_s1)
    ImageView ivq4bs1;

    @InjectView(R.id.rl_fb_q4b_s2)
    RelativeLayout rlq4bs2;

    @InjectView(R.id.iv_fb_q4b_s2)
    ImageView ivq4bs2;

    @InjectView(R.id.rl_fb_q4b_s3)
    RelativeLayout rlq4bs3;

    @InjectView(R.id.iv_fb_q4b_s3)
    ImageView ivq4bs3;

    @InjectView(R.id.rl_fb_q4b_s4)
    RelativeLayout rlq4bs4;

    @InjectView(R.id.iv_fb_q4b_s4)
    ImageView ivq4bs4;

    @InjectView(R.id.rl_fb_q4b_s5)
    RelativeLayout rlq4bs5;

    @InjectView(R.id.iv_fb_q4b_s5)
    ImageView ivq4bs5;

    @InjectView(R.id.rl_fb_q4b_s6)
    RelativeLayout rlq4bs6;

    @InjectView(R.id.iv_fb_q4b_s6)
    ImageView ivq4bs6;

    @InjectView(R.id.rl_fb_q4b_s7)
    RelativeLayout rlq4bs7;

    @InjectView(R.id.iv_fb_q4b_s7)
    ImageView ivq4bs7;

    @InjectView(R.id.rl_fb_q4b_s8)
    RelativeLayout rlq4bs8;

    @InjectView(R.id.iv_fb_q4b_s8)
    ImageView ivq4bs8;


    @InjectView(R.id.et_fb_q5)
    EditText etq5;

    @InjectView(R.id.rl_fb_q6_s1)
    RelativeLayout rlq6s1;

    @InjectView(R.id.iv_fb_q6_s1)
    ImageView ivq6s1;

    @InjectView(R.id.rl_fb_q6_s2)
    RelativeLayout rlq6s2;

    @InjectView(R.id.iv_fb_q6_s2)
    ImageView ivq6s2;


    @InjectView(R.id.rl_fb_q6_s3)
    RelativeLayout rlq6s3;

    @InjectView(R.id.iv_fb_q6_s3)
    ImageView ivq6s3;

    @InjectView(R.id.rl_fb_q6_s4)
    RelativeLayout rlq6s4;

    @InjectView(R.id.iv_fb_q6_s4)
    ImageView ivq6s4;

    @InjectView(R.id.rl_fb_q6_s5)
    RelativeLayout rlq6s5;

    @InjectView(R.id.iv_fb_q6_s5)
    ImageView ivq6s5;

    @InjectView(R.id.et_fb_q6)
    EditText etq6;

    @InjectView(R.id.rl_fb_q7_s1)
    RelativeLayout rlq7s1;

    @InjectView(R.id.iv_fb_q7_s1)
    ImageView ivq7s1;

    @InjectView(R.id.rl_fb_q7_s2)
    RelativeLayout rlq7s2;

    @InjectView(R.id.iv_fb_q7_s2)
    ImageView ivq7s2;

    @InjectView(R.id.rl_fb_q7_s3)
    RelativeLayout rlq7s3;

    @InjectView(R.id.iv_fb_q7_s3)
    ImageView ivq7s3;

    @InjectView(R.id.rl_fb_q7_s4)
    RelativeLayout rlq7s4;

    @InjectView(R.id.iv_fb_q7_s4)
    ImageView ivq7s4;

    @InjectView(R.id.rl_fb_q7_s5)
    RelativeLayout rlq7s5;

    @InjectView(R.id.iv_fb_q7_s5)
    ImageView ivq7s5;

    @InjectView(R.id.rl_fb_q7_s6)
    RelativeLayout rlq7s6;

    @InjectView(R.id.iv_fb_q7_s6)
    ImageView ivq7s6;

    @InjectView(R.id.et_fb_q7)
    EditText etq7;

    @InjectView(R.id.et_fb_q8)
    EditText etq8;

    @InjectView(R.id.tv_fb_page1_save)
    TextView tvPage1Save;

    @InjectView(R.id.tv_fb_page1_complete)
    TextView tvNext;

    @InjectView(R.id.tv_fb_page2_save)
    TextView tvPage2Save;

    @InjectView(R.id.tv_fb_page2_complete)
    TextView tvPage2Submit;

    @InjectView(R.id.form_b_page1)
    View page1;

    @InjectView(R.id.form_b_page2)
    View page2;

    @InjectView(R.id.ll_location_fb_page1)
    LinearLayout llPoint1;

    @InjectView(R.id.ll_location_fb_page2)
    LinearLayout llPoint2;

    private PartBAnswer formBsBean;
    private int jobId;
    private List<ImageView> ivPoints1 = new ArrayList<>();
    private List<ImageView> ivPoints2 = new ArrayList<>();

    private int page = 1;
    private JobModule mJobModle;

    private List<RelativeLayout> rlQ1a = new ArrayList<>();
    private List<ImageView> ivQ1a = new ArrayList<>();

    private List<RelativeLayout> rlQ1b = new ArrayList<>();
    private List<ImageView> ivQ1b = new ArrayList<>();

    private List<RelativeLayout> rlQ1c = new ArrayList<>();
    private List<ImageView> ivQ1c = new ArrayList<>();

    private List<RelativeLayout> rlQ2 = new ArrayList<>();
    private List<ImageView> ivQ2 = new ArrayList<>();

    private List<RelativeLayout> rlQ4b = new ArrayList<>();
    private List<ImageView> ivQ4b = new ArrayList<>();

    private List<RelativeLayout> rlQ6 = new ArrayList<>();
    private List<ImageView> ivQ6 = new ArrayList<>();

    private List<RelativeLayout> rlQ7 = new ArrayList<>();
    private List<ImageView> ivQ7 = new ArrayList<>();

    //多选的记录
    private List<Integer> q1aSelect = new ArrayList<>();
    private List<Integer> q1bSelect = new ArrayList<>();
    private List<Integer> q1cSelect = new ArrayList<>();

    private List<Integer> q6Select = new ArrayList<>();
    private List<Integer> q7Select = new ArrayList<>();

    private PartBAnswer mPartBAnswer;
    private int viewType = 0;//0 可编辑状态 1为不可编辑状态




    @Override
    public void initView() {
        ivSend.setVisibility(View.INVISIBLE);
        formBsBean = new Gson().fromJson(getIntent().getStringExtra("formBsBean"),new TypeToken<PartBAnswer>(){}.getType());
        jobId = getIntent().getIntExtra("jobId",0);
        viewType = getIntent().getIntExtra("View",0);
        String title = formBsBean.getJobFileName().split("\\.")[0]+formBsBean.getTitle();
        tvTitle.setText(title);
        String jobModule = getIntent().getStringExtra("JobModule");
        if (StringUtil.isNotEmpty(jobModule)) {
            mJobModle = new Gson().fromJson(jobModule, new TypeToken<JobModule>() {
            }.getType());
            LogUtil.loge("JobModule",jobModule);
        }
        if (StringUtil.isNotEmpty(mJobModle.getJob().getJobNo())){
            tvJobNo.setText(mJobModle.getJob().getJobNo());
        }
        if (StringUtil.isNotEmpty(mJobModle.getPropertyName())){
            tvInspector.setText(mJobModle.getPropertyName());
        }
        if (StringUtil.isNotEmpty(mJobModle.getStreet1())){
            tvAdd.setText(mJobModle.getStreet1());
        }
        if (StringUtil.isNotEmpty(mJobModle.getCreatedOn())){
            tvDate.setText(mJobModle.getCreatedOn());
        }
        initChoiceView();
        setDisEditState();
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

        rlQ1b.add(rlq1bs1);
        ivQ1b.add(ivq1bs1);

        rlQ1b.add(rlq1bs2);
        ivQ1b.add(ivq1bs2);

        rlQ1b.add(rlq1bs3);
        ivQ1b.add(ivq1bs3);

        rlQ1b.add(rlq1bs4);
        ivQ1b.add(ivq1bs4);

        rlQ1b.add(rlq1bs5);
        ivQ1b.add(ivq1bs5);

        rlQ1c.add(rlq1cs1);
        ivQ1c.add(ivq1cs1);

        rlQ1c.add(rlq1cs2);
        ivQ1c.add(ivq1cs2);

        rlQ1c.add(rlq1cs3);
        ivQ1c.add(ivq1cs3);

        rlQ1c.add(rlq1cs4);
        ivQ1c.add(ivq1cs4);

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

        rlQ4b.add(rlq4bs1);
        ivQ4b.add(ivq4bs1);

        rlQ4b.add(rlq4bs2);
        ivQ4b.add(ivq4bs2);

        rlQ4b.add(rlq4bs3);
        ivQ4b.add(ivq4bs3);

        rlQ4b.add(rlq4bs4);
        ivQ4b.add(ivq4bs4);

        rlQ4b.add(rlq4bs5);
        ivQ4b.add(ivq4bs5);

        rlQ4b.add(rlq4bs6);
        ivQ4b.add(ivq4bs6);

        rlQ4b.add(rlq4bs7);
        ivQ4b.add(ivq4bs7);

        rlQ4b.add(rlq4bs8);
        ivQ4b.add(ivq4bs8);


        rlQ6.add(rlq6s1);
        ivQ6.add(ivq6s1);

        rlQ6.add(rlq6s2);
        ivQ6.add(ivq6s2);

        rlQ6.add(rlq6s3);
        ivQ6.add(ivq6s3);

        rlQ6.add(rlq6s4);
        ivQ6.add(ivq6s4);

        rlQ6.add(rlq6s5);
        ivQ6.add(ivq6s5);

        rlQ7.add(rlq7s1);
        ivQ7.add(ivq7s1);

        rlQ7.add(rlq7s2);
        ivQ7.add(ivq7s2);

        rlQ7.add(rlq7s3);
        ivQ7.add(ivq7s3);

        rlQ7.add(rlq7s4);
        ivQ7.add(ivq7s4);

        rlQ7.add(rlq7s5);
        ivQ7.add(ivq7s5);

        rlQ7.add(rlq7s6);
        ivQ7.add(ivq7s6);

    }

    @Override
    public void initListener() {
        tvNext.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        ivPhoto.setOnClickListener(this);
        ivPhotoShow.setOnClickListener(this);
        llPoint1.setOnClickListener(this);
        llPoint2.setOnClickListener(this);
        initChoicesListener();
        initQ1aEditListener();
        initQ1bEditListener();
        initQ1cEditListener();
        initQ2EditListener();
        initQ3EditListener();
        initQ4EditListener();
        initQ5EditListener();
        initQ6EditListener();
        initQ7EditListener();
        initQ8EditListener();
        tvPage1Save.setOnClickListener(this);
        tvPage2Save.setOnClickListener(this);
        tvPage2Save.setOnClickListener(this);
        tvPage2Submit.setOnClickListener(this);
    }

    private void setDisEditState(){
        switch (viewType){
            case 0:
                rlq1as1.setClickable(true);
                rlq1as2.setClickable(true);
                rlq1as3.setClickable(true);
                rlq1as4.setClickable(true);
                rlq1as5.setClickable(true);
                rlq1as6.setClickable(true);
                rlq1as7.setClickable(true);
                etq1a.setEnabled(true);
                rlq1bs1.setClickable(true);
                rlq1bs2.setClickable(true);
                rlq1bs3.setClickable(true);
                rlq1bs4.setClickable(true);
                rlq1bs5.setClickable(true);
                etq1b.setEnabled(true);
                rlq1cs1.setClickable(true);
                rlq1cs2.setClickable(true);
                rlq1cs3.setClickable(true);
                rlq1cs4.setClickable(true);
                etq1c.setEnabled(true);
                rlq2s1.setClickable(true);
                rlq2s2.setClickable(true);
                rlq2s3.setClickable(true);
                rlq2s4.setClickable(true);
                rlq2s5.setClickable(true);
                etq2.setEnabled(true);
                etq3.setEnabled(true);
                etq4.setEnabled(true);
                rlq4bs1.setClickable(true);
                rlq4bs2.setClickable(true);
                rlq4bs3.setClickable(true);
                rlq4bs4.setClickable(true);
                rlq4bs5.setClickable(true);
                rlq4bs6.setClickable(true);
                rlq4bs7.setClickable(true);
                rlq4bs8.setClickable(true);
                etq5.setEnabled(true);
                rlq6s1.setClickable(true);
                rlq6s2.setClickable(true);
                rlq6s3.setClickable(true);
                rlq6s4.setClickable(true);
                rlq6s5.setClickable(true);
                etq6.setEnabled(true);
                rlq7s1.setClickable(true);
                rlq7s2.setClickable(true);
                rlq7s3.setClickable(true);
                rlq7s4.setClickable(true);
                rlq7s5.setClickable(true);
                rlq7s6.setClickable(true);
                etq7.setEnabled(true);
                etq8.setEnabled(true);
                tvPage1Save.setVisibility(View.VISIBLE);
                tvPage2Save.setVisibility(View.VISIBLE);
                tvPage2Submit.setVisibility(View.VISIBLE);
                ivPhoto.setVisibility(View.VISIBLE);
                break;
            case 1:
                rlq1as1.setClickable(false);
                rlq1as2.setClickable(false);
                rlq1as3.setClickable(false);
                rlq1as4.setClickable(false);
                rlq1as5.setClickable(false);
                rlq1as6.setClickable(false);
                rlq1as7.setClickable(false);
                etq1a.setEnabled(false);
                rlq1bs1.setClickable(false);
                rlq1bs2.setClickable(false);
                rlq1bs3.setClickable(false);
                rlq1bs4.setClickable(false);
                rlq1bs5.setClickable(false);
                etq1b.setEnabled(false);
                rlq1cs1.setClickable(false);
                rlq1cs2.setClickable(false);
                rlq1cs3.setClickable(false);
                rlq1cs4.setClickable(false);
                etq1c.setEnabled(false);
                rlq2s1.setClickable(false);
                rlq2s2.setClickable(false);
                rlq2s3.setClickable(false);
                rlq2s4.setClickable(false);
                rlq2s5.setClickable(false);
                etq2.setEnabled(false);
                etq3.setEnabled(false);
                etq4.setEnabled(false);
                rlq4bs1.setClickable(false);
                rlq4bs2.setClickable(false);
                rlq4bs3.setClickable(false);
                rlq4bs4.setClickable(false);
                rlq4bs5.setClickable(false);
                rlq4bs6.setClickable(false);
                rlq4bs7.setClickable(false);
                rlq4bs8.setClickable(false);
                etq5.setEnabled(false);
                rlq6s1.setClickable(false);
                rlq6s2.setClickable(false);
                rlq6s3.setClickable(false);
                rlq6s4.setClickable(false);
                rlq6s5.setClickable(false);
                etq6.setEnabled(false);
                rlq7s1.setClickable(false);
                rlq7s2.setClickable(false);
                rlq7s3.setClickable(false);
                rlq7s4.setClickable(false);
                rlq7s5.setClickable(false);
                rlq7s6.setClickable(false);
                etq7.setEnabled(false);
                etq8.setEnabled(false);
                tvPage1Save.setVisibility(View.GONE);
                tvPage2Save.setVisibility(View.GONE);
                tvPage2Submit.setVisibility(View.GONE);
                ivPhoto.setVisibility(View.GONE);
                break;
        }
    }

    private void initChoicesListener(){
        rlq1as1.setOnClickListener(this);
        rlq1as2.setOnClickListener(this);
        rlq1as3.setOnClickListener(this);
        rlq1as4.setOnClickListener(this);
        rlq1as5.setOnClickListener(this);
        rlq1as6.setOnClickListener(this);
        rlq1as7.setOnClickListener(this);

        rlq1bs1.setOnClickListener(this);
        rlq1bs2.setOnClickListener(this);
        rlq1bs3.setOnClickListener(this);
        rlq1bs4.setOnClickListener(this);
        rlq1bs5.setOnClickListener(this);

        rlq1cs1.setOnClickListener(this);
        rlq1cs2.setOnClickListener(this);
        rlq1cs3.setOnClickListener(this);
        rlq1cs4.setOnClickListener(this);

        rlq2s1.setOnClickListener(this);
        rlq2s2.setOnClickListener(this);
        rlq2s3.setOnClickListener(this);
        rlq2s4.setOnClickListener(this);
        rlq2s5.setOnClickListener(this);

        rlq4bs1.setOnClickListener(this);
        rlq4bs2.setOnClickListener(this);
        rlq4bs3.setOnClickListener(this);
        rlq4bs4.setOnClickListener(this);
        rlq4bs5.setOnClickListener(this);
        rlq4bs6.setOnClickListener(this);
        rlq4bs7.setOnClickListener(this);
        rlq4bs8.setOnClickListener(this);

        rlq6s1.setOnClickListener(this);
        rlq6s2.setOnClickListener(this);
        rlq6s3.setOnClickListener(this);
        rlq6s4.setOnClickListener(this);
        rlq6s5.setOnClickListener(this);

        rlq7s1.setOnClickListener(this);
        rlq7s2.setOnClickListener(this);
        rlq7s3.setOnClickListener(this);
        rlq7s4.setOnClickListener(this);
        rlq7s5.setOnClickListener(this);
        rlq7s6.setOnClickListener(this);
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
                mPartBAnswer.setQuestion12FloorRemark(etq1a.getText().toString().trim());
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
                mPartBAnswer.setQuestion12WallsRemark(etq1b.getText().toString().trim());
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
                mPartBAnswer.setQuestion12CeilingRemark(etq1c.getText().toString().trim());
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
                mPartBAnswer.setQuestion13InternalConditionRemark(etq2.getText().toString().trim());
            }
        });
    }
    private void initQ3EditListener(){
        etq3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPartBAnswer.setQuestion14UnazuthrizedStructureOrAlteration(etq3.getText().toString().trim());
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
                mPartBAnswer.setQuestion15View(etq4.getText().toString().trim());
            }
        });
    }
    private void initQ5EditListener(){
        etq5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPartBAnswer.setQuestion16Usage(etq5.getText().toString().trim());
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
                mPartBAnswer.setQuestion17Remark(etq6.getText().toString().trim());
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
                mPartBAnswer.setQuestion18OccupationDetails(etq7.getText().toString().trim());
            }
        });
    }
    private void initQ8EditListener(){
        etq8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPartBAnswer.setQuestion19Remarks(etq8.getText().toString().trim());
            }
        });
    }


    @Override
    public void initData() {
        initPoint();
        getFormBDate();
    }

    private void getFormBDate(){
        showLoading();
        q1aSelect.clear();
        q1bSelect.clear();
        q1cSelect.clear();
        q6Select.clear();
        q7Select.clear();
        HttpUtil.getInstance().get(mContext, RequstList.GET_FORM_B_DETAIL + mJobModle.getJobId() + "/" + mJobModle.getId() + "/" + formBsBean.getId(), HttpUtil.JSON, 601, true, new NetCallback() {
            @Override
            public void onSuccess(int tag, String result) {
               LogUtil.loge("getFormBDate",result);
               PartBDetail partBDetail = new Gson().fromJson(result,new TypeToken<PartBDetail>(){}.getType());
               if (partBDetail != null && partBDetail.getCode() == 0&& partBDetail.getData()!= null){
                   mPartBAnswer = partBDetail.getData();
                   //设置值
                   dealRealData();
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

    private void useWhichData(){
        if (mPartBAnswer.isSubmitted()){
            viewType = 1;
            setDisEditState();
            spUtils.setIsSaveFormB(mPartBAnswer.getId(),false);
        }else {
            viewType = 0;
            setDisEditState();
        }
        try {
            boolean isSave = spUtils.getIsSaveFormB(mPartBAnswer.getId());
            if (isSave){
                //判断时间
                String json = spUtils.getSaveFormBDetail(mPartBAnswer.getId());
                PartBAnswer spAnswer = new Gson().fromJson(json,new TypeToken<PartBAnswer>(){}.getType());
                LogUtil.loge("useWhichData",json);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                Date date = sdf.parse(mPartBAnswer.getUpdatedOn());
                LogUtil.loge("useWhichData",spAnswer.getUpdatedOn()+"///"+date.getTime());
                if (date.getTime() > Long.parseLong(spAnswer.getUpdatedOn())){
//                网络获取的就是最新的
                    spUtils.setIsSaveFormB(mPartBAnswer.getId(),false);
                }else {
                    //本地的是最新的数据
//                step1保存 --->成功后 改成false ---》重新获取数据
                    mPartBAnswer = spAnswer;
                   saveFormB(false);
                }

            }

        }catch (Exception e){
            LogUtil.loge("useWhichData",e.getMessage());
        }
    }
    public static char[] reserve( char[] arr ){
        char[] arr1 = new char[arr.length];
        for( int x=0;x<arr.length;x++ ){
            arr1[x] = arr[arr.length-x-1];
        }
        return arr1 ;
    }

    private void dealRealData(){
        useWhichData();
//        设置数据到表格上
//        1a
        char[] chars1a = reserve(Integer.toBinaryString(mPartBAnswer.getQuestion12Floor()).toCharArray());
        for (int i = 0;i<chars1a.length;i++){
            if (chars1a[i]=='1'){
                //
                dealChoicesShow(i,q1aSelect,ivQ1a,rlQ1a);
            }
        }
        if (StringUtil.isNotEmpty(mPartBAnswer.getQuestion12FloorRemark()))
            etq1a.setText(mPartBAnswer.getQuestion12FloorRemark());
//        1b
        char[] chars1b = reserve(Integer.toBinaryString(mPartBAnswer.getQuestion12Walls()).toCharArray());
        for (int i = 0;i<chars1b.length;i++){
            if (chars1b[i]=='1'){
                //
                dealChoicesShow(i,q1bSelect,ivQ1b,rlQ1b);
            }
        }
        if (StringUtil.isNotEmpty(mPartBAnswer.getQuestion12WallsRemark()))
            etq1b.setText(mPartBAnswer.getQuestion12WallsRemark());
//        1c
        char[] chars1c = reserve(Integer.toBinaryString(mPartBAnswer.getQuestion12Ceiling()).toCharArray());
        for (int i = 0;i<chars1c.length;i++){
            if (chars1c[i]=='1'){
                //
                dealChoicesShow(i,q1cSelect,ivQ1c,rlQ1c);
            }
        }
        if (StringUtil.isNotEmpty(mPartBAnswer.getQuestion12CeilingRemark()))
            etq1c.setText(mPartBAnswer.getQuestion12CeilingRemark());
//        2
        dealSingleChoicesShow(mPartBAnswer.getQuestion13InternalCondition()-1,ivQ2,rlQ2);
        if (StringUtil.isNotEmpty(mPartBAnswer.getQuestion13InternalConditionRemark()))
            etq2.setText(mPartBAnswer.getQuestion13InternalConditionRemark());
//        3
        if (StringUtil.isNotEmpty(mPartBAnswer.getQuestion14UnazuthrizedStructureOrAlteration()))
            etq3.setText(mPartBAnswer.getQuestion14UnazuthrizedStructureOrAlteration());
//        4a
        if (StringUtil.isNotEmpty(mPartBAnswer.getQuestion15View()))
            etq4.setText(mPartBAnswer.getQuestion15View());

//        4b
        dealSingleChoicesShow(mPartBAnswer.getQuestion15Aspect()-1,ivQ4b,rlQ4b);
//        5
        if (StringUtil.isNotEmpty(mPartBAnswer.getQuestion16Usage()))
            etq5.setText(mPartBAnswer.getQuestion16Usage());
//        6
        char[] chars6 = reserve(Integer.toBinaryString(mPartBAnswer.getQuestion17()).toCharArray());
        for (int i = 0;i<chars6.length;i++){
            if (chars6[i]=='1'){
                //
                dealChoicesShow(i,q6Select,ivQ6,rlQ6);
            }
        }
        if (StringUtil.isNotEmpty(mPartBAnswer.getQuestion17Remark()))
            etq6.setText(mPartBAnswer.getQuestion17Remark());
//        7
        char[] chars7 = reserve(Integer.toBinaryString(mPartBAnswer.getQuestion18OccupationStatus()).toCharArray());
        for (int i = 0;i<chars7.length;i++){
            if (chars7[i]=='1'){
                //
                dealChoicesShow(i,q7Select,ivQ7,rlQ7);
            }
        }
        if (StringUtil.isNotEmpty(mPartBAnswer.getQuestion18OccupationDetails()))
            etq7.setText(mPartBAnswer.getQuestion18OccupationDetails());
//        8
        if (StringUtil.isNotEmpty(mPartBAnswer.getQuestion19Remarks()))
            etq8.setText(mPartBAnswer.getQuestion19Remarks());

        if (mPartBAnswer.isSubmitted()){
            //提交过了
            tvPage1Save.setVisibility(View.GONE);
            tvPage2Save.setVisibility(View.GONE);
            tvPage2Submit.setVisibility(View.GONE);
        }else {
            tvPage1Save.setVisibility(View.VISIBLE);
            tvPage2Save.setVisibility(View.VISIBLE);
            tvPage2Submit.setVisibility(View.VISIBLE);
        }
    }

    private void initPoint(){
//        分成三部分小圆点
        for (int i = 0;i<5;i++){
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
                case 4:
                    title = "3";
                    break;
            }
            tvCount.setText(title);
            llPoint1.addView(pointView);
        }
        for (int i = 0;i<5;i++){
            View pointView = LayoutInflater.from(mContext).inflate(R.layout.item_point,null);
            TextView tvCount = pointView.findViewById(R.id.tv_item_count);
            ImageView ivPoint = pointView.findViewById(R.id.iv_item_point);
            View vPoint = pointView.findViewById(R.id.v_item_point);
            ivPoint.setImageResource(R.drawable.img_grey);
            ivPoints2.add(ivPoint);
            if (i == 4 ){
                vPoint.setVisibility(View.INVISIBLE);
            }else {
                vPoint.setVisibility(View.VISIBLE);
            }
            String title = "1a";
            switch (i){
                case 0:
                    title = "4";
                    break;
                case 1:
                    title = "5";
                    break;
                case 2:
                    title = "6";
                    break;
                case 3:
                    title = "7";
                    break;
                case 4:
                    title = "8";
                    break;
            }
            tvCount.setText(title);
            llPoint2.addView(pointView);
        }

    }
    private void changePoints(){
//        让所有的球都置灰
        for (int i = 0;i<ivPoints1.size();i++){
            ivPoints1.get(i).setImageResource(R.drawable.img_grey);
        }
        for (int i = 0;i<ivPoints2.size();i++){
            ivPoints2.get(i).setImageResource(R.drawable.img_grey);
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
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_part_bquestion_update;
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
        int answer = mPartBAnswer.getQuestion12Floor();
        if (isHave){
            answer = (int)(answer-Math.pow(2,position));
        }else {
            answer = (int)(answer+Math.pow(2,position));
        }
        mPartBAnswer.setQuestion12Floor(answer);
    }

    private void dealQ1bAnswer(boolean isHave,int position){
        int answer = mPartBAnswer.getQuestion12Walls();
        if (isHave){
            answer = (int)(answer-Math.pow(2,position));
        }else {
            answer = (int)(answer+Math.pow(2,position));
        }
        mPartBAnswer.setQuestion12Walls(answer);
    }

    private void dealQ1cAnswer(boolean isHave,int position){
        int answer = mPartBAnswer.getQuestion12Ceiling();
        if (isHave){
            answer = (int)(answer-Math.pow(2,position));
        }else {
            answer = (int)(answer+Math.pow(2,position));
        }
        mPartBAnswer.setQuestion12Ceiling(answer);
    }

    private void dealQ6Answer(boolean isHave,int position){
        int answer = mPartBAnswer.getQuestion17();
        if (isHave){
            answer = (int)(answer-Math.pow(2,position));
        }else {
            answer = (int)(answer+Math.pow(2,position));
        }
        mPartBAnswer.setQuestion17(answer);
    }
    private void dealQ7Answer(boolean isHave,int position){
        int answer = mPartBAnswer.getQuestion18OccupationStatus();
        if (isHave){
            answer = (int)(answer-Math.pow(2,position));
        }else {
            answer = (int)(answer+Math.pow(2,position));
        }
        mPartBAnswer.setQuestion18OccupationStatus(answer);
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

    private void submitFormB(){
        showLoading();
        HttpUtil.getInstance().post(mContext, RequstList.SUBMIT_FORM_B + mJobModle.getJobId() + "/" + mJobModle.getId() + "/" + mPartBAnswer.getId(), 605, null, new NetCallback() {
            @Override
            public void onSuccess(int tag, String result) {
                LogUtil.loge("submitFormB",result);
                SimpleResponse simple = new Gson().fromJson(result,new TypeToken<SimpleResponse>(){}.getType());

                if (simple.getCode() == 0){
                    ToastUtil.showToast(mContext,"Submitted successfully");
//                    提醒前面刷新数据
                    Constant.isFormBListRefresh = true;
                    viewType = 1;
                    setDisEditState();
                    spUtils.setIsSaveFormB(mPartBAnswer.getId(),false);
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

    private void saveFormB(boolean isSubmit){
        showLoading();
        String json = new Gson().toJson(mPartBAnswer,new TypeToken<PartBAnswer>(){}.getType());
        HttpUtil.getInstance().postJson(mContext, RequstList.SAVE_FORM_B + mJobModle.getJobId() + "/" + mPartBAnswer.getId(), 602, json, new NetCallback() {
            @Override
            public void onSuccess(int tag, String result) {
                LogUtil.loge("saveFormB",result);
//                {"data":"1cc04705-8f82-49f9-ded1-08d8abc00753","code":0,"codeDesc":"OK"}
                SimpleResponse simple= new Gson().fromJson(result,new TypeToken<SimpleResponse>(){}.getType());
                if (simple!= null &&  simple.getCode() == 0){
                    //保存成功
                    ToastUtil.showToast(mContext,"Saved Successfully");
                    spUtils.setIsSaveFormB(mPartBAnswer.getId(),false);
                    if (isSubmit){
                        submitFormB();
                    }else {
                        getFormBDate();
                    }
                }else {
                    //失败了 要保存在本地
                    saveLocal();
                }
            }

            @Override
            public void onError(int tag, String msg) {
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
        mPartBAnswer.setUpdatedOn(String.valueOf(System.currentTimeMillis()));
        spUtils.setIsSaveFormB(mPartBAnswer.getId(),true);
        spUtils.setSaveFormBDetail(mPartBAnswer.getId(),new Gson().toJson(mPartBAnswer,new TypeToken<PartBAnswer>(){}.getType()));
        ToastUtil.showToast(mContext,"Network anomaly data saved locally");
    }

    private void testComplete(){
//        第2题---13 第7题---18是必填
        if (mPartBAnswer.getQuestion13InternalCondition()== 0){
            ToastUtil.showToast(mContext,"Please answer question 2 first.");
            return;
        }
        if (mPartBAnswer.getQuestion18OccupationStatus() <= 0){
            ToastUtil.showToast(mContext,"Please answer question 7 first.");
            return;
        }
//        第七题如果选择了others 那么填空必填
        if (isContains(5)&& StringUtil.isEmpty(mPartBAnswer.getQuestion18OccupationDetails())){
            ToastUtil.showToast(mContext,"Please answer question 7 first.\n Fill in the blank.");
            return;
        }
        saveFormB(true);
    }
    private boolean isContains(int position){
        char[] chars1a = reserve(Integer.toBinaryString(mPartBAnswer.getQuestion18OccupationStatus()).toCharArray());
        if (chars1a.length >position){
            return  chars1a[position] == '1';
        }else {
            return false;
        }

    }

    @Override
    public void onClickNext(View v) {
        switch (v.getId()){
            case R.id.tv_fb_page1_complete:
            case R.id.ll_location_fb_page2:
                page1.setVisibility(View.GONE);
                page2.setVisibility(View.VISIBLE);
                page = 2;
                changePoints();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_fb_photo:
                Intent intent = new Intent(mContext,PhotoActivity.class);
                intent.putExtra("jobId",jobId);
                intent.putExtra("formId",formBsBean.getId());
                startActivity(intent);
                break;
            case R.id.iv_fb_show:
                Intent intent1 = new Intent(mContext,PhotoShowActivity.class);
                intent1.putExtra("jobId",jobId);
                intent1.putExtra("formId",formBsBean.getId());
                startActivity(intent1);
                break;
            case R.id.ll_location_fb_page1:
                page1.setVisibility(View.VISIBLE);
                page2.setVisibility(View.GONE);
                page = 1;
                changePoints();
                break;
            case R.id.rl_fb_q1a_s1:
                boolean isHave = dealChoicesShow(0,q1aSelect,ivQ1a,rlQ1a);
                dealQ1aAnswer(isHave,0);
                break;
            case R.id.rl_fb_q1a_s2:
                boolean isHave1 = dealChoicesShow(1,q1aSelect,ivQ1a,rlQ1a);
                dealQ1aAnswer(isHave1,1);
                break;
            case R.id.rl_fb_q1a_s3:
                boolean isHave2 = dealChoicesShow(2,q1aSelect,ivQ1a,rlQ1a);
                dealQ1aAnswer(isHave2,2);
                break;
            case R.id.rl_fb_q1a_s4:
                boolean isHave3 = dealChoicesShow(3,q1aSelect,ivQ1a,rlQ1a);
                dealQ1aAnswer(isHave3,3);
                break;
            case R.id.rl_fb_q1a_s5:
                boolean isHave4 = dealChoicesShow(4,q1aSelect,ivQ1a,rlQ1a);
                dealQ1aAnswer(isHave4,4);
                break;
            case R.id.rl_fb_q1a_s6:
                boolean isHave5 = dealChoicesShow(5,q1aSelect,ivQ1a,rlQ1a);
                dealQ1aAnswer(isHave5,5);
                break;
            case R.id.rl_fb_q1a_s7:
                boolean isHave6 = dealChoicesShow(6,q1aSelect,ivQ1a,rlQ1a);
                dealQ1aAnswer(isHave6,6);
                break;
            case R.id.rl_fb_q1b_s1:
                boolean isHave1b = dealChoicesShow(0,q1bSelect,ivQ1b,rlQ1b);
                dealQ1bAnswer(isHave1b,0);
                break;
            case R.id.rl_fb_q1b_s2:
                boolean isHave1b1 = dealChoicesShow(1,q1bSelect,ivQ1b,rlQ1b);
                dealQ1bAnswer(isHave1b1,1);
                break;
            case R.id.rl_fb_q1b_s3:
                boolean isHave1b2 = dealChoicesShow(2,q1bSelect,ivQ1b,rlQ1b);
                dealQ1bAnswer(isHave1b2,2);
                break;
            case R.id.rl_fb_q1b_s4:
                boolean isHave1b3 = dealChoicesShow(3,q1bSelect,ivQ1b,rlQ1b);
                dealQ1bAnswer(isHave1b3,3);
                break;
            case R.id.rl_fb_q1b_s5:
                boolean isHave1b4 = dealChoicesShow(4,q1bSelect,ivQ1b,rlQ1b);
                dealQ1bAnswer(isHave1b4,4);
                break;
            case R.id.rl_fb_q1c_s1:
                boolean isHave1c = dealChoicesShow(0,q1cSelect,ivQ1c,rlQ1c);
                dealQ1cAnswer(isHave1c,0);
                break;
            case R.id.rl_fb_q1c_s2:
                boolean isHave1c1 = dealChoicesShow(1,q1cSelect,ivQ1c,rlQ1c);
                dealQ1cAnswer(isHave1c1,1);
                break;

            case R.id.rl_fb_q1c_s3:
                boolean isHave1c2 = dealChoicesShow(2,q1cSelect,ivQ1c,rlQ1c);
                dealQ1cAnswer(isHave1c2,2);
                break;

            case R.id.rl_fb_q1c_s4:
                boolean isHave1c3 = dealChoicesShow(3,q1cSelect,ivQ1c,rlQ1c);
                dealQ1cAnswer(isHave1c3,3);
                break;
            case R.id.rl_fb_q2_s1:
                dealSingleChoicesShow(0,ivQ2,rlQ2);
                mPartBAnswer.setQuestion13InternalCondition(1);
                break;
            case R.id.rl_fb_q2_s2:
                dealSingleChoicesShow(1,ivQ2,rlQ2);
                mPartBAnswer.setQuestion13InternalCondition(2);
                break;
            case R.id.rl_fb_q2_s3:
                dealSingleChoicesShow(2,ivQ2,rlQ2);
                mPartBAnswer.setQuestion13InternalCondition(3);
                break;
            case R.id.rl_fb_q2_s4:
                dealSingleChoicesShow(3,ivQ2,rlQ2);
                mPartBAnswer.setQuestion13InternalCondition(4);
                break;
            case R.id.rl_fb_q2_s5:
                dealSingleChoicesShow(4,ivQ2,rlQ2);
                mPartBAnswer.setQuestion13InternalCondition(5);
                break;
            case R.id.rl_fb_q4b_s1:
                dealSingleChoicesShow(0,ivQ4b,rlQ4b);
                mPartBAnswer.setQuestion15Aspect(1);
                break;
            case R.id.rl_fb_q4b_s2:
                dealSingleChoicesShow(1,ivQ4b,rlQ4b);
                mPartBAnswer.setQuestion15Aspect(2);
                break;
            case R.id.rl_fb_q4b_s3:
                dealSingleChoicesShow(2,ivQ4b,rlQ4b);
                mPartBAnswer.setQuestion15Aspect(3);
                break;
            case R.id.rl_fb_q4b_s4:
                dealSingleChoicesShow(3,ivQ4b,rlQ4b);
                mPartBAnswer.setQuestion15Aspect(4);
                break;
            case R.id.rl_fb_q4b_s5:
                dealSingleChoicesShow(4,ivQ4b,rlQ4b);
                mPartBAnswer.setQuestion15Aspect(5);
                break;
            case R.id.rl_fb_q4b_s6:
                dealSingleChoicesShow(5,ivQ4b,rlQ4b);
                mPartBAnswer.setQuestion15Aspect(6);
                break;
            case R.id.rl_fb_q4b_s7:
                dealSingleChoicesShow(6,ivQ4b,rlQ4b);
                mPartBAnswer.setQuestion15Aspect(7);
                break;
            case R.id.rl_fb_q4b_s8:
                dealSingleChoicesShow(7,ivQ4b,rlQ4b);
                mPartBAnswer.setQuestion15Aspect(8);
                break;
            case R.id.rl_fb_q6_s1:
                boolean isHave60 = dealChoicesShow(0,q6Select,ivQ6,rlQ6);
                dealQ6Answer(isHave60,0);
                break;
            case R.id.rl_fb_q6_s2:
                boolean isHave61 = dealChoicesShow(1,q6Select,ivQ6,rlQ6);
                dealQ6Answer(isHave61,1);
                break;
            case R.id.rl_fb_q6_s3:
                boolean isHave62 = dealChoicesShow(2,q6Select,ivQ6,rlQ6);
                dealQ6Answer(isHave62,2);
                break;
            case R.id.rl_fb_q6_s4:
                boolean isHave63 = dealChoicesShow(3,q6Select,ivQ6,rlQ6);
                dealQ6Answer(isHave63,3);
                break;
            case R.id.rl_fb_q6_s5:
                boolean isHave64 = dealChoicesShow(4,q6Select,ivQ6,rlQ6);
                dealQ6Answer(isHave64,4);
                break;
            case R.id.rl_fb_q7_s1:
                boolean isHave70 = dealChoicesShow(0,q7Select,ivQ7,rlQ7);
                dealQ7Answer(isHave70,0);
                break;
            case R.id.rl_fb_q7_s2:
                boolean isHave71 = dealChoicesShow(1,q7Select,ivQ7,rlQ7);
                dealQ7Answer(isHave71,1);
                break;
            case R.id.rl_fb_q7_s3:
                boolean isHave72 = dealChoicesShow(2,q7Select,ivQ7,rlQ7);
                dealQ7Answer(isHave72,2);
                break;
            case R.id.rl_fb_q7_s4:
                boolean isHave73 = dealChoicesShow(3,q7Select,ivQ7,rlQ7);
                dealQ7Answer(isHave73,3);
                break;
            case R.id.rl_fb_q7_s5:
                boolean isHave74 = dealChoicesShow(4,q7Select,ivQ7,rlQ7);
                dealQ7Answer(isHave74,4);
                break;
            case R.id.rl_fb_q7_s6:
                boolean isHave75 = dealChoicesShow(5,q7Select,ivQ7,rlQ7);
                dealQ7Answer(isHave75,5);
                break;
            case R.id.tv_fb_page1_save:
            case R.id.tv_fb_page2_save:
                saveFormB(false);
                break;
            case R.id.tv_fb_page2_complete:

                testComplete();
                break;



        }

    }
}
