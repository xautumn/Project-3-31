package com.example.businessclockapplication;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ImageView ivMouthTen;
    private ImageView ivMouthUnit;
    private ImageView ivHour;
    private ImageView ivMinute;
    private ImageView ivSecond;
    private int hourValue;
    private int minuteValue;
    private int secondValue;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        double i = 7*1.0/3;
        Log.i("test","i="+i);
        double ceil = Math.ceil(i);
        Log.i("test","ceil="+ceil);
        initView();
        initTimer();


    }



    private void initView() {
        ivMouthTen = (ImageView) findViewById(R.id.iv_business_clock_mouth_ten);
        ivMouthUnit = (ImageView) findViewById(R.id.iv_business_clock_mouth_unit);
        ivHour = (ImageView) findViewById(R.id.iv_business_clock_hour_right);
        ivMinute = (ImageView) findViewById(R.id.iv_business_clock_minute_right);
        ivSecond = (ImageView) findViewById(R.id.iv_business_clock_second_right);
    }

    private void initTimer() {
        Log.i("test","thread1 name="+Thread.currentThread().getName());
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.i("test","thread name="+Thread.currentThread().getName());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateDate();
                    }
                });
            }
        }, 0, 1000);//重复执行的间隔
    }

    private void updateDate() {
        Calendar calender = Calendar.getInstance();
        int day = calender.get(Calendar.DAY_OF_MONTH);
        ivMouthTen.setImageResource(getDateResNumber(day / 10));
        ivMouthUnit.setImageResource(getDateResNumber(day % 10));

        int newHour = calender.get(Calendar.HOUR);
        int newMinute = calender.get(Calendar.MINUTE);
        int newSecond = calender.get(Calendar.SECOND);
        updateAnimation(newHour, newMinute, newSecond);
    }

    private void updateAnimation(int hour, int minute, int second) {
        if (second != secondValue) {
            setAnimation(ivSecond, secondValue, second,60);
            secondValue = second;
        }

        if (minute != minuteValue) {
            setAnimation(ivMinute, minuteValue, minute,60);
            minuteValue = minute;
        }

        if (hour != hourValue) {
            setAnimation(ivHour, hourValue, hour*60+minuteValue,720);
            hourValue = hour;
        }




    }

    private void setAnimation(View view, int startValue, int stopValue,int percent) {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(view, "rotation", 360 * startValue / percent, 360 * stopValue / percent);
        rotation.setDuration(0);
        rotation.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    private int getDateResNumber(int number) {
        int resId = R.drawable.ic_business_clock_numble_0;
        switch (number) {
            case 0:
                resId = R.drawable.ic_business_clock_numble_0;
                break;
            case 1:
                resId = R.drawable.ic_business_clock_numble_1;
                break;
            case 2:
                resId = R.drawable.ic_business_clock_numble_2;
                break;
            case 3:
                resId = R.drawable.ic_business_clock_numble_3;
                break;
            case 4:
                resId = R.drawable.ic_business_clock_numble_4;
                break;
            case 5:
                resId = R.drawable.ic_business_clock_numble_5;
                break;
            case 6:
                resId = R.drawable.ic_business_clock_numble_6;
                break;
            case 7:
                resId = R.drawable.ic_business_clock_numble_7;
                break;
            case 8:
                resId = R.drawable.ic_business_clock_numble_8;
                break;
            case 9:
                resId = R.drawable.ic_business_clock_numble_9;
                break;
        }
        return resId;
    }
}
