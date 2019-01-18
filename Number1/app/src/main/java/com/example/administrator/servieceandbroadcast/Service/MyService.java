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

public class MyService extends Service {
    private static final String TAG="MyService";
    private timeThread mtimeThread;

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
        Log.i(TAG, "onStartCommandServic: ");
        Bundle bundle = intent.getExtras();
        if (bundle != null)
        {
            int control =0;
            control = (int)bundle.getSerializable("Key");
        if (control != 0) {
            switch (control) {
                case 1:
                    if(mtimeThread==null){
                        mtimeThread=new timeThread();
                        mtimeThread.start();
                    }
                    Log.i(TAG, " timeThread.start();");
                    break;
                case 2:
                        Log.i(TAG, " timeThread.interrupt(); ");
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
                   Thread.sleep(1000);
                   Log.i(TAG, "i: "+i++);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }
   }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroyService ");
    }
}
