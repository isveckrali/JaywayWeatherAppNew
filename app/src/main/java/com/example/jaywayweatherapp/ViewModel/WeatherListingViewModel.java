package com.example.jaywayweatherapp.ViewModel;

import com.example.jaywayweatherapp.Models.WeatherListingModel;

public class WeatherListingViewModel {

    //Variables
    String name;
    String text;

    public WeatherListingViewModel(WeatherListingModel weatherListingModel) {
        this.name = weatherListingModel.getName();
        this.text = weatherListingModel.getCountry();
    }
}
