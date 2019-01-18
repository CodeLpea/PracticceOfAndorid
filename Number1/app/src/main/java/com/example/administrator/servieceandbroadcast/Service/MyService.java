package com.example.administrator.servieceandbroadcast.Service;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.administrator.servieceandbroadcast.uils.ConfigKey.TIME_THRAED_1;
import static com.example.administrator.servieceandbroadcast.uils.ConfigKey.TIME_THRAED_2;

public class MyService extends Service {
    private static final String TAG="MyService";
    private timeThread mtimeThread;
    private int startId;

    public MyService() {
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreateService: ");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MyBinder();
    }


    public class MyBinder extends Binder {
        public void connService(){

        }
        public void NotifiStart(Activity activity){
            Toast.makeText(activity,"开启服务",Toast.LENGTH_SHORT).show();

        }
        public void NotifiStop(Activity activity){
            Toast.makeText(activity,"关闭服务",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommandstartId: "+startId);
        Log.i(TAG, "onStartCommandServic: ");
        Bundle bundle = intent.getExtras();
        if (bundle != null)
        {
            int control =0;
            control = (int)bundle.getSerializable("Key");
        if (control != 0) {
            switch (control) {
                case TIME_THRAED_1:
                    if(mtimeThread==null){
                        mtimeThread=new timeThread();
                        mtimeThread.start();
                    }
                    Log.i(TAG, " timeThread.start();");
                    break;
                case TIME_THRAED_2:
                    Log.i(TAG, "   stopSelf(startId): "+startId);
                    stopSelf(startId);//当同时采取bindService（）的时候，实际上stopSelf的命令是不起作用的，必须想接触绑定服务才能再手动停止服务。
                    break;
            }
        }
        }
        return super.onStartCommand(intent, flags, startId);
    }

   static class timeThread extends Thread{
        private int i=0;
       private timeThread() {
           Log.i(TAG, "timeThread: ");
       }
       @Override
       public void run() {
           while (true){
               try {
                   Thread.sleep(5000);
                   Log.i(TAG, "i: "+i++);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }
   }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroyService ");
    }
}
