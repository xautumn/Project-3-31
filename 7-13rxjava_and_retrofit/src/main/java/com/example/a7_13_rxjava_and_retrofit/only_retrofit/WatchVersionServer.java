package com.example.a7_13_rxjava_and_retrofit.only_retrofit;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wuqi on 2017/7/15.
 * retrofit接口类
 */

public interface WatchVersionServer {
    /**
     * get 获取豆瓣top电影
     *
     * @param start
     * @param count
     * @return
     */
    @GET("top250")
    Call<Object> getDouBanMovie(@Query("start") int start, @Query("count") int count);
}
