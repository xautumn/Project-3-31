package com.example.svgtest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    private String TAG = "Main3Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        TextView tv = (TextView) findViewById(R.id.tv);
        BadgeView badgeView = new BadgeView(this);
        badgeView.setTargetView(tv);
        badgeView.setText("ceshi");
        badgeView.setBackground(8,Color.BLUE);

        Log.i(TAG,"1="+Thread.currentThread());
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG,"2="+Thread.currentThread());
            }
        }).start();

        new Runnable() {
            @Override
            public void run() {
                Log.i(TAG,"3="+Thread.currentThread());
            }
        };
    }
}
