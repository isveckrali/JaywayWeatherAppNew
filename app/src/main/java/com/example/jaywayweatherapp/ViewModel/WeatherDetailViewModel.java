package com.example.jaywayweatherapp.ViewModel;

import com.example.jaywayweatherapp.Controller.Services.Services;
import com.example.jaywayweatherapp.Models.ListItem;

public class WeatherDetailViewModel {

    //Variables
    public String summary;
    public String temp;
    public String humidity;
    public String wind;
    public String date;
    public String imageUrl;

    public WeatherDetailViewModel(ListItem listItem) {
        this.summary = listItem.getWeather().get(0).getDescription();
        this.date = listItem.getDtTxt();
        this.humidity = listItem.getMain().getHumidity() + " %";
        this.wind = listItem.getWind().getSpeed()+ " %";
        this.temp = listItem.getMain().getTemp() +  "°";
        String imageIcon = listItem.getWeather().get(0).getIcon();
        this.imageUrl = Services.WEATHER_IAMGE_URL + imageIcon + "@2x.png";
    }
}
