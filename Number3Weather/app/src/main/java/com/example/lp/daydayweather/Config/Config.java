package com.example.lp.daydayweather.Config;

public class Config {
/**
 * 用作区分标志
* */
    public static String province="province";
    public static String city="city";
    public static String county="county";
    /**
     * 地区信息的地址*/
    public static String provinceApi="http://guolin.tech/api/china";
    /**
     * 每日图片请求的地址*/
    public static String imagApi="http://guolin.tech/api/bing_pic";
    /**
     * 天气信息的接口Key*/
    //http://guolin.tech/api/weather?cityid=CN101190401&key=8ce4087f233f4dcb9d9c25e2396ada1c
    public static String weatherKey="&key=8ce4087f233f4dcb9d9c25e2396ada1c";
    public static String weatherUrl="http://guolin.tech/api/weather?cityid=";


/**
 * Json数据的键名称*/
    public static String provinceNmae="name";
    public static String provinceCode="id";
    public static String CityNmae="name";
    public static String CityCode="id";
    public static String CountyName="name";
    public static String CountyWeatherId="weather_id";
    public static String preferencesWeather="weather";
    public static String preferencesImgUrl="imgUrl";
}
