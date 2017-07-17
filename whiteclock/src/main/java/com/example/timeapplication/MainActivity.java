package com.example.timeapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int hour1 = calendar.get(Calendar.HOUR);
        Log.i("test","hour="+hour);
        Log.i("test","hour1="+hour1);
        int result = dealFormat(this, calendar);
        Log.i("test","result="+result);
        int i = 11/3;
        Log.i("test","i="+i);
    }
    public static int dealFormat(Context context, Calendar calendar) {

        if (DateFormat.is24HourFormat(context)) {
            return 1;
        } else {
            if (calendar.get(Calendar.HOUR_OF_DAY) > 12) {
                return 3;
            } else {
                return 2;
            }
        }
    }
}
