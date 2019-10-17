package com.example.jaywayweatherapp.Controller.Helper;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jaywayweatherapp.R;

public class Helper {

    //Load image to view from Server by Url
    public static void imageLoad(ImageView imageView, String url, Context context) {
                 Glide
                .with(context)
                .load(url)
                .centerCrop()
                .into(imageView);

    }
}
