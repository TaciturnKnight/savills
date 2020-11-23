package com.bamboo.savills.base.view;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.WindowManager;

import com.bamboo.savills.Module.UserBack;
import com.bamboo.savills.Module.UserInfo;
import com.bamboo.savills.R;
import com.bamboo.savills.base.utils.LogUtil;
import com.bamboo.savills.base.utils.SpUtils;
import com.bamboo.savills.utils.MyCrashHandler;


/**
 * Created by qyj on 2019/10/9.
 */

public class BaseApplication extends Application {
    private static WindowManager windowManager;
    public static int windowWidth;
    public static int windowHeight;
    public static Context mContext;
    public static UserBack userBack;
    private static final String TAG ="SavillsApplication" ;
//    userToken
    public static String token;

    @Override
    public void onCreate() {
        LogUtil.e("--------------", "111111111111");
        super.onCreate();
        windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowWidth = windowManager.getDefaultDisplay().getWidth();
        windowHeight = windowManager.getDefaultDisplay().getHeight();
        mContext = this;
//        MyCrashHandler crashHandler = MyCrashHandler.instance();
//        crashHandler.init(getApplicationContext());

    }


}
