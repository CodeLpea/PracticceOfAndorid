package com.example.lp.daydayweather;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.lp.daydayweather.Util.HttpUtil;
import com.example.lp.daydayweather.Util.TimeUtils;
import com.example.lp.daydayweather.Util.Utility;
import com.example.lp.daydayweather.gson.Forecast;
import com.example.lp.daydayweather.gson.Weather;
import java.io.IOException;
import java.text.SimpleDateFormat;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.lp.daydayweather.Util.Config.imagApi;
import static com.example.lp.daydayweather.Util.Config.preferencesImgUrl;
import static com.example.lp.daydayweather.Util.Config.preferencesTime;
import static com.example.lp.daydayweather.Util.Config.preferencesWeather;
import static com.example.lp.daydayweather.Util.Config.weatherKey;
import static com.example.lp.daydayweather.Util.Config.weatherUrl;

public class WeatherActivity extends BaseActivity {
    private static final String TAG="WeatherActivity";
    private  SharedPreferences.Editor editor;
    private  SharedPreferences preferences;
    private ScrollView weatherLayout;
    private TextView titileCity,titleUpateTime,degreeText,weatherInfoText,aqiText,pm25Text,comfortText,carWashText,sportText;
    private LinearLayout forecastLayout;//未来几天预告

    private ImageView bingPicImg;//背景图片

    private String mWeatherId;//用于访问天气的城市id
    public SwipeRefreshLayout swipeRefresh;//刷新控件

    public DrawerLayout drawerLayout;//滑动菜单
    private Button navButton;//选择城市按钮


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        init();//初始化控件
        initWeather();//初始化天气ui
        initImg();//初始化背景图片
        refreshWeather();//手动刷新天气
        navChoose();//手动选择城市


    }
/**
 * 自动更新*/
    private void autoUpadate() {
        Log.i(TAG, "autoUpadate: ");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String date=sdf.format(new java.util.Date());
        Log.i(TAG, "date"+date);
        //先判断一下是否存在上次刷新的时间
        String upateTime=preferences.getString(preferencesTime,null);//读取是上次刷新时间
        if(upateTime!=null){//如果有，就比对一下是否满足刷新要求
            Log.i(TAG, "比对一下是否满足刷新要求: ");
            long time=TimeUtils.getInstance().getTimeExpend(upateTime,date);
            Log.i(TAG, "时间差: "+time);
            if(time>=1){
                //如果时间差大于两个小时则刷新
                swipeRefresh.setRefreshing(true);//显示刷新
                requestWeather(mWeatherId);
            }else{
                Log.i(TAG, "autoUpadate: 还没到自动刷新时间");
            }


        }else{//如果没有就保存下来，并刷新
            Log.i(TAG, "保存当前时间，并刷新: ");
            requestWeather(mWeatherId);
            loadBingPic();

        }

    }
    /***
     * 保存刷新的时间*/
private void savaUpdateTime(String date){
    Log.i(TAG, "savaUpdateTime:保存刷新的时间 ");
    editor.putString(preferencesTime,date);
    editor.apply();//将时间保存下来的地址缓存下来

}
    /**
 * 手动选择城市操作
 * */
    private void navChoose() {
        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);//打开滑动菜单，也就启动了chooseAreaFragment。
            }
        });
    }

    /***
 * 根据weather_id
 * 刷新天气
 * */
    private void refreshWeather() {
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestWeather(mWeatherId);//根据id请求天气信息
            }
        });
    }


    /**
 *初始化背景图
 * */
    private void initImg() {
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
        String weatherString =preferences.getString(preferencesWeather,null);
        if(weatherString!=null){
            //有缓存就直接解析
            Weather weatherBean= Utility.getInstance().handleWeaherResponse(weatherString);
            mWeatherId=weatherBean.basic.weatherId;
            autoUpadate();//自动刷新
            showWeatherInfo(weatherBean);
        }else {
            //无缓存就直接去服务器查询天气
            mWeatherId=getIntent().getStringExtra("weather_id");
            weatherLayout.setVisibility(View.INVISIBLE);
            requestWeather(mWeatherId);
        }
    }
/**
 * 根据weather_id
 * 发起天气查询请求*/
    public void requestWeather(String weatherId) {
        //http://guolin.tech/api/weather?cityid=CN101190401&key=8ce4087f233f4dcb9d9c25e2396ada1c
        String weaUrl=weatherUrl+weatherId+weatherKey;
        HttpUtil.getInstance().sendOkHttpRequest(weaUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                swipeRefresh.setRefreshing(false);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    final String responseText=response.body().string();
                    Log.i(TAG, "天气信息:   "+responseText);
                    final Weather weatherBean=Utility.getInstance().handleWeaherResponse(responseText);
                      SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    final String dateNow=sdf.format(new java.util.Date());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(weatherBean!=null&&weatherBean.status.equals("ok")){
                                editor.putString(preferencesWeather,responseText);
                                editor.apply();//放入缓存
                                mWeatherId=weatherBean.basic.weatherId;//将手动选择了城市之后的，重新刷新的weatherId记录下来
                                savaUpdateTime(dateNow);//保存刷新时间
                                showWeatherInfo(weatherBean);
                            }else {
                                Log.i(TAG, "获取天气信息失败");

                            }
                            swipeRefresh.setRefreshing(false);//刷新结束，并且隐藏刷新条
                        }
                    });
            }
        });

    }

    /**
 * 加载weatherBean实体类中的天气数据到ui
 * */
    private void showWeatherInfo(Weather weatherBean) {
        String upateTime=preferences.getString(preferencesTime,null).split(" ")[1];//读取是上次刷新时间,分割，只显示时分秒
        String cityName=weatherBean.basic.cityName;//城市
        //String updateTime=weatherBean.basic.update.updateTime.split(" ")[1];//时间
        //String updateTime=weatherBean.basic.update.updateTime;//时间
        String degree=weatherBean.now.temperature+"℃";//温度
        String weatherInfo=weatherBean.now.more.info;//晴、阴
        titileCity.setText(cityName);
        titleUpateTime.setText(upateTime);
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
        //下拉刷新
        swipeRefresh=findViewById(R.id.siwp_refresh);
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));

        drawerLayout=findViewById(R.id.drawer_layout);
        navButton=findViewById(R.id.btn_nav);

        editor=PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
        preferences= PreferenceManager.getDefaultSharedPreferences(this);

    }
}
