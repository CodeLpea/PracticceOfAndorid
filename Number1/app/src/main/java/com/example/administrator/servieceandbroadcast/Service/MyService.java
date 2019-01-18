package com.example.administrator.servieceandbroadcast.Service;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import static com.example.administrator.servieceandbroadcast.uils.ConfigKey.INTENT_KEY1;
import static com.example.administrator.servieceandbroadcast.uils.ConfigKey.TIME_THRAED_1;
import static com.example.administrator.servieceandbroadcast.uils.ConfigKey.TIME_THRAED_2;

public class MyService extends Service{
    private static final String TAG="MyService";
    private timeThread mtimeThread;
    private int startId;

    protected static ServiceListener serviceListener;

    public MyService() {
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreateService: ");
    }

    /**
     * onbind 方法会返回一个IBINDER对象，
     * 用户可以在Acitivity中使用ServiceConnection来接受这个Ibinder，
     * 从而来控制正在运行的服务中的某些操作
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MyBinder();
    }


    /**
     * 通过制定一个
     */
    public class MyBinder extends Binder {
        public void SetServiceListener(ServiceListener listener){
            serviceListener =listener;
        }
        public void NotifiStart(Activity activity){
            Toast.makeText(activity,"开启服务",Toast.LENGTH_SHORT).show();
            serviceListener.onOpen();//将绑定成功的消息回传给Acticity
        }
        public void NotifiStop(Activity activity){
            Toast.makeText(activity,"关闭服务",Toast.LENGTH_SHORT).show();
            serviceListener.onClose();
        }

    }

    /**
     * 每次启动服务，都会执行该方法
     * “onCreate()”方法只会在初始时调用一次，
     * “onStartCommand(Intent intent, int flags, int startId)”方法会在starService（）都被调用，
     *当中，每次回调onStartCommand()方法时，参数“startId”的值都是递增的，startId用于唯一标识每次对Service发起的处理请求
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommandstartId: "+startId);
        Log.i(TAG, "onStartCommandServic: ");
        Bundle bundle = intent.getExtras();
        if (bundle != null)
        {
            int control =0;
            control = (int)bundle.getSerializable(INTENT_KEY1);
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
                   Thread.sleep(2000);
                   Log.i(TAG, "i: "+i++);
                   serviceListener.onAction(i);//让Activity执行特定的操作
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
