package com.example.lp.daydayweather;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lp.daydayweather.JasonBean.WeatherBean;
import com.example.lp.daydayweather.Util.HttpUtil;
import com.example.lp.daydayweather.Util.Utility;
import com.example.lp.daydayweather.gson.Forecast;
import com.example.lp.daydayweather.gson.Weather;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.lp.daydayweather.Config.Config.imagApi;
import static com.example.lp.daydayweather.Config.Config.preferencesImgUrl;
import static com.example.lp.daydayweather.Config.Config.preferencesWeather;
import static com.example.lp.daydayweather.Config.Config.weatherKey;
import static com.example.lp.daydayweather.Config.Config.weatherUrl;

public class WeatherActivity extends AppCompatActivity {
    private static final String TAG="WeatherActivity";
    private ScrollView weatherLayout;
    private TextView titileCity,titleUpateTime,degreeText,weatherInfoText,aqiText,pm25Text,comfortText,carWashText,sportText;
    private LinearLayout forecastLayout;//未来几天预告

    private ImageView bingPicImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // makeBackGroundFuSion();//将背景图片与状态栏融合到一起
        setContentView(R.layout.activity_weather);
        init();//初始化控件
        initWeather();//初始化天气ui
        initImg();//初始化背景图片

    }
/**
 * 将背景图片与状态栏融合到一起
 * */
    private void makeBackGroundFuSion() {
        if(Build.VERSION.SDK_INT>=21){
            View decorView=getWindow().getDecorView();//难道当前活动的DecorView
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_FULLSCREEN
                    |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);//改变系统UI的显示：活动布局的现实在状态栏上面
            getWindow().setStatusBarColor(Color.TRANSPARENT);//将状态栏设置成透明
/**
 * 此处还会存在显示过于贴近状态栏的问题
 * 需要在相应的layout中，添加 android:fitsSystemWindows="true"
 * 表示为系统状态栏留出空间
 * */
        }
    }

    /**
 *初始化背景图
 * */
    private void initImg() {
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        String bingPicUrl=preferences.getString(preferencesImgUrl,null);
        if(bingPicUrl!=null){//如果有图片的地址的话
            Glide.with(this).load(bingPicUrl).into(bingPicImg);//将网络地址中的图片，加载到图片控件中
        }
        else{//如果没有图片的地址，则去更新
            loadBingPic();
        }
    }
/**
 * 请求图片地址
* */
    private void loadBingPic() {
        HttpUtil.getInstance().sendOkHttpRequest(imagApi, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.i(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPicUrl=response.body().string();
                SharedPreferences.Editor editor=PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString(preferencesImgUrl,bingPicUrl);
                editor.apply();//将背景图片的地址缓存下来
                runOnUiThread(new Runnable() {//在主线程中去更新界面
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(bingPicUrl).into(bingPicImg);//直接将图片加载
                    }
                });
            }
        });
    }

    /**
 * 初始化天气，加载缓存或者从接口更新*/
    private void initWeather() {
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString =preferences.getString(preferencesWeather,null);
        if(weatherString!=null){
            //有缓存就直接解析
            Weather weatherBean= Utility.getInstance().handleWeaherResponse(weatherString);
            showWeatherInfo(weatherBean);
        }else {
            //无缓存就直接去服务器查询天气
            String weatherId=getIntent().getStringExtra("weather_id");
            weatherLayout.setVisibility(View.INVISIBLE);
            requestWeather(weatherId);
        }
    }
/**
 * 根据weather_id
 * 发起天气查询请求*/
    private void requestWeather(String weatherId) {
        //http://guolin.tech/api/weather?cityid=CN101190401&key=8ce4087f233f4dcb9d9c25e2396ada1c
        String weaUrl=weatherUrl+weatherId+weatherKey;
        HttpUtil.getInstance().sendOkHttpRequest(weaUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    final String responseText=response.body().string();
                    final Weather weatherBean=Utility.getInstance().handleWeaherResponse(responseText);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(weatherBean!=null&&weatherBean.status.equals("ok")){
                                SharedPreferences.Editor editor=PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                                editor.putString(preferencesWeather,responseText);
                                editor.apply();//放入缓存
                                showWeatherInfo(weatherBean);
                            }
                        }
                    });
            }
        });

    }

    /**
 * 加载weatherBean实体类中的天气数据到ui
 * */
    private void showWeatherInfo(Weather weatherBean) {
        String cityName=weatherBean.basic.cityName;//城市
        String updateTime=weatherBean.basic.update.updateTime.split(" ")[1];//时间
        String degree=weatherBean.now.temperature+"℃";//温度
        String weatherInfo=weatherBean.now.more.info;//晴、阴
        titileCity.setText(cityName);
        titleUpateTime.setText(updateTime);
        degreeText.setText(degree);
        weatherInfoText.setText(weatherInfo);

        forecastLayout.removeAllViews();//移除
        for(Forecast forecastBean:weatherBean.forecastList){
            View view= LayoutInflater.from(this).inflate(R.layout.forecast_item,forecastLayout,false);//将forecast_item与forecastLayout绑定
            TextView dataText=view.findViewById(R.id.tv_data);
            TextView infoText=view.findViewById(R.id.tv_info);
            TextView maxText=view.findViewById(R.id.tv_max);
            TextView minText=view.findViewById(R.id.tv_min);
            dataText.setText(forecastBean.date);
            infoText.setText(forecastBean.more.info);
            maxText.setText(forecastBean.temperature.max);
            minText.setText(forecastBean.temperature.min);
            forecastLayout.addView(view);//添加到视图
        }
        if(weatherBean.aqi!=null){
            aqiText.setText(weatherBean.aqi.city.aqi);
            pm25Text.setText(weatherBean.aqi.city.pm25);
        }
        String comfort="舒适程度："+weatherBean.suggestion.comfort.info;
        String carWash="洗车指数："+weatherBean.suggestion.carWash.info;
        String sport="运动建议："+weatherBean.suggestion.sport.info;
        comfortText.setText(comfort);
        carWashText.setText(carWash);
        sportText.setText(sport);
        weatherLayout.setVisibility(View.VISIBLE);

    }

    /**
 * 初始化各种控件
 * */
    private void init() {
        bingPicImg=findViewById(R.id.bing_pic_img);
        weatherLayout=findViewById(R.id.sl_weather);
        titileCity=findViewById(R.id.tv_title_city);
        titleUpateTime=findViewById(R.id.tv_title_time);
        degreeText=findViewById(R.id.tv_degree );
        weatherInfoText=findViewById(R.id.tv_weather_info );
        aqiText=findViewById(R.id.tv_aqi );
        pm25Text=findViewById(R.id.tv_pm );
        comfortText=findViewById(R.id.tv_comfort );
        sportText=findViewById(R.id.tv_sport );
        carWashText=findViewById(R.id.tv_wash );

        forecastLayout=findViewById(R.id.ll_forecast);

        weatherLayout=findViewById(R.id.sl_weather);
    }
}
