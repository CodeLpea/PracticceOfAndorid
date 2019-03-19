package com.example.lp.myrecyleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lp.myrecyleview.Adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private List<String> mList=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList.add("1");
        mList.add("2");
        mList.add("3");
        mList.add("4");
        mList.add("5");
        mList.add("6");
        mList.add("7");
        init();
    }

    private void init() {
        recyclerView=findViewById(R.id.id_recycleview);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);    //设置排列方式
        recyclerView.setLayoutManager(linearLayoutManager);

        //设置item增加和删除的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        homeAdapter=new HomeAdapter(this,mList);
        recyclerView.setAdapter(homeAdapter);




    }
}
