package com.example.svgtest;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.graphics.Path;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wang.avi.AVLoadingIndicatorView;

public class MainActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Path path = new Path();
        VectorDrawable vectorDrawable = new VectorDrawable();
        AVLoadingIndicatorView av = (AVLoadingIndicatorView) findViewById(R.id.avi);
        av.setIndicator("BallSpinFadeLoaderIndicator");
        ValueAnimator.ofFloat(0,5,400);
    }
}
