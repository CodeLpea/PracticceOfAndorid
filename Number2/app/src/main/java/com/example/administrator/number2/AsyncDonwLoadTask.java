package com.example.administrator.number2;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
*Params泛型是String类型，Progress泛型是Object类型，Result泛型是Long类型
* */
public class AsyncDonwLoadTask extends AsyncTask<String,Object,Long> {
    private static final String TAG="AsyncDonwLoadTask";
    private TextView textView;
    private Button btnDownload;

    public AsyncDonwLoadTask(TextView textView, Button btnDownLoad) {
        Log.i(TAG, "AsyncDonwLoadTask: ");
        this.textView=textView;
        this.btnDownload=btnDownLoad;
    }
/**
 * execute会自动执行opPreExecute
* */
    @Override
    protected void onPreExecute() {
        Log.i("iSpring", "DownloadTask -> onPreExecute, Thread name: " + Thread.currentThread().getName());
        super.onPreExecute();
        btnDownload.setEnabled(false);
        textView.setText("开始下载...");
    }
/**
 * onpreExecute之后
 * 会自动执行DoInBackGround
 * 其中params参数， downloadTask.execute(urls);就是execute中传递过来的
* */
    @Override
    protected Long doInBackground(String... params) {
        Log.i("iSpring", "DownloadTask -> doInBackground, Thread name: " + Thread.currentThread().getName());
        //totalByte表示所有下载的文件的总字节数
        long totalByte = 0;
        //params是一个String数组
        for(String url: params){
            //遍历Url数组，依次下载对应的文件
            Object[] result = downloadSingleFile(url);
            int byteCount = (int)result[0];
            totalByte += byteCount;
            //在下载完一个文件之后，我们就把阶段性的处理结果发布出去
            publishProgress(result);
            //如果AsyncTask被调用了cancel()方法，那么任务取消，跳出for循环
            if(isCancelled()){
                break;
            }
        }
        //将总共下载的字节数作为结果返回
        return totalByte;
    }

    //下载文件后返回一个Object数组：下载文件的字节数以及下载的博客的名字
    private Object[] downloadSingleFile(String str){
        Object[] result = new Object[2];
        int byteCount = 0;
        String blogName = "";
        HttpURLConnection conn = null;
        try{
            URL url = new URL(str);
            conn = (HttpURLConnection)url.openConnection();
            InputStream is = conn.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int length = -1;
            while ((length = is.read(buf)) != -1) {
                baos.write(buf, 0, length);
                byteCount += length;
            }
            String respone = new String(baos.toByteArray(), "utf-8");
            int startIndex = respone.indexOf("<title>");
            if(startIndex > 0){
                startIndex += 7;
                int endIndex = respone.indexOf("</title>");
                if(endIndex > startIndex){
                    //解析出博客中的标题
                    blogName = respone.substring(startIndex, endIndex);
                }
            }
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(conn != null){
                conn.disconnect();
            }
        }
        result[0] = byteCount;
        result[1] = blogName;
        return result;
    }
/**
 * 使用publishProgress后被调用
 * */
    @Override
    protected void onProgressUpdate(Object... values) {
        Log.i("iSpring", "DownloadTask -> onProgressUpdate, Thread name: " + Thread.currentThread().getName());
        super.onProgressUpdate(values);
        int byteCount = (int)values[0];
        String blogName = (String)values[1];
        String text = textView.getText().toString();
        text += "\n简书《" + blogName + "》下载完成，共" + byteCount + "字节";
        textView.setText(text);
    }

    /**
     * 整个doInBackground方法执行完毕后，AsyncTask就会回调onPostExecute方法
     * */
    @Override
    protected void onPostExecute(Long aLong) {
        Log.i("iSpring", "DownloadTask -> onPostExecute, Thread name: " + Thread.currentThread().getName());
        super.onPostExecute(aLong);
        String text = textView.getText().toString();
        text += "\n全部下载完成，总共下载了" + aLong + "个字节";
        textView.setText(text);
        btnDownload.setEnabled(true);
    }
/**
 * 可以通过AsyncTask的cancel方法取消任务，取消任务后AsyncTask会回调onCancelled方法，这样不会再调用onPostExecute方法。
 * */
    @Override
    protected void onCancelled() {
        Log.i("iSpring", "DownloadTask -> onCancelled, Thread name: " + Thread.currentThread().getName());
        super.onCancelled();
        textView.setText("取消下载");
        btnDownload.setEnabled(true);
    }
}
