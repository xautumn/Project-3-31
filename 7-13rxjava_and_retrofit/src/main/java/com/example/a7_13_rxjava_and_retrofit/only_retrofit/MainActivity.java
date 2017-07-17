package com.example.a7_13_rxjava_and_retrofit.only_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.a7_13_rxjava_and_retrofit.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * 豆瓣电影测试接口
 * https://api.douban.com/v2/movie/top250?start=0&count=10
 */
public class MainActivity extends AppCompatActivity {

    private String baseUrl = "https://api.douban.com/v2/movie/";
    private String TAG = "retrofit_rxjava";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        retrofit.create(WatchVersionServer.class)
                .getDouBanMovie(0,1)
                .enqueue(new Callback<Object>() {

                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        Log.i(TAG,"response ="+response);//协议、响应码等
                        Log.i(TAG,"response1 ="+response.body());//服务器返回的内容
                        Log.i(TAG,"response1 ="+response.message());
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        Log.i(TAG,"onFailure ="+t);
                    }
                });
    }

    //rxjava和retrofit结合使用
    private void test() {

    }
}
