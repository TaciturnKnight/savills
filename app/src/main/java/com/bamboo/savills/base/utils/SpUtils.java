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


//  根据formid判断是否需要存储为Form A 详情页
    public static final String IS_SAVE_FORM_A = "IS_SAVE_FORM_A";
    public static final String SAVE_FORM_A_DETAIL = "SAVE_FORM_A_DETAIL";

    //  根据formid判断是否需要存储为Form B 详情页
    public static final String IS_SAVE_FORM_B = "IS_SAVE_FORM_B";
    public static final String SAVE_FORM_B_DETAIL = "SAVE_FORM_B_DETAIL";

    public void setIsSaveFormA(String formId,boolean isSave){
        sp = context.getSharedPreferences(IS_SAVE_FORM_A,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(formId,isSave);
        editor.apply();
    }
    public boolean getIsSaveFormA(String formId){
        sp = context.getSharedPreferences(IS_SAVE_FORM_A,Context.MODE_PRIVATE);
        return sp.getBoolean(formId,false);
    }

    public void setSaveFormADetail(String formId,String detail){
        sp = context.getSharedPreferences(SAVE_FORM_A_DETAIL,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(formId,detail);
        editor.apply();
    }
    public String getSaveFormADetail(String formId){
        sp = context.getSharedPreferences(SAVE_FORM_A_DETAIL,Context.MODE_PRIVATE);
        return  sp.getString(formId,"");
    }

    public void setIsSaveFormB(String formId,boolean isSave){
        sp = context.getSharedPreferences(IS_SAVE_FORM_B,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(formId,isSave);
        editor.apply();
    }
    public boolean getIsSaveFormB(String formId){
        sp = context.getSharedPreferences(IS_SAVE_FORM_B,Context.MODE_PRIVATE);
        return sp.getBoolean(formId,false);
    }

    public void setSaveFormBDetail(String formId,String detail){
        sp = context.getSharedPreferences(SAVE_FORM_B_DETAIL,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(formId,detail);
        editor.apply();
    }
    public String getSaveFormBDetail(String formId){
        sp = context.getSharedPreferences(SAVE_FORM_B_DETAIL,Context.MODE_PRIVATE);
        return  sp.getString(formId,"");
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
