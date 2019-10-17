package com.example.jaywayweatherapp.Controller.LaunchScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.jaywayweatherapp.Controller.WeatherListingFragment.WeatherListingFragment;
import com.example.jaywayweatherapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_listing_container,new WeatherListingFragment(),WeatherListingFragment.WEATHER_LISTING_FRAGMENT_TAG)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
