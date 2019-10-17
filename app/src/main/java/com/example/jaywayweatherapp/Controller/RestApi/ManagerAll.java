package com.example.jaywayweatherapp.Controller.RestApi;


import com.example.jaywayweatherapp.Controller.Services.Services;
import com.example.jaywayweatherapp.Models.WeatherListingDetailModel;
import com.example.jaywayweatherapp.Models.WeatherListingModel;

import java.util.List;

import retrofit2.Call;

public class ManagerAll extends BaseManager{

    private static ManagerAll ourInstance = new ManagerAll();

    public static synchronized ManagerAll getInstance() {

        return ourInstance;

    }

    public Call<WeatherListingDetailModel> getWeatherListingData(String cityId) {

        Call<WeatherListingDetailModel> call = getRestApiClient().getWeatherList(cityId,Services.API_KEY);
        return call;
    }
}
