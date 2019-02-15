package com.example.lp.daydayweather;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
/**
 * BaseActivity
 * */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeBackGroundFuSion();//将背景图片与状态栏融合到一起
    }
    /**
     * 将背景图片与状态栏融合到一起
     * */
    private void makeBackGroundFuSion() {
        if(Build.VERSION.SDK_INT >= 21){
            View decorView=getWindow().getDecorView();//难道当前活动的DecorView
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE);//改变系统UI的显示：活动布局的现实在状态栏上面
            getWindow().setStatusBarColor(Color.TRANSPARENT);//将状态栏设置成透明
/***
 * 此处还会存在显示过于贴近状态栏的问题
 * 需要在相应的layout中，添加 android:fitsSystemWindows="true"
 * 表示为系统状态栏留出空间
 * */
        }
    }

}
