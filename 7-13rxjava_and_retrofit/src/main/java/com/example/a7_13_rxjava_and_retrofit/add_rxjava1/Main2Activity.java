package com.example.a7_13_rxjava_and_retrofit.add_rxjava1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.a7_13_rxjava_and_retrofit.R;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.schedulers.Schedulers;

public class Main2Activity extends AppCompatActivity {

    private String baseUrl = "https://api.douban.com/v2/movie/";
    private String TAG = "retrofit_rxjava";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("123","456");
        Log.i(TAG,"objectObjectHashMap="+objectObjectHashMap);
        Object remove = objectObjectHashMap.remove("123");
        Log.i(TAG,"remove="+remove);
        Log.i(TAG,"size="+objectObjectHashMap.size());
    }

    private void init() {
      /*  Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        retrofit.create(WatchVersionServer.class)
                .getDouBanMovie(0,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.)*/
    }
}
