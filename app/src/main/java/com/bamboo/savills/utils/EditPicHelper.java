package com.bamboo.savills.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.hzw.doodle.DoodleParams;
import cn.hzw.doodle.DoodleView;

/**
 * Created by tong on 2020/10/20.
 */
public class EditPicHelper {
    private EditPicHelper() {
        colors.add("#ffffff");
        colors.add("#000000");
        colors.add("#FF0000");
        colors.add("#0C41FA");
        colors.add("#10E00A");
        colors.add("#FFEF00");
        colors.add("#BD00FF");
        colors.add("#A1A1A1");
        editParams = new DoodleParams();
    }

    public static EditPicHelper getInstance() {
        return EditPicHelperHolder.instance;
    }

    static class EditPicHelperHolder {
        private static EditPicHelper instance = new EditPicHelper();
    }

    public void setImage(File file) {
        bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        // 涂鸦参数
        editParams.mIsFullScreen = true;
        // 图片路径
        editParams.mImagePath = file.getAbsolutePath();
        // 初始画笔大小
        editParams.mPaintUnitSize = DoodleView.DEFAULT_SIZE;
        // 画笔颜色
        editParams.mPaintColor = Color.RED;
        // 是否支持缩放item
        editParams.mSupportScaleItem = false;
    }

    public void addText(String text) {
        strings.add(text);
    }

    public void reset() {
        if (strings != null)
            strings.clear();
        if (bitmap != null) {
            bitmap.recycle();
            bitmap = null;
        }
        editParams = null;
    }

    public DoodleParams editParams;
    public Bitmap bitmap;
    public List<String> strings = new ArrayList<>();
    public List<String> colors = new ArrayList<>();
}
