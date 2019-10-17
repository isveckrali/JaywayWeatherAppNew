package com.example.jaywayweatherapp.Controller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jaywayweatherapp.Controller.Helper.Helper;
import com.example.jaywayweatherapp.Controller.LaunchScreen.MainActivity;
import com.example.jaywayweatherapp.Models.ListItem;
import com.example.jaywayweatherapp.R;
import com.example.jaywayweatherapp.ViewModel.WeatherDetailViewModel;

import java.util.List;

public class ListDetailAdapter extends RecyclerView.Adapter<ListDetailAdapter.ViewHolder> {

    private Context context;
    private List<ListItem> listItem;

    public ListDetailAdapter(Context context, List<ListItem> listItem) {
        this.context = context;
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_detail_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            WeatherDetailViewModel weatherDetailViewModel = new WeatherDetailViewModel(this.listItem.get(position));
            holder.txtViewHumidity.setText(weatherDetailViewModel.humidity);
            holder.txtViewDesc.setText(weatherDetailViewModel.summary);
            holder.txtViewTemp.setText(weatherDetailViewModel.temp);
            holder.txtViewWind.setText(weatherDetailViewModel.wind);
            holder.txtViewDate.setText(weatherDetailViewModel.date);
            Helper.imageLoad(holder.imgViewIcon,weatherDetailViewModel.imageUrl,context);
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtViewHumidity, txtViewWind, txtViewDesc, txtViewTemp,txtViewDate;
        ImageView imgViewIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtViewDate = itemView.findViewById(R.id.txt_date);
            txtViewHumidity = itemView.findViewById(R.id.txt_humudity);
            txtViewWind = itemView.findViewById(R.id.txt_wind);
            txtViewDesc = itemView.findViewById(R.id.txt_desc);
            txtViewTemp = itemView.findViewById(R.id.txt_temp);
            imgViewIcon = itemView.findViewById(R.id.img_weather);


        }
    }
}
