package com.example.lp.mydraw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.lp.mydraw.MyView.MoveView;

public class MainActivity extends AppCompatActivity {
    private MoveView moveView;
    private TextView locationInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moveView=findViewById(R.id.mv_move);
        locationInfo=findViewById(R.id.tv_info);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏

    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                locationInfo.setText("当前坐标\n"+event.getX()+" , "+event.getY());
            }
        });
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                moveView.setPlane(event.getX(),event.getY());
                moveView.invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                moveView.setPlane(event.getX(),event.getY());
                moveView.invalidate();
                break;
        }

        return super.onTouchEvent(event);

    }
}
