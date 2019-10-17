package com.example.jaywayweatherapp.Controller.WeatherListingFragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.jaywayweatherapp.Controller.Adapter.ListingAdapter;
import com.example.jaywayweatherapp.Controller.Helper.Helper;
import com.example.jaywayweatherapp.Controller.InternetController.InternetController;
import com.example.jaywayweatherapp.Models.WeatherListingModel;
import com.example.jaywayweatherapp.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherListingFragment extends Fragment implements SearchView.OnQueryTextListener {

    //Constants
     public static String WEATHER_LISTING_FRAGMENT_TAG = "weatherListingFragmentTag";

    //Variables
    private List<WeatherListingModel> weatherListingModels;
    private RecyclerView recyclerView;
    private View view;
    private LinearLayoutManager layoutManager;
    private ListingAdapter listingAdapter;
    private ProgressBar progressBar;
    private ImageView imageView;
    private SearchView searchView;

    public WeatherListingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_weather_listing, container, false);
        defineComponents();
        getLocalData();
        setClientImage();
        return view;
    }


    //Set data to Recyler view
    private void setData() {
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter
        listingAdapter = new ListingAdapter(getActivity(),weatherListingModels);
        recyclerView.setAdapter(listingAdapter);
        listingAdapter.notifyDataSetChanged();

        progressBar.setVisibility(View.GONE);

    }

    private void setClientImage() {
    if (InternetController.isNetworkConnected(getActivity())) {
        Helper.imageLoad(imageView,"https://cdn.pixabay.com/photo/2015/03/03/05/56/woodland-656969__340.jpg",getActivity());
     }
    }
    //Read local json file from assets file
    private void getLocalData() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getActivity().getAssets().open("cities.json"), "UTF-8"));
            WeatherListingModel[] myModel = new Gson().fromJson(reader, WeatherListingModel[].class);
            weatherListingModels = Arrays.asList(myModel);
            setData();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Define compenents by id
    private void defineComponents() {
        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progressBar);
        imageView = view.findViewById(R.id.img_top);
        searchView = view.findViewById(R.id.search_bar_btn);

        searchView.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        listingAdapter.getFilter().filter(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        listingAdapter.getFilter().filter(s);
        return false;
    }
}
