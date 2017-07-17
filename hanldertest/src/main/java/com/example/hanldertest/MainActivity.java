package com.example.hanldertest;

import android.content.res.ColorStateList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvHhh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity","onCreate");
        Log.i("MainActivity","onCreate");
        setContentView(R.layout.activity_main);
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("异常后"); //抛出异常，不会执行
        tvHhh = (TextView) findViewById(R.id.hhhh);
        //tvHhh.setTextColor(getResources().getColor(R.color.shape_color_text_black));
        ColorStateList colorStateList = getResources().getColorStateList(R.color.shape_color_text_black);
        Log.i("colorStateList","="+colorStateList);
        tvHhh.setTextColor(colorStateList);
        tvHhh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"click",Toast.LENGTH_LONG).show();
            }
        });
        RelativeLayout viewById = (RelativeLayout) findViewById(R.id.rl);
        RelativeLayout.LayoutParams viewStatusBarLayoutParams = (RelativeLayout.LayoutParams) viewById.getLayoutParams();
        //viewStatusBarLayoutParams.height =  + viewStatusBarLayoutParams.height;
        //rlTop.setLayoutParams(viewStatusBarLayoutParams);
//        viewById.setPadding(0, 200, 0, 0);
//        viewById.
    }

    private void init() throws Exception {
        if(true) {
            System.out.println("异常后1");
            throw new Exception("参数越界");
        }
        System.out.println("异常后2");
    }


}
