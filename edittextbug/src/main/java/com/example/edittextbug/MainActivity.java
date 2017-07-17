package com.example.edittextbug;

import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.edittextbug.titlebar.titlebarview.TitleBarUtil;
import com.example.edittextbug.titlebar.titlebarview.TransparentStatusBarUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        //不加半透明状态栏 能正常弹出  但加上toTransparent就异常啦 fuck
        TransparentStatusBarUtil.init(this);
        //TitleBarUtil.assistActivity(this);

    }
    public void back(View view) {
        finish();
    }
}
