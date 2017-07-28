package com.example.a7_26refreshonmeasure;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

/**
 * Created by wuqi on 2017/7/26.
 */

public class TestViewApi extends RelativeLayout {

    private String TAG = TestViewApi.class.getSimpleName();
    private Context context;

    public TestViewApi(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setWillNotDraw(false);
        init();
    }

    private void init() {
        LayoutInflater.from(context).inflate(R.layout.titlebar_view,this);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG,"onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i(TAG,"onLayout");
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG,"onDraw");
        super.onDraw(canvas);
    }
}
