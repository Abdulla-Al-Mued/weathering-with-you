package com.example.weatheringwithyou.Db_con;


import com.example.weatheringwithyou.ModelClass.weatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("data/2.5/weather")
    Call<weatherData> getData(

            @Query("q") String q,
            @Query("appid") String APIKEY,
            @Query("units") String units

    );

}
