package com.example.administrator.servieceandbroadcast;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.servieceandbroadcast.Service.ServiceListener;
import com.example.administrator.servieceandbroadcast.Service.MyService;

import static com.example.administrator.servieceandbroadcast.uils.ConfigKey.INTENT_KEY1;
import static com.example.administrator.servieceandbroadcast.uils.ConfigKey.TIME_THRAED_1;
import static com.example.administrator.servieceandbroadcast.uils.ConfigKey.TIME_THRAED_2;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ServiceListener {
    private static final String TAG="MainActivity";
    private static final  int MSG1=1;
    private static ServiceListener thisActivity=null;
    private Button btn_open_service;
    private Button btn_close_service;
    private Button btn_Service_Commond_1;
    private Button btn_Service_Commond_2;
    private Button btn_to_intentService;

    private TextView textView;

    protected MyService.MyBinder mMyBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: ");
        thisActivity=this;
        initService();//自动绑定
        init();

    }
    private void initService() {
      Intent intent=new Intent(this, MyService.class);
      bindService(intent,mCon,BIND_AUTO_CREATE);
    }
    ServiceConnection mCon=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(TAG, "onServiceConnected: bind绑定成功");
            mMyBinder=(MyService.MyBinder)iBinder;
            mMyBinder.SetServiceListener(thisActivity);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TAG, "onServiceDisconnected:被迫终止的时候才会被调用 ");
            mMyBinder=null;
        }
    };

    private void init() {
        btn_open_service=findViewById(R.id.btn_open_service);
        btn_close_service=findViewById(R.id.btn_close_service);

        btn_Service_Commond_1=findViewById(R.id.btn_Service_Commond_1);
        btn_Service_Commond_2=findViewById(R.id.btn_Service_Commond_2);

        btn_to_intentService=findViewById(R.id.btn_to_intentService);

        textView=findViewById(R.id.tv_info);


        btn_close_service.setOnClickListener(this);
        btn_open_service.setOnClickListener(this);


        btn_Service_Commond_1.setOnClickListener(this);
        btn_Service_Commond_2.setOnClickListener(this);

        btn_to_intentService.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_open_service:

                if(mMyBinder!=null)
                {
                    mMyBinder.NotifiStart(this);
                    Log.i(TAG, "点击进行bind绑定服务");
                }else {
                    initService();
                }
                break;
            case R.id.btn_close_service:
                if(mMyBinder!=null)
                {
                    Log.i(TAG, "点击进行unbind: 关闭服务");
                    mMyBinder.NotifiStop(this);
                    unbindService(mCon);
                    mMyBinder=null;
                }else {
                    Log.i(TAG, "服务没有绑定，不能关闭服务绑定");
                }
                break;
            case R.id.btn_Service_Commond_1:
                Log.i(TAG, "点击进行startService服务通信");
                Intent intent = new Intent(this, MyService.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(INTENT_KEY1,TIME_THRAED_1);
                intent.putExtras(bundle);
                startService(intent);
                break;
            case R.id.btn_Service_Commond_2:
                Log.i(TAG, "点击进行stopService关闭服务");
                Intent intent2 = new Intent(this, MyService.class);
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable(INTENT_KEY1,TIME_THRAED_2);
                intent2.putExtras(bundle2);
                startService(intent2);
                break;
            case R.id.btn_to_intentService:
                Log.i(TAG, "进入intentServiceActivity: ");
                Intent intent3=new Intent(this,IntentServiceActivity.class);
                startActivity(intent3);
                break;
            default:
                Log.i(TAG, "onClick: default");
                break;

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mCon);
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    public void onOpen() {
        Log.i(TAG, "onOpen:绑定成功，交由ui操作 ");
    }

    @Override
    public void onClose() {
        Log.i(TAG, "onClose: 关闭绑定，交由ui操作");
    }

    @Override
    public void onAction(int i) {
        Log.i(TAG, "onAction: 服务产生特定操作，交由ui执行");
        Message message=mhandler.obtainMessage();
        message.what=MSG1;
        message.obj="MyService线程进行中......"+i;
        mhandler.sendMessage(message);
    }
    private Handler mhandler =new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG1:
                    textView.append(msg.obj+"\r\n");
                    break;
                default:
                    break;
            }

        }
    };


}
