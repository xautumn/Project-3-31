package com.example.project_3_31;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {
    private AnimationDrawable animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main2);
        View viewById = findViewById(R.id.rl_root);
        viewById.setBackgroundResource(R.drawable.anim_black_clock);
        AnimationDrawable animation = (AnimationDrawable)viewById.getBackground();
        animation.start();
    }
}
