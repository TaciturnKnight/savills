package com.bamboo.savills.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bamboo.savills.Module.UserBack;
import com.bamboo.savills.Module.UserInfo;
import com.bamboo.savills.R;
import com.bamboo.savills.base.utils.LogUtil;
import com.bamboo.savills.base.view.BaseApplication;
import com.bamboo.savills.base.view.LoadingDialog;
import com.bamboo.savills.base.view.LoginFailDialog;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.microsoft.graph.authentication.IAuthenticationProvider; //Imports the Graph sdk Auth interface
import com.microsoft.graph.concurrency.ICallback;
import com.microsoft.graph.core.ClientException;
import com.microsoft.graph.http.IHttpRequest;
import com.microsoft.graph.models.extensions.*;
import com.microsoft.graph.requests.extensions.GraphServiceClient;
import com.microsoft.identity.client.AuthenticationCallback; // Imports MSAL auth methods
import com.microsoft.identity.client.*;
import com.microsoft.identity.client.PublicClientApplication;
import com.microsoft.identity.client.exception.*;

public class LoginActivity extends AppCompatActivity {
    private final static String[] SCOPES = {"Files.Read"};
    /* Azure AD v2 Configs */
    final static String AUTHORITY = "https://login.microsoftonline.com/common";
    private ISingleAccountPublicClientApplication mSingleAccountApp;

    private static final String TAG = LoginActivity.class.getSimpleName();

    /* UI & Debugging Variables */
    TextView signInButton;
    Button signOutButton;
    Button callGraphApiInteractiveButton;
    Button callGraphApiSilentButton;
    TextView logTextView;
    TextView currentUserTextView;
    Button signIn2Btn;
    TextView tvAccont;
    LoadingDialog loadingDialog;
    LinearLayout llSec;
    private LoginFailDialog loginFailDialog ;
//    private boolean isHaveToken = false;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 99:
                    String exception = (String) msg.obj;
//                    llSec.setVisibility(View.VISIBLE);
                    loginFailDialog.setReason(exception);
                    loginFailDialog.show();
//                    logTextView.setText(exception);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        llSec = findViewById(R.id.ll_login_sec);
        loginFailDialog = new LoginFailDialog(this);

        initializeUI();

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
    //When app comes to the foreground, load existing account to determine if user is signed in
    private void loadAccount() {
        if (mSingleAccountApp == null) {
            return;
        }

        mSingleAccountApp.getCurrentAccountAsync(new ISingleAccountPublicClientApplication.CurrentAccountCallback() {
            @Override
            public void onAccountLoaded(@Nullable IAccount activeAccount) {
                // You can use the account data to update your UI or your app database.
                updateUI(activeAccount);
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
//    侦听按钮并相应地调用方法或日志错误。
    private void initializeUI(){
        loadingDialog = new LoadingDialog(LoginActivity.this);
        signInButton = findViewById(R.id.login);
        tvAccont = findViewById(R.id.tv_account);
        signIn2Btn = findViewById(R.id.login2);

        callGraphApiSilentButton = findViewById(R.id.callGraphSilent);
        callGraphApiInteractiveButton = findViewById(R.id.callGraphInteractive);
        signOutButton = findViewById(R.id.clearCache);
        logTextView = findViewById(R.id.txt_log);
        currentUserTextView = findViewById(R.id.current_user);


        //Sign in user
        signInButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                loadingDialog.show();
                String  tag = (String)v.getTag();
                if ("loginAgain".equalsIgnoreCase(tag)){
                    //有账户
                    if (mSingleAccountApp == null) {
                        return;
                    }
                    mSingleAccountApp.acquireToken(LoginActivity.this, SCOPES, getAuthInteractiveCallback());

//                    if (mSingleAccountApp == null){
//                        return;
//                    }
//                    mSingleAccountApp.acquireTokenSilentAsync(SCOPES, AUTHORITY, getAuthSilentCallback());
//                    String[] newScopes={"api://15df8d38-ad1a-454f-ae12-edbce9f8f858/access_as_user"};
//                    mSingleAccountApp.acquireTokenSilentAsync(newScopes, AUTHORITY, getAuthSilentCallback());

                }else {
//                    无账户
                    if (mSingleAccountApp == null) {
                        return;
                    }
                    mSingleAccountApp.signIn(LoginActivity.this, null, SCOPES, getAuthInteractiveCallback());

                }
                }
        });
        signIn2Btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
//                ToastUtil.showToast(LoginActivity.this,"1111111");
                if (mSingleAccountApp == null) {
                    return;
                }
                mSingleAccountApp.signIn(LoginActivity.this, null, SCOPES, getAuthInteractiveCallback());
            }
        });

        //Sign out user
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSingleAccountApp == null){
                    return;
                }
                mSingleAccountApp.signOut(new ISingleAccountPublicClientApplication.SignOutCallback() {
                    @Override
                    public void onSignOut() {
                        updateUI(null);
                        performOperationOnSignOut();
                    }
                    @Override
                    public void onError(@NonNull MsalException exception){
                        displayError(exception);
                    }
                });
            }
        });

        //Interactive
        callGraphApiInteractiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSingleAccountApp == null) {
                    return;
                }
                mSingleAccountApp.acquireToken(LoginActivity.this, SCOPES, getAuthInteractiveCallback());
            }
        });

        //Silent
        callGraphApiSilentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSingleAccountApp == null){
                    return;
                }
                mSingleAccountApp.acquireTokenSilentAsync(SCOPES, AUTHORITY, getAuthSilentCallback());
            }
        });
    }
