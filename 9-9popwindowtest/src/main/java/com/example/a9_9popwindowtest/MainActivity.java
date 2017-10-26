package com.example.a9_9popwindowtest;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

public class MainActivity extends Activity {

    private View ivRorationView;
    private ObjectAnimator tipRotationAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivRorationView = findViewById(R.id.iv_rotation);
    }

    public void test(View v) {
        //startActivity(new Intent(this,Main2Activity.class));
        startTipsAnimation(0,90);

    }

    public void test1(View v) {
        startTipsAnimation(90,180);
    }

    public void startTipsAnimation(int startValue,int stopValue) {
            tipRotationAnimation = ObjectAnimator.ofFloat(ivRorationView, "rotation", startValue, stopValue);
        if (ivRorationView != null) {
            //tipRotationAnimation.cancel();
            tipRotationAnimation.setDuration(2000);
            tipRotationAnimation.start();
        }
    }

    /*if (titleTextView != null && type == 3)
            titleTextView.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
            LogUtil.i("onClick");
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                View popView = activity.getLayoutInflater().inflate(R.layout.layout_titlebar_view_search_popwindow, null);
                //ListView listView = (ListView) popView.findViewById(R.id.lv_titleBar_pop);
                //listView.setAdapter(new );
                PopupWindow window = new PopupWindow(popView, AndroidUtil.dpToPx(context, 240), 600);
                //window.setAnimationStyle(R.style.popup_window_anim);
                // TODO: 2016/5/17 设置背景颜色
                window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff0000")));
                // TODO: 2016/5/17 设置可以获取焦点
                window.setFocusable(true);
                // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
                window.setOutsideTouchable(true);
                // TODO：更新popupwindow的状态
                window.update();

                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 0.5f; //0.0-1.0
                activity.getWindow().setAttributes(lp);
                // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
                window.showAsDropDown(rlRoot, ScreenUtil.getPxWidth(context) / 2 - AndroidUtil.dpToPx(context, 240) / 2, 0);
            }

        }
    });*/
}
