package com.example.jaywayweatherapp.Controller.RestApi;

import com.example.jaywayweatherapp.Models.WeatherListingDetailModel;
import com.example.jaywayweatherapp.Models.WeatherListingModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApi {
    @GET("/data/2.5/forecast")
    Call<WeatherListingDetailModel> getWeatherList(@Query("id") String cityId, @Query("appid") String apiId);
}
