package com.example.lp.daydayweather.Config;

public class Config {
    private static Config instance=null;
    public static synchronized Config getInstance(){
        if(instance==null){
            instance=new Config();
        }
        return instance;
    }

    public static String province="province";
    public static String city="city";
    public static String county="county";
    public static String provinceApi="http://guolin.tech/api/china";
    public static String weatherKey="8ce4087f233f4dcb9d9c25e2396ada1c";

    public static String provinceNmae="name";
    public static String provinceCode="id";
    public static String CityNmae="name";
    public static String CityCode="id";
    public static String CountyName="name";
    public static String CountyWeatherId="weather_id";
}
