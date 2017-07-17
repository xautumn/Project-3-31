package com.example.a7_14testtopbarmenu;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * popMenu样式过少，采用popWindow来实现
 */
public class PopWindowActivity extends AppCompatActivity {

    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_window);
        init();
    }

    private void init() {
        View popupView = getLayoutInflater().inflate(R.layout.layout_pop_window_test, null);
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);//PopupWindow是否响应touch事件,true：里面的item是否可以点击 false：不响应
        popupWindow.setOutsideTouchable(false);//PopupWindow以外的区域是否可点击
        //popupWindow.setFocusable(boolean focusable);//PopupWindow是否具有获取焦点的能力,默认为false
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));//点击空白区域，popwindow会消失
        //popupWindow.setWidth(1000);
        popupWindow.setAnimationStyle(R.style.anim_menu_bottombar);

        popupWindow.getContentView().setFocusableInTouchMode(true);
        popupWindow.getContentView().setFocusable(true);
        popupWindow.getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i("--", "onKey =" + event.getAction());
                return false;
            }
        });
    }

    public void test(View v) {
        popupWindow.showAsDropDown(v, 0, -700);//向下弹出动画形式显示出来
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //按返回键
        Log.i("--", "onKeyDown =" + event.getAction());
        return super.onKeyDown(keyCode, event);
    }
}