package com.example.lp.myfragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG="MainActivity";
    private FragmentTransaction fragmentTransaction;

    private down01Fragment down01Fragment;
    private down02Fragment down02Fragment;

    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
    }

    private void initFragment() {
        Log.i(TAG, "initFragment: ");
       //fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        down01Fragment=new down01Fragment();
        down02Fragment=new down02Fragment();
        fragmentTransaction.replace(R.id.fragment_donw,down01Fragment);//replace的形式是每次都刷新，add show不会刷新，只是hide隐藏起来
        fragmentTransaction.commit();

    }

    private void initView() {
        Log.i(TAG, "initView: ");
        button1=findViewById(R.id.bt_1);
        button2=findViewById(R.id.bt_2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_1:
                fragmentTransaction.replace(R.id.fragment_donw,down01Fragment);
                fragmentTransaction.commit();
                break;
            case R.id.bt_2:
                fragmentTransaction.replace(R.id.fragment_donw,down02Fragment);
                fragmentTransaction.commit();
                break;
        }
    }
}
