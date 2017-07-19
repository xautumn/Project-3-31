package com.example.a7_17javainnerclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private InnerCallTest innerCallTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        innerCallTest = new InnerCallTest();
        innerCallTest = null;
        HashMap<Object, Object> mapss = new HashMap<>();
        mapss.put("name", "2");
        mapss.put("name", "1ssss");
        Iterator<Map.Entry<Object, Object>> iterator = mapss.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getValue());
        }
        System.out.println(mapss.size());
        System.out.println(mapss.get("name"));
    }



}
