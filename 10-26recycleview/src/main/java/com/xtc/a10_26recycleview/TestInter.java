package com.xtc.a10_26recycleview;

import android.util.Log;
import android.view.animation.LinearInterpolator;

/**
 * Created by wuqi on 2017/10/30.
 */

public class TestInter extends LinearInterpolator {
    @Override
    public float getInterpolation(float input) {
        Log.i("test","value = "+input);
        return 0.5f;
    }

}
