package com.example.hanldertest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by wuqi on 2017/6/19.
 */

public class MyRelatetive2 extends RelativeLayout {

    public MyRelatetive2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("--","MyRelatetive2");
        return true;
//        return super.onInterceptTouchEvent(ev);
    }
}
