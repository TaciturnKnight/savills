package com.bamboo.savills.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.bamboo.savills.utils.DensityUtils;

import androidx.annotation.Nullable;

/**
 * Created by tong on 2020/10/20.
 */
public class PointView extends View {
    private Context mContext;
    private String color;
    private Paint paint;

    public PointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设置宽度
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        int mWidth = DensityUtils.dp2px(mContext, 25);
        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            mWidth = specSize;
        }
        setMeasuredDimension(mWidth, mWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (color != null) {
            paint.setColor(Color.parseColor(color));
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - DensityUtils.dp2px(mContext, 5), paint);
        }
    }

    public void setColor(String color) {
        this.color = color;
        invalidate();
    }
}
