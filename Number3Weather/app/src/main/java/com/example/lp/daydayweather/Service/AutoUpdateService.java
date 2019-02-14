package com.example.lp.daydayweather.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
/**
 * 自动刷新服务
 * */
public class AutoUpdateService extends Service {
    public AutoUpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
