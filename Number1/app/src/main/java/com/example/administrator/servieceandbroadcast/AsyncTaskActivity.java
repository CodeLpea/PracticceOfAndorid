package com.example.administrator.servieceandbroadcast;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.servieceandbroadcast.Sync.AsyncDonwLoadTask;

/**
 * 使用AsyncTask
 * 简单的异步操作
* */
public class AsyncTaskActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG="AsyncTaskActivity";
    private TextView textView;
    private Button btnDownLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        init();
    }
    private void init() {
        textView=findViewById(R.id.tv_info3);
        btnDownLoad=findViewById(R.id.btn_start_async_task_down);

        btnDownLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start_async_task_down:
                Log.i(TAG, "onClick:btn_start_async_task ");
                /*开启AsynvTask异步下载*/
                String[] urls = {
                        "https://www.jianshu.com/p/ce7239eac866",
                        "https://www.jianshu.com/p/afbc4ea2f867",
                        "https://www.jianshu.com/p/433166ce0536",
                        "https://www.jianshu.com/p/07d54c6712e3",
                        "https://www.jianshu.com/p/1459410b3508"
                };
                AsyncDonwLoadTask downloadTask = new AsyncDonwLoadTask(textView,btnDownLoad);
                downloadTask.execute(urls);//注意一个AsyncTask实例，只能执行一次excute。
                break;
                default:
                    break;
        }
    }
}
