package com.example.hanldertest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by wuqi on 2017/6/19.
 */

public class MyRelative extends RelativeLayout {

    public MyRelative(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("--","MyRelative");
        return true;
//        return super.onInterceptTouchEvent(ev);
    }
}
