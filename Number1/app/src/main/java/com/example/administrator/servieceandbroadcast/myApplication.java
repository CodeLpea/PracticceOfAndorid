package com.example.administrator.servieceandbroadcast;

import android.app.Application;
import android.util.Log;

/**
 * Author by lp,on 2019/1/18/018,11:46.
 */
public class myApplication extends Application {
    private static final String TAG="myApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate:myApplication");
    }
}
