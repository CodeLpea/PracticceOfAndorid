package com.example.administrator.servieceandbroadcast.Service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import static com.example.administrator.servieceandbroadcast.uils.ConfigKey.INTENTSERVICENOTIFI1;
import static com.example.administrator.servieceandbroadcast.uils.ConfigKey.INTENT_KEY2;
import static com.example.administrator.servieceandbroadcast.uils.ConfigKey.INTENT_KEY4;

/**
 * Author by lp,on 2019/1/18/018,13:52.
 */
public class MyIntentService extends IntentService {
    private static final String TAG="MyIntentService";
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate: ");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.i(TAG, "onStart: ");
        super.onStart(intent, startId);
    }

    /**
由于大多数启动服务都不必同时处理多个请求，因此使用 IntentService 类实现服务也许是最好的选择
IntentService 执行以下操作：
•创建默认的工作线程，用于在应用的主线程外执行传递给 onStartCommand() 的所有 Intent
•创建工作队列，用于将 Intent 逐一传递给 onHandleIntent() 实现，这样就不必担心多线程问题
•在处理完所有启动请求后停止服务，因此不必自己调用 stopSelf()方法
•提供 onBind() 的默认实现（返回 null）
•提供 onStartCommand() 的默认实现，可将 Intent 依次发送到工作队列和 onHandleIntent()
因此，只需实现构造函数与 onHandleIntent() 方法即可
* */
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "onHandleIntent: ");
        Bundle bundle=intent.getExtras();
        Bundle bundle2;
        if(bundle!=null){
            Intent intent1;
            for(int i=0;i<5;i++){
                try{
                    Thread.sleep(1000);
                  intent1=new Intent(INTENTSERVICENOTIFI1);
                  bundle2=new Bundle();
                  bundle2.putSerializable(INTENT_KEY4,i);
                  intent1.putExtras(bundle2);
                  LocalBroadcastManager.getInstance(MyIntentService.this).sendBroadcast(intent1);
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
