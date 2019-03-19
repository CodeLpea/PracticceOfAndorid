package com.example.administrator.servieceandbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.servieceandbroadcast.Service.MyIntentService;
import com.example.administrator.servieceandbroadcast.Service.MyService;
import com.example.administrator.servieceandbroadcast.Service.ServiceListener;

import static com.example.administrator.servieceandbroadcast.uils.ConfigKey.INTENTSERVICENOTIFI1;
import static com.example.administrator.servieceandbroadcast.uils.ConfigKey.INTENT_KEY2;
import static com.example.administrator.servieceandbroadcast.uils.ConfigKey.INTENT_KEY3;
import static com.example.administrator.servieceandbroadcast.uils.ConfigKey.INTENT_KEY4;

public class IntentServiceActivity extends AppCompatActivity implements View.OnClickListener {
    private  static  final String TAG="IntentServiceActivity";
 private myBroadcastReceiver mmmyBroadcastReceiver;
    private int i=0;
    private Button btn_start_intentService;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
        Log.i(TAG, "onCreate: ");
        init();
        registBroadcast();
    }

    private void registBroadcast() {
        mmmyBroadcastReceiver=new myBroadcastReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(INTENTSERVICENOTIFI1);
        LocalBroadcastManager.getInstance(this).registerReceiver(mmmyBroadcastReceiver,intentFilter);
    }
private class myBroadcastReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction() ;
        if ( INTENTSERVICENOTIFI1.equals( action )){
            Log.d( "tttt 消息：" + intent.getExtras().getSerializable(INTENT_KEY4)  , "线程： " + Thread.currentThread().getName() ) ;
            textView.append("当前intentServicei......."+ intent.getExtras().getSerializable(INTENT_KEY4)  +"\r\n");
        }
    }


}
    private void init() {
        btn_start_intentService=findViewById(R.id.btn_start_intentservice);
        textView=findViewById(R.id.tv_info2);

        btn_start_intentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start_intentservice:
                Log.i(TAG, "btn_start_intentservice: ");
                ToMyIntentService();
                break;
        }
    }

    public void ToMyIntentService(){
        Log.i(TAG, "ToMyIntentService: ");
        MyIntentService myIntentService=new MyIntentService();
        Intent intent=new Intent(this, myIntentService.getClass());
        Bundle bundle=new Bundle();
        bundle.putString(INTENT_KEY2,"当前值i++:      "+i++);
        intent.putExtras(bundle);
        startService(intent);
    }


}
