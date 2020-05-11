package com.deepblue.library.seekbar;


import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

/**
 * Created by zhuangguangquan on 2017/12/13.
 * <p>
 * https://github.com/warkiz/IndicatorSeekBar
 */

public class CircleBubbleView extends View {
    private int mIndicatorTextColor;
    private int mIndicatorColor;
    private float mIndicatorTextSize;
    private Context mContext;
    private Path mPath;
    private Paint mPaint;
    private float mIndicatorWidth;
    private float mIndicatorHeight;
    private float mTextHeight;
    private String mProgress;

    CircleBubbleView(Context context) {
        this(context, null);
    }

    CircleBubbleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    CircleBubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init("100");
    }

    CircleBubbleView(Context context, float indicatorTextSize, int indicatorTextColor, int indicatorColor, String maxLengthText) {
        super(context, null, 0);
        this.mContext = context;
        this.mIndicatorTextSize = indicatorTextSize;
        this.mIndicatorTextColor = indicatorTextColor;
        this.mIndicatorColor = indicatorColor;
        init(maxLengthText);
    }

    private void init(String maxLengthText) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(mIndicatorTextSize);
        Rect mRect = new Rect();
        mPaint.getTextBounds(maxLengthText, 0, maxLengthText.length(), mRect);
        mIndicatorWidth = mRect.width() + SizeUtils.dp2px(mContext, 4);
        int minWidth = SizeUtils.dp2px(mContext, 36);
        if (mIndicatorWidth < minWidth) {
            mIndicatorWidth = minWidth;
        }
        mTextHeight = mRect.height();
        mIndicatorHeight = mIndicatorWidth * 1.2f;
        initPath();
    }

    private void initPath() {
        mPath = new Path();
        RectF rectF = new RectF(0, 0, mIndicatorWidth, mIndicatorWidth);
        mPath.arcTo(rectF, 135, 270);
        mPath.lineTo(mIndicatorWidth / 2, mIndicatorHeight);
        mPath.close();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension((int) mIndicatorWidth, (int) mIndicatorHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(mIndicatorColor);
        canvas.drawPath(mPath, mPaint);
        mPaint.setColor(mIndicatorTextColor);
        canvas.drawText(mProgress, mIndicatorWidth / 2f, mIndicatorHeight / 2 + mTextHeight / 4, mPaint);
    }

    void setProgress(String progress) {
        this.mProgress = progress;
        invalidate();
    }

}