//    用于交互式请求的回调
    private AuthenticationCallback getAuthInteractiveCallback() {
        return new AuthenticationCallback() {
            @Override
            public void onSuccess(IAuthenticationResult authenticationResult) {
                /* Successfully got a token, use it to call a protected resource - MSGraph */
                Log.d(TAG, "Successfully authenticated");
                /* Update UI */
                updateUI(authenticationResult.getAccount());
                /* call graph */
                callGraphAPI(authenticationResult);
            }

            @Override
            public void onError(MsalException exception) {
                /* Failed to acquireToken */
                Log.d(TAG, "Authentication failed: " + exception.toString());
                displayError(exception);
                loadingDialog.dismiss();
            }
            @Override
            public void onCancel() {
                /* User canceled the authentication */
                Log.d(TAG, "User cancelled login.");
                loadingDialog.dismiss();
            }
        };
    }
//    用于无提示请求的回调
    private SilentAuthenticationCallback getAuthSilentCallback() {
        return new SilentAuthenticationCallback() {
            @Override
            public void onSuccess(IAuthenticationResult authenticationResult) {
                Log.d(TAG, "Successfully authenticated");
                callGraphAPI(authenticationResult);
            }
            @Override
            public void onError(MsalException exception) {
                Log.d(TAG, "Authentication failed: " + exception.toString());
                displayError(exception);
            }
        };
    }
//    以下代码演示如何使用 Graph SDK 调用 GraphAPI。
    private void callGraphAPI(IAuthenticationResult authenticationResult) {

        final String accessToken = authenticationResult.getAccessToken();
//        if (isHaveToken){
//            BaseApplication.token = "Bearer " +accessToken;
//            LogUtil.e("/////",BaseApplication.token);
//        }
        IGraphServiceClient graphClient =
                GraphServiceClient
                        .builder()
                        .authenticationProvider(new IAuthenticationProvider() {
                            @Override
                            public void authenticateRequest(IHttpRequest request) {
                                Log.d(TAG, "Authenticating request," + request.getRequestUrl());
                                request.addHeader("Authorization", "Bearer " + accessToken);
                            }
                        })
                        .buildClient();
        graphClient
                .me()
                .drive()
                .buildRequest()
                .get(new ICallback<Drive>() {
                    @Override
                    public void success(final Drive drive) {
                        Log.d(TAG, "Found Drive " + drive.id);
                        displayGraphResult(drive.getRawObject());
                    }

                    @Override
                    public void failure(ClientException ex) {

                        displayError(ex);
                        loadingDialog.dismiss();
                    }
                });
    }
