package com.example.a8_3notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
        int notificationId = Integer.MAX_VALUE;
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.icon5);
        builder.setContentTitle("hhd")
                .setContentText("ddd")
                .setLargeIcon(largeIcon) // 一定要将大图标设置为UI提供的图标
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.icon5)
                .setTicker("有新消息")
                .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND)
                .setWhen(System.currentTimeMillis());

        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, builder.build());
    }

}
