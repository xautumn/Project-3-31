package com.example.svgtest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.carlos.notificatoinbutton.library.NotificationButton;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Calendar;

public class Main4Activity extends AppCompatActivity {

    private static TextView viewById;
    private static InputMethodManager inputMethodManager;
    private static View etTest1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fresco.initialize(this);
        setContentView(R.layout.activity_main4);
        getSupportActionBar().hide();
        viewById = (TextView) findViewById(R.id.et_test);
        etTest1 = findViewById(R.id.et_test1);
        test("eee");
        test("32323");
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


    }
    private static void test(String testString) {
        Log.i("eee","---=");
        final String mTest = testString;//存在栈中
        Log.i("eee","---="+mTest);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputMethodManager.showSoftInput(etTest1,InputMethodManager.SHOW_FORCED);
                Log.i("eee1","---="+mTest);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Calendar calendar = Calendar.getInstance();
        int hour;
        if (DateFormat.is24HourFormat(this)) {
            hour = calendar.get(Calendar.HOUR_OF_DAY);
        } else {
            hour = calendar.get(Calendar.HOUR);
        }
        int minute = calendar.get(Calendar.MINUTE);
        viewById.setText(hour+":"+minute);
        Log.i("Main4Activity","hour="+hour+"minute="+minute);
    }
}
