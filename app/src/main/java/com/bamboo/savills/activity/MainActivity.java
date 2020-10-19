package com.bamboo.savills.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.InjectView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bamboo.savills.R;
import com.bamboo.savills.base.utils.LogUtil;
import com.bamboo.savills.base.view.BaseActivity;
import com.bamboo.savills.fragment.JobFragment;
import com.bamboo.savills.fragment.SettingFragment;
import com.microsoft.identity.client.IAccount;
import com.microsoft.identity.client.IPublicClientApplication;
import com.microsoft.identity.client.ISingleAccountPublicClientApplication;
import com.microsoft.identity.client.PublicClientApplication;
import com.microsoft.identity.client.exception.MsalException;

public class MainActivity extends BaseActivity {
    @InjectView(R.id.rl_jobs)
    RelativeLayout rlJobs;

    @InjectView(R.id.rl_setting)
    RelativeLayout rlSetting;

    @InjectView(R.id.iv_jobs)
    ImageView ivJobs;

    @InjectView(R.id.iv_setting)
    ImageView ivSetting;

    @InjectView(R.id.tv_jobs)
    TextView tvJobs;

    @InjectView(R.id.tv_setting)
    TextView tvSetting;

    private JobFragment jobFragment ;
    private SettingFragment settingFragment;
    private FragmentManager fragmentManager;
    private final static String[] SCOPES = {"Files.Read"};
    /* Azure AD v2 Configs */
    final static String AUTHORITY = "https://login.microsoftonline.com/common";
    private ISingleAccountPublicClientApplication mSingleAccountApp;


    @Override
    public void initView() {
        fragmentManager = getSupportFragmentManager();
        setFragmentTag(0);
        PublicClientApplication.createSingleAccountPublicClientApplication(getApplicationContext(),
                R.raw.auth_config_single_account, new IPublicClientApplication.ISingleAccountApplicationCreatedListener() {
                    @Override
                    public void onCreated(ISingleAccountPublicClientApplication application) {
                        mSingleAccountApp = application;

                        loadAccount();
                    }
                    @Override
                    public void onError(MsalException exception) {

                        displayError(exception);
                    }
                });

    }
    private void loadAccount() {
        if (mSingleAccountApp == null) {
            return;
        }

        mSingleAccountApp.getCurrentAccountAsync(new ISingleAccountPublicClientApplication.CurrentAccountCallback() {
            @Override
            public void onAccountLoaded(@Nullable IAccount activeAccount) {
                // You can use the account data to update your UI or your app database.
//                updateUI(activeAccount);
            }

            @Override
            public void onAccountChanged(@Nullable IAccount priorAccount, @Nullable IAccount currentAccount) {
                if (currentAccount == null) {
                    // Perform a cleanup task as the signed-in account changed.
                    performOperationOnSignOut();
                }
            }

            @Override
            public void onError(@NonNull MsalException exception) {
                displayError(exception);
            }
        });
    }
    private void performOperationOnSignOut() {
        final String signOutText = "Log Out.";
        Toast.makeText(getApplicationContext(), signOutText, Toast.LENGTH_SHORT)
                .show();
    }
    private void displayError(@NonNull final Exception exception) {
        LogUtil.e("displayError",exception.toString());
    }

    @Override
    public void initListener() {
        rlJobs.setOnClickListener(this);
        rlSetting.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }
    private void setFragmentTag(int position) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideAllFragment(transaction);
        switch (position) {
            case 0:
                if (jobFragment == null) {
                    jobFragment = new JobFragment();
                    transaction.add(R.id.home_fragment, jobFragment, "jobFragment");
                } else {
                    transaction.show(jobFragment);
                }
                break;
            case 1:
                if (settingFragment == null) {
                    settingFragment = new SettingFragment();
                    transaction.add(R.id.home_fragment, settingFragment, "settingFragment");
                } else {
                    transaction.show(settingFragment);
                }
                break;
        }
        transaction.commit();

    }
    private void hideAllFragment(FragmentTransaction transaction) {

        if (jobFragment != null) {
            transaction.hide(jobFragment);
        }
        if (settingFragment != null) {
            transaction.hide(settingFragment);
        }

    }
    public void logOut(){
        if (mSingleAccountApp == null){
            return;
        }
        mSingleAccountApp.signOut(new ISingleAccountPublicClientApplication.SignOutCallback() {
            @Override
            public void onSignOut() {
                performOperationOnSignOut();
                //跳转登录界面
                Intent intent = new Intent(mContext,LoginActivity.class);
                startActivity(intent);
                finish();
            }
            @Override
            public void onError(@NonNull MsalException exception){
                displayError(exception);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClickNext(View v) {
        switch (v.getId()){
            case R.id.rl_jobs:
                ivJobs.setImageResource(R.drawable.job_select);
                tvJobs.setTextColor(mContext.getResources().getColor(R.color.colorTextSelect));
                ivSetting.setImageResource(R.drawable.setting_select_un);
                tvSetting.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                setFragmentTag(0);
                break;
            case R.id.rl_setting:
                ivJobs.setImageResource(R.drawable.job_select_un);
                tvJobs.setTextColor(mContext.getResources().getColor(R.color.colorTextSelectUn));
                ivSetting.setImageResource(R.drawable.setting_select);
                tvSetting.setTextColor(mContext.getResources().getColor(R.color.colorTextSelect));
                setFragmentTag(1);
                break;
        }

    }
}
