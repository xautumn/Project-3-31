package com.example.svgtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        double v = 2 * 1.00 / 1000;
        Log.i("test","v="+v);
        int s = 5001;

        Log.i("test","v="+ (s % 5000 == 0));
    }
}
