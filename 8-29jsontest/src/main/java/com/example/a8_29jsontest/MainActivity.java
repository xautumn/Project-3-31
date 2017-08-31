package com.example.a8_29jsontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        User u = new User();
        u.setAge(22);
        u.setUsername("hzucmj");
        u.setEnabled(true);
        createSigleJson();
        createListJson();

    }

    private void createListJson() {
        //生成
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();

        try {
            jsonObject1.put("text","红表1的手表可升级至");
            jsonObject1.put("language",0);

            jsonObject2.put("text","手表会在充电时下载安装包，");
            jsonObject2.put("language",0);

            jsonArray.put(jsonObject1);
            jsonArray.put(jsonObject2);

            jsonObject.put("versionId","eb3b64eb71fe4885bde7334902875801");
            jsonObject.put("versionTipBos",jsonArray);

            Log.i("wq test","createListJson = "+jsonObject);

            String unicode = new String(jsonObject.toString().getBytes(), Charset.forName("unicode"));

            Log.i("wq test","unicode = "+unicode);
//wq test: createListJson = {"versionId":"eb3b64eb71fe4885bde7334902875801","versionTipBos":[{"text":"红表1的手表可升级至","language":0},{"text":"手表会在充电时下载安装包，","language":0}]}

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void createSigleJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("title","1123");
            Log.i("wq test","jsonObject = "+jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