//    根据登录状态启用/禁用按钮，并设置文本。
    private void updateUI(@Nullable final IAccount account) {
        if (account != null) {
//            signInButton.setClickable(false);
//            signOutButton.setEnabled(true);
//            callGraphApiInteractiveButton.setEnabled(true);
//            callGraphApiSilentButton.setEnabled(true);
            tvAccont.setText(account.getUsername());
            signInButton.setTag("loginAgain");
        } else {
//            signInButton.setClickable(true);
//            signOutButton.setEnabled(false);
//            callGraphApiInteractiveButton.setEnabled(false);
//            callGraphApiSilentButton.setEnabled(false);
            tvAccont.setText("");
            signInButton.setTag("login");
//            logTextView.setText("");
        }
    }
    private void displayError(@NonNull final Exception exception) {
        Message msg = Message.obtain();
        msg.what = 99;
        msg.obj = exception.toString();
        mHandler.sendMessage(msg);
    }
    private SilentAuthenticationCallback callToken(){
        return new SilentAuthenticationCallback() {
            @Override
            public void onSuccess(IAuthenticationResult authenticationResult) {
                Log.d(TAG, "Successfully authenticated");
                final String accessToken = authenticationResult.getAccessToken();

                BaseApplication.token = "Bearer " +accessToken;
                LogUtil.e("/////",BaseApplication.token);
//                BaseApplication.userInfo = new Gson().fromJson(graphResponse.toString(),new TypeToken<UserInfo>(){}.getType());
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
            @Override
            public void onError(MsalException exception) {
                Log.d(TAG, "Authentication failed: " + exception.toString());
                displayError(exception);
            }
        };
    }
    private void displayGraphResult(@NonNull final JsonObject graphResponse ) {
        loadingDialog.dismiss();
//        成功 返回的信息
//        {"@odata.context":"https://graph.microsoft.com/v1.0/$metadata#drives/$entity","createdDateTime":"2017-10-17T03:37:14Z","description":"","id":"b!YfBrE2oet0WeMSpYqRNel6Uk0eDM4shGqXCkKvKE8dSKvozqfHWXQ5vLnhSmgCiB","lastModifiedDateTime":"2017-11-23T02:46:55Z","name":"OneDrive","webUrl":"https://bamboonetwork-my.sharepoint.com/personal/sunny_qiao_bamboonetworks_com/Documents","driveType":"business","createdBy":{"user":{"displayName":"系统帐户"}},"lastModifiedBy":{"user":{"email":"sunny.qiao@bamboonetworks.com","id":"ffbeda94-4305-479a-8411-e8872b73d51e","displayName":"Sunny Qiao"}},"owner":{"user":{"email":"sunny.qiao@bamboonetworks.com","id":"ffbeda94-4305-479a-8411-e8872b73d51e","displayName":"Sunny Qiao"}},"quota":{"deleted":0,"remaining":1099509779868,"state":"normal","total":1099511627776,"used":0}}
        LogUtil.e("---------",graphResponse.toString());
        BaseApplication.userBack = new Gson().fromJson(graphResponse.toString(),new TypeToken<UserBack>(){}.getType());
//        if (isHaveToken){
//            //已经获取过token
//            BaseApplication.userInfo = new Gson().fromJson(graphResponse.toString(),new TypeToken<UserInfo>(){}.getType());
//            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//            startActivity(intent);
//            finish();
//
//        }else {
//            isHaveToken = true;
            String[] newScopes={"api://15df8d38-ad1a-454f-ae12-edbce9f8f858/access_as_user"};
            mSingleAccountApp.acquireTokenSilentAsync(newScopes, AUTHORITY, callToken());
//        }
//        logTextView.setText(graphResponse.toString());



    }
//    在 UI 中更新文本以表示注销的方法
    private void performOperationOnSignOut() {
        final String signOutText = "Signed Out.";
        currentUserTextView.setText("");
        Toast.makeText(getApplicationContext(), signOutText, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loadingDialog!= null && loadingDialog.isShowing()){
            loadingDialog.dismiss();
        }
        if (loginFailDialog != null && loginFailDialog.isShowing()){
            loginFailDialog.dismiss();
        }
    }
}
