package com.example.a7_17javainnerclass;

import android.util.Log;

/**
 * Created by wuqi on 2017/7/17.
 */

public class InnerCallTest {

    private String test1 ="123";
    public InnerCallTest() {
        //super();//加了无效
        Log.i("wq", "test="+test1);
        Log.i("wq", "InnerCallTest");
        test1 = "3445";
        init();
    }

    private void init() {
        final Test test = new Test();
        test.setonCallback(new TestOnCallBack() {
            @Override
            public void onCallback() {
                Log.i("wq", "InnerCallTest onCallback");
                //当前类已经被销毁，但还是能访问外部类的成员变量？？
                Log.i("wq", "InnerCallTest test="+test1);
            }
        });

    }

}
