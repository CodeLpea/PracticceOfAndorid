package com.example.lp.daydayweather.gson;

/**
 * Created by jjy on 2017/2/23.
 */

public class AQI {

    public AQICity city;

    public class AQICity {
        public String aqi;
        public String pm25;
    }
}
