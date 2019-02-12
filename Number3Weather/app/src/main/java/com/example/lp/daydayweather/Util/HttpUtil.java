package com.example.lp.daydayweather.Util;



import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    private static HttpUtil instance=null;
    public static synchronized HttpUtil getInstance(){
        if(instance==null){
            instance=new HttpUtil();
        }
        return instance;
    }
    public void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
