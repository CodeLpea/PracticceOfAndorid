package com.example.administrator.servieceandbroadcast.Service;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {

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
}
