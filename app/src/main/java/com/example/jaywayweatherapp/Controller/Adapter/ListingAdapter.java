package com.example.jaywayweatherapp.Controller.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jaywayweatherapp.Controller.Helper.Helper;
import com.example.jaywayweatherapp.Controller.LaunchScreen.MainActivity;
import com.example.jaywayweatherapp.Controller.WeatherDetailFragment.WeatherDetailFragment;
import com.example.jaywayweatherapp.Models.WeatherListingModel;
import com.example.jaywayweatherapp.R;

import java.util.ArrayList;
import java.util.List;

public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<WeatherListingModel> weatherListingModel,filteredListingModel;

    public ListingAdapter(Context context, List<WeatherListingModel> weatherListingModel) {
        this.context = context;
        this.weatherListingModel = weatherListingModel;
        this.filteredListingModel = weatherListingModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_listing_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherListingModel weatherListingModel = this.filteredListingModel.get(position);
        holder.txtCityName.setText(weatherListingModel.getName());
        holder.txtCitysTemp.setText(weatherListingModel.getCountry());
        Helper.imageLoad(holder.imgWeatherIcon,"https://github.com/mehmetcanseyhan/JaywayWeatherApp/blob/master/WeatherAppCovrChallenge/Assests/Assets.xcassets/default.imageset/default.png?raw=true",context);
    }

    @Override
    public int getItemCount() {
        return filteredListingModel.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charSequenceString = constraint.toString();
                if (charSequenceString.isEmpty()) {
                    filteredListingModel = weatherListingModel;
                } else {

                    List<WeatherListingModel> filteredList = new ArrayList<>();
                    for (WeatherListingModel name : weatherListingModel) {
                        if (name.getName().toLowerCase().contains(charSequenceString.toLowerCase())) {
                            filteredList.add(name);
                        }
                        filteredListingModel = filteredList;
                    }

                }
                FilterResults results = new FilterResults();
                results.values = filteredListingModel;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredListingModel = (List<WeatherListingModel>) results.values;
                notifyDataSetChanged();
            }
        };

    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtCityName,txtCitysTemp;
        ImageView imgWeatherIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCityName = itemView.findViewById(R.id.text_view_city_country);
            txtCitysTemp = itemView.findViewById(R.id.text_view_temp);
            imgWeatherIcon = itemView.findViewById(R.id.weather_image_view);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            Bundle bundle=new Bundle();
            bundle.putString("cityId", filteredListingModel.get(getLayoutPosition()).getId()+"");
            bundle.putString("name",filteredListingModel.get(getLayoutPosition()).getName());
            WeatherDetailFragment weatherDetailFragment = new WeatherDetailFragment();
            weatherDetailFragment.setArguments(bundle);
            ((MainActivity)context).getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_listing_container,weatherDetailFragment,WeatherDetailFragment.WEATHER_DETAIL_FRAGMENT_TAG)
                    .addToBackStack(null)
                    .commit();
            closeKeyboard();
        }

        //Close opened keyboard
        private void closeKeyboard() {
            if (((MainActivity)context).getCurrentFocus() != null) {
                InputMethodManager inputManager = (InputMethodManager) ((MainActivity)context).getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(((MainActivity)context).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
