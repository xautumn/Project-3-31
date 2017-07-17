package com.example.project_3_31;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private View ivMovingShop;
    private ObjectAnimator mRotateAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        ivMovingShop = findViewById(R.id.iv_cat_clock_ship);
        init();
        initAnimation();
    }

    private void init() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int week = calendar.get(Calendar.DAY_OF_WEEK);

    }

    private void initAnimation() {
        ObjectAnimator mRotateAnimation = ObjectAnimator.ofFloat(ivMovingShop, "rotation", -30, 30);
        mRotateAnimation.setRepeatMode(ValueAnimator.REVERSE);
        mRotateAnimation.setRepeatCount(-1);
        mRotateAnimation.setDuration(1000);
        ivMovingShop.setPivotX(160);
        ivMovingShop.setPivotY(102);
        mRotateAnimation.start();
    }
}
