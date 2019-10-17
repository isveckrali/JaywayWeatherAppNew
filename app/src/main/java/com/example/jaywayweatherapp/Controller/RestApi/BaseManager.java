package com.example.jaywayweatherapp.Controller.RestApi;

public class BaseManager {

    protected RestApi getRestApiClient() {
        RestApiClient restApiClient = new RestApiClient(BaseUrl.WEATHER_URL);
        return restApiClient.getmRestApi();
    }
}
