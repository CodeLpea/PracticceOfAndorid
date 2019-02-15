package com.example.lp.daydayweather.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    private static TimeUtils instance=null;
    public static synchronized TimeUtils getInstance(){
        if(instance==null){
            instance=new TimeUtils();
        }
        return instance;
    }
    /**传入开始时间和结束时间字符串来计算消耗时长
     * */
    public long getTimeExpend(String startTime, String endTime){
        //传入字串类型 2016年06月28日 08:30
        long longStart = getTimeMillis(startTime); //获取开始时间毫秒数
        long longEnd = getTimeMillis(endTime);  //获取结束时间毫秒数
        long longExpend = longEnd - longStart;  //获取时间差

        long longHours = longExpend / (60 * 60 * 1000); //根据时间差来计算小时数
        long longMinutes = (longExpend - longHours * (60 * 60 * 1000)) / (60 * 1000);   //根据时间差来计算分钟数

        //return longHours + ":" + longMinutes;//返回的是小时：分钟的形式。
        return longHours;
    }



    private long getTimeMillis(String strTime) {
        long returnMillis = 0;
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
        Date d = null;
        try {
            d = sdf.parse(strTime);
            returnMillis = d.getTime();
        } catch (ParseException e) {

        }
        return returnMillis;
    }
}
