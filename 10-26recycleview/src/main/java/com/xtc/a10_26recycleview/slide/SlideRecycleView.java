package com.xtc.a10_26recycleview.slide;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by wuqi on 2017/11/7.
 */

public class SlideRecycleView extends RecyclerView {

    public SlideRecycleView(Context context) {
        this(context,null);
    }

    public SlideRecycleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SlideRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
        initData();
    }

    private void initView() {
    }

    private void initData() {

    }

}
