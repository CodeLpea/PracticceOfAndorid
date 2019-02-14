package com.example.lp.daydayweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jjy on 2017/2/23.
 */

public class Weather {
    public String status;

    public Basic basic;

    public AQI aqi;

    public Now now;

    public Suggestion suggestion;

    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;
}
