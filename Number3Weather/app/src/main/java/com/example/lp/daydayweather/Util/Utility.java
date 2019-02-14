package com.example.lp.daydayweather.Util;


import android.text.TextUtils;

import com.example.lp.daydayweather.Dao.City;
import com.example.lp.daydayweather.Dao.County;
import com.example.lp.daydayweather.Dao.Province;
import com.example.lp.daydayweather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 解析和处理服务器返回的数据
 * */
public class Utility {
    private static Utility instance=null;
    public static synchronized Utility getInstance(){
        if(instance==null){
            instance=new Utility();
        }
        return instance;
    }
    /**
     * 解析和处理服务器返回的省数据
     * */
    public boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){//response不等于空
            try{
                JSONArray allProvinces=new JSONArray(response);
                for(int i=0;i<allProvinces.length();i++){
                    JSONObject provinceObject=allProvinces.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceName(provinceObject.getString(Config.provinceNmae));
                    province.setProvinceCode(provinceObject.getInt(Config.provinceCode));
                    province.save();//保存到数据库
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     * 解析和处理服务器返回的城市数据
     * */
    public boolean handleCityResponse(String response,int ProvinceId){
        if(!TextUtils.isEmpty(response)){//response不等于空
            try{
                JSONArray allCity=new JSONArray(response);
                for(int i=0;i<allCity.length();i++){
                    JSONObject provinceObject=allCity.getJSONObject(i);
                    City city=new City();
                    city.setCityName(provinceObject.getString(Config.CityNmae));
                    city.setCityCode(provinceObject.getInt(Config.CityCode));
                    city.setProvinceId(ProvinceId);
                    city.save();//保存到数据库
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     * 解析和处理服务器返回的区县数据
     * */
    public boolean handleCountyResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){//response不等于空
            try{
                JSONArray allCounty=new JSONArray(response);
              /*  String testGson=allCounty.getJSONObject(0).toString();
                Log.i(TAG, "handleCountyResponse:  "+testGson);
                Gson gson = new Gson();
                County user = gson.fromJson(testGson, County.class);
                Log.i(TAG, "getCityId: "+user.getCityId()
                        +"getCityId   "+user.getCountyName());*/
                for(int i=0;i<allCounty.length();i++){
                    JSONObject provinceObject=allCounty.getJSONObject(i);
                    County county=new County();
                    county.setCountyName(provinceObject.getString(Config.CountyName));
                    county.setWeatherId(provinceObject.getString(Config.CountyWeatherId));
                    county.setCityId(cityId);
                    county.save();//保存到数据库
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     * 解析天气的JSON数据
     * */
    public Weather handleWeaherResponse(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray=jsonObject.getJSONArray("HeWeather");
            String weatherContent=jsonArray.getJSONObject(0).toString();
            return  new Gson().fromJson(weatherContent,Weather.class);
        }catch (JSONException e){
            e.printStackTrace();
        }

        return null;
    }
}
