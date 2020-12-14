package com.bamboo.savills.base.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.bamboo.savills.Module.SaveForm;

public class SpUtils {
    private Context context;

    private SharedPreferences sp;


    public SpUtils(Context context) {
        this.context = context;
    }
//    1 简体中文 2 英文 3 繁体

    public static  final String LANGUAGE = "LANGUAGE";

    public static final String TEXT_SIZE_MODEL = "TEXT_SIZE_MODEL";

//    是否有数据待上传 如果是待上传 应该在哪里提示呢？ 首页 还是FormA 详情页？
//    暂定为Form A 详情页吧
    public static final String SAVE_FORM_A = "SAVE_FORM_A";
//    不能只存一个对象
//    public void setSaveFormA(SaveForm saveForm){
//        sp = context.getSharedPreferences(SAVE_FORM_A,Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putString("",saveForm.);
//        editor.putFloat("time",time)
//    }

    public void setTSize(String type){
        sp =context.getSharedPreferences(TEXT_SIZE_MODEL,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("size",type);
        editor.apply();
    }
    public String getTSize(){
        sp = context.getSharedPreferences(TEXT_SIZE_MODEL,Context.MODE_PRIVATE);
        return sp.getString("size","1");
    }


    public void setLanguage(String type){
        sp =context.getSharedPreferences(LANGUAGE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("lang",type);
        editor.apply();
    }
    public String getLanguage(){
        sp = context.getSharedPreferences(LANGUAGE,Context.MODE_PRIVATE);
        return sp.getString("lang","1");
    }

}
