package com.xtc.a10_26recycleview;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xtc.a10_26recycleview.R;

public class TestSvgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_svg);
        TextView tv = (TextView) findViewById(R.id.tv_test);

        Typeface mtypeface=Typeface.createFromAsset(getAssets(),"style1.ttf");
        tv.setTypeface(mtypeface);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}
