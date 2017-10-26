package com.example.a9_19testdynamicchangetextsize;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Thread thread;
    private Handler handler;
    private RelativeLayout rlRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rlRoot = (RelativeLayout) findViewById(R.id.activity_main);
        rlRoot.setBackgroundColor(Color.WHITE);
        handler = new Handler();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("wq",""+Thread.currentThread());
                try {
                    Thread.currentThread().sleep(5000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            TextView textView = new TextView(MainActivity.this);
                            textView.setText("hhhh");
                            rlRoot.addView(textView);
                            rlRoot.setBackgroundResource(R.drawable.download_base_map);
                            rlRoot.setBackgroundColor(Color.WHITE);
                            //getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            Log.i("wq","show = "+Thread.currentThread());

                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }
}
