package com.bamboo.savills.base.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtils {
    private Context context;

    private SharedPreferences sp;


    public SpUtils(Context context) {
        this.context = context;
    }
//    1 简体中文 2 英文 3 繁体

    public static  final String LANGUAGE = "LANGUAGE";

    public static final String TEXT_SIZE_MODEL = "TEXT_SIZE_MODEL";

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
