package com.example.lp.daydayweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lp.daydayweather.Config.Config;
import com.example.lp.daydayweather.Dao.Province;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void initTest() {
        LitePal.getDatabase();
    }
}
