package com.example.readponittext7_10;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by wuqi on 2017/7/10.
 */

public class TestTextView extends TextView {
    private String TAG = "TestTextView";
    private Context context;

    public TestTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setWillNotDraw(false);
        initView();
    }

    private void initView() {

    }
   int count;
    public void setText(String s) {
        count =0;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (count == 1) {
            Log.i(TAG,"onMeasure falg=true");
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.EXACTLY);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(1000, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG,"onMeasure width="+getMeasuredWidth()+"--height="+getMeasuredHeight());
        count++;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i(TAG,"onLayout");
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //当前view的宽高
        Log.i(TAG,"onSizeChanged w =" + w + "--" + h + "--" + oldw + "--" + oldh);
        super.onSizeChanged(w, h, oldw, oldh);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG,"onDraw width="+getWidth()+"--height="+getHeight());
    }
}
