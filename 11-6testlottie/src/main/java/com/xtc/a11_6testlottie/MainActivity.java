package com.xtc.a11_6testlottie;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieImageAsset;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LottieAnimationView lav = findViewById(R.id.animation_view);
        lav.setImageAssetsFolder("test");
        lav.setAnimation("data.json");
        lav.playAnimation();
        //LottieComposition


        TextView viewById = findViewById(R.id.tv);
        viewById.setTextColor(Color.WHITE);
    }
}
