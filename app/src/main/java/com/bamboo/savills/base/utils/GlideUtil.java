//package com.bamboo.savills.base.utils;
//
//import android.content.Context;
//import android.graphics.drawable.Drawable;
//import android.widget.ImageView;
//
//import com.bamboo.savills.R;
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.RequestBuilder;
//import com.bumptech.glide.load.resource.bitmap.CircleCrop;
//import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
//import com.bumptech.glide.request.RequestOptions;
//
///**
// * Created by qyj on 2019/10/11.
// */
//
//public class GlideUtil {
//    public static final int ROUND_TYPE_CIRCLE = 1;
//    public static final int ROUND_TYPE_CONERS = 2;
//    public static final int ROUND_TYPE_NONE = 0;
//    //替换成自己的占位图
//    private final int DEFAULT_PLACE_HOLDER = R.mipmap.ic_launcher;
//    private final int DEFAULT_ERROR = R.mipmap.ic_launcher;
//
//    private GlideUtil() {
//
//    }
//
//    private static class GlideUtilHolder {
//        private static final GlideUtil instance = new GlideUtil();
//    }
//
//    public static GlideUtil getInstance() {
//        return GlideUtilHolder.instance;
//    }
//
//    /**
//     * 加载一张图片
//     */
//    public void showImage(Context mContext, String url, ImageView imageView) {
//        show(mContext, url, imageView, DEFAULT_PLACE_HOLDER, DEFAULT_ERROR, ROUND_TYPE_NONE, 0);
//    }
//
//    /**
//     * 加载一张圆形图片
//     */
//    public void showCircleImage(Context mContext, String url, ImageView imageView) {
//        show(mContext, url, imageView, DEFAULT_PLACE_HOLDER, DEFAULT_ERROR, ROUND_TYPE_CIRCLE, 0);
//    }
//
//    /**
//     * 加载一张圆角图片
//     *
//     * @param roundSize 为px值 调用者自行转换为px值传入
//     */
//    public void showRoundImage(Context mContext, String url, ImageView imageView, int roundSize) {
//        show(mContext, url, imageView, DEFAULT_PLACE_HOLDER, DEFAULT_ERROR, ROUND_TYPE_CONERS, roundSize);
//    }
//
//    private void show(Context mContext, String url, ImageView imageView, int loadingImage, int errorImage, int roundType, int roundSize) {
//        RequestBuilder<Drawable> builder = Glide.with(mContext)
//                .load(url)
//                .placeholder(loadingImage)
//                .error(errorImage)
//                .fitCenter();
//        if (roundType == ROUND_TYPE_CIRCLE)
//            //圆形
//            builder.apply(RequestOptions.bitmapTransform(new CircleCrop()));
//        else if (roundType == ROUND_TYPE_CONERS)
//            //圆角
//            builder.apply(RequestOptions.bitmapTransform(new RoundedCorners(roundSize)));
//
//        builder.into(imageView);
//    }
//    public void showImages(Context mContext, String url, ImageView view) {
//        Glide.with(mContext).load(url).fitCenter().placeholder(R.drawable.bg_default).error(R.drawable.bg_default).dontAnimate().into(view);
//
//    }
//}
