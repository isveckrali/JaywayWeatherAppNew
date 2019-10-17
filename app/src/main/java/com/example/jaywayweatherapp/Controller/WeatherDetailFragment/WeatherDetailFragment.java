package com.example.jaywayweatherapp.Controller.WeatherDetailFragment;


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
import android.widget.TextView;
import android.widget.Toast;

import com.example.jaywayweatherapp.Controller.Adapter.ListDetailAdapter;
import com.example.jaywayweatherapp.Controller.Adapter.ListingAdapter;
import com.example.jaywayweatherapp.Controller.Helper.Helper;
import com.example.jaywayweatherapp.Controller.InternetController.InternetController;
import com.example.jaywayweatherapp.Controller.RestApi.ManagerAll;
import com.example.jaywayweatherapp.Models.WeatherListingDetailModel;
import com.example.jaywayweatherapp.R;
import com.example.jaywayweatherapp.ViewModel.WeatherDetailViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherDetailFragment extends Fragment {

    //Constanst
    public static final String WEATHER_DETAIL_FRAGMENT_TAG = "weaherDetailFragmenTag";

    //Variables
    private View view;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayoutManager layoutManager;
    private ListDetailAdapter listDetailAdapter;
    private WeatherListingDetailModel weatherDetailModel;
    private String cityId, cityName;
    private TextView txtCityName;
    private ImageView imageView;


    public WeatherDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_weather_detail, container, false);
        getFirstValues();
        defineComponents();
        checkAccessAndGetData();
        return view;
    }

    //Get fragment arguments from another fragment
    private void getFirstValues() {
        cityId = getArguments().getString("cityId","");
        cityName = getArguments().getString("name","");
    }

    //Define xml compnonets by id
    private void defineComponents() {
        recyclerView = view.findViewById(R.id.detail_recycler_view);
        progressBar = view.findViewById(R.id.detail_progres_bar);
        txtCityName = view.findViewById(R.id.txt_city_name);
        imageView = view.findViewById(R.id.img_icon);
    }

    //Call Data method afer checking internet accessing
    private void checkAccessAndGetData() {
        txtCityName.setText(cityName);
        if (InternetController.isNetworkConnected(getActivity())) {
            getData();
            Helper.imageLoad(imageView,"https://github.com/mehmetcanseyhan/JaywayWeatherApp/blob/master/WeatherAppCovrChallenge/Assests/Assets.xcassets/DarkSkyLogo.imageset/DarkSkyLogo.png?raw=true",getActivity());
        } else  {
            Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void getData() {
        Call<WeatherListingDetailModel> weatherList = ManagerAll.getInstance().getWeatherListingData(cityId);
        weatherList.enqueue(new Callback<WeatherListingDetailModel>() {
            @Override
            public void onResponse(Call<WeatherListingDetailModel> call, Response<WeatherListingDetailModel> response) {
                if (response.isSuccessful()) {
                    weatherDetailModel = response.body();
                    setData();
                } else {
                    Toast.makeText(getActivity(), "Request isn't successfull", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<WeatherListingDetailModel> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Set data to Recyler view
    private void setData() {
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter
        listDetailAdapter = new ListDetailAdapter(getActivity(), weatherDetailModel.getList());
        recyclerView.setAdapter(listDetailAdapter);
        listDetailAdapter.notifyDataSetChanged();

        progressBar.setVisibility(View.GONE);

    }



}
