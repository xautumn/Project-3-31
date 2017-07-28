package com.example.a7_26refreshonmeasure;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    private TestViewApi viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewById = (TestViewApi) findViewById(R.id.testapi);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("TestViewApi","run="+Thread.currentThread());

                //viewById.setVisibility(View.GONE);

                viewById.;
            }
        },5000);
    }
}
