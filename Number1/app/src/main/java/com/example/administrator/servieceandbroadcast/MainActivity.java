package com.example.administrator.servieceandbroadcast;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.servieceandbroadcast.Service.MyService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG="MainActivity";


    private Button btn_open_service;
    private Button btn_close_service;

    protected MyService.MyBinder mMyBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: ");
        init();
        initService();
    }

    private void initService() {
      Intent intent=new Intent(this, MyService.class);
      bindService(intent,mCon,BIND_AUTO_CREATE);
    }
    ServiceConnection mCon=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(TAG, "onServiceConnected: ");
            mMyBinder=(MyService.MyBinder)iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TAG, "onServiceDisconnected: ");
        }
    };

    private void init() {
        btn_open_service=findViewById(R.id.btn_open_service);
        btn_close_service=findViewById(R.id.btn_close_service);

        btn_close_service.setOnClickListener(this);
        btn_open_service.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Log.i(TAG, "onClick: ");
        switch (view.getId()){
            case R.id.btn_open_service:
                if(mMyBinder!=null)
                {
                    mMyBinder.NotifiStart(this);
                }
                Log.i(TAG, "onClick: 开启服务");
                break;
            case R.id.btn_close_service:
                if(mMyBinder!=null)
                {
                    mMyBinder.NotifiStop(this);
                }
                Log.i(TAG, "onClick: 关闭服务");
                break;
            default:
                Log.i(TAG, "onClick: default");
                break;

        }

    }
}
