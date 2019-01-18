package com.example.administrator.servieceandbroadcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.servieceandbroadcast.Service.MyIntentService;

import static com.example.administrator.servieceandbroadcast.uils.ConfigKey.INTENT_KEY2;

public class IntentServiceActivity extends AppCompatActivity implements View.OnClickListener {
    private  static  final String TAG="IntentServiceActivity";

    private int i=0;
    private Button btn_start_intentService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
        Log.i(TAG, "onCreate: ");
        init();
    }

    private void init() {
        btn_start_intentService=findViewById(R.id.btn_start_intentservice);

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
        Intent intent=new Intent(this, MyIntentService.class);
        Bundle bundle=new Bundle();
        bundle.putString(INTENT_KEY2,"当前值i++:      "+i++);
        intent.putExtras(bundle);
        startService(intent);
    }
}
