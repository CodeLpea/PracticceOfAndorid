package com.example.administrator.servieceandbroadcast.Service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.example.administrator.servieceandbroadcast.uils.ConfigKey.INTENT_KEY2;

/**
 * Author by lp,on 2019/1/18/018,13:52.
 */
public class MyIntentService extends IntentService {
    private static final String TAG="MyIntentService";
    public MyIntentService() {
        super("MyIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "onHandleIntent: ");
        Bundle bundle=intent.getExtras();
        if(bundle!=null){
            for(int i=0;i<5;i++){
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                Log.e(TAG, "onHandleIntent:  bundle.putString"+bundle.get(INTENT_KEY2) );
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroyIntentService: ");
    }
}
