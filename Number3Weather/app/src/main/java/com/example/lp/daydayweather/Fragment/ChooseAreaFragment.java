package com.example.lp.daydayweather.Fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lp.daydayweather.Util.Config;
import com.example.lp.daydayweather.Dao.City;
import com.example.lp.daydayweather.Dao.County;
import com.example.lp.daydayweather.Dao.Province;
import com.example.lp.daydayweather.MainActivity;
import com.example.lp.daydayweather.R;
import com.example.lp.daydayweather.Util.HttpUtil;
import com.example.lp.daydayweather.Util.Utility;
import com.example.lp.daydayweather.WeatherActivity;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 选择地点的碎片
 * */
public class ChooseAreaFragment extends Fragment {
    public static final String TAG="ChooseAreaFragment";
    public static final int LEVL_PROVINCE=0;
    public static final int LEVL_CITY=1;
    public static final int LEVL_COUNTY=2;

    private ProgressDialog progressDialog;
    private TextView titleText;
    private Button backButton;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> dataList=new ArrayList<>();
    /*
    * 省列表*/
    private List<Province> provinceList;
    /*
     * 城市列表*/
    private List<City> cityList;
    /*
     * 区县列表*/
    private List<County> countyList;

    /*
    * 选中的省份*/
    private Province selectedProvince;
    /*
     * 选中的城市*/
    private City selectedCity;

    /*
    * 当前选中的级别*/
    private int currentLevel;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.choose_area,container,false);
        titleText=view.findViewById(R.id.title_text);
        backButton=view.findViewById(R.id.back_button);
        listView=view.findViewById(R.id.list_view);
        adapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,dataList);
        listView.setAdapter(adapter);
        return view;
    }
/**
 *在onActivityCreated()调用之前 activity的onCreate可能还没有完成，
 *所以不能再onCreateView()中进行 与activity有交互的UI操作，
 * UI交互操作可以在onActivityCreated()里面进行。*/
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listViewClick();// 处理istView的各级点击事件，刷新listView。（进入）
        backButtonClick();//处理不同级别下的返回事件，刷新listView。（返回）
        queryProvinces();//直接查询省份信息，并显示

    }
/**
 * backButtonClick
 * 点击事件
 * */
    private void backButtonClick() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentLevel==LEVL_COUNTY){
                    queryCities();
                }else if(currentLevel==LEVL_CITY){
                    queryProvinces();
                }
            }
        });
    }

    /**
     * listViewClick
     * 点击事件
     * */
    private void listViewClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int postion, long id) {
                if(currentLevel==LEVL_PROVINCE){
                    selectedProvince=provinceList.get(postion);
                    queryCities();
                }else if(currentLevel==LEVL_CITY){
                    selectedCity=cityList.get(postion);
                    queryCounties();
                }else if(currentLevel==LEVL_COUNTY){
                    String weatherId=countyList.get(postion).getWeatherId();
                    if(getActivity() instanceof MainActivity){//判断当前碎片是否属于MainActivity
                        Intent intent =new Intent(getActivity(), WeatherActivity.class);
                        intent.putExtra("weather_id",weatherId);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else if(getActivity() instanceof  WeatherActivity){
                        WeatherActivity activity=(WeatherActivity) getActivity();
                        activity.drawerLayout.closeDrawers();
                        activity.swipeRefresh.setRefreshing(true);//选择完城市之后，出现刷新的标志。
                        activity.requestWeather(weatherId);
                    }


                }

            }
        });
    }

    /**
*查询全国所有的省份
*有限从数据库查询
 * 如果没有再从服务器上查询
* */
    private void queryProvinces() {
        titleText.setText("中国");
        backButton.setVisibility(View.GONE);
        provinceList= DataSupport.findAll(Province.class);
        if(provinceList.size()>1){
            dataList.clear();
            for(Province province:provinceList){
                dataList.add(province.getProvinceName());
            }
            adapter.notifyDataSetChanged();//更新listView
            listView.setSelection(0);
            currentLevel=LEVL_PROVINCE;
        }else {//如果没有就进行网络请求
            String address= Config.provinceApi;
            queryFromServer(address,Config.province);
        }
    }



    /**
     *查询城市
     *有限从数据库查询
     * 如果没有再从服务器上查询
     * */
    private void queryCities() {
        titleText.setText(selectedProvince.getProvinceName());
        backButton.setVisibility(View.VISIBLE);
        cityList=DataSupport.where("provinceId=?",String.valueOf(selectedProvince.getProvinceCode())).find(City.class);
        if(cityList.size()>1){
            dataList.clear();
            for(City city:cityList){
                dataList.add(city.getCityName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel=LEVL_CITY;
        }
        else{
            int provinceCode=selectedProvince.getProvinceCode();
            String address=Config.provinceApi+"/"+provinceCode;
            queryFromServer(address,Config.city);
        }
    }
    /**
     *查询区县
     *有限从数据库查询
     * 如果没有再从服务器上查询
     * */
    private void queryCounties() {
        titleText.setText(selectedCity.getCityName());
        backButton.setVisibility(View.VISIBLE);
        countyList=DataSupport.where("cityId=?",String.valueOf(selectedCity.getCityCode())).find(County.class);
        if(countyList.size()>1){
            dataList.clear();
            for(County county:countyList){
                dataList.add(county.getCountyName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel=LEVL_COUNTY;
        }else {
            int provinceCode=selectedProvince.getProvinceCode();
            int cityCode=selectedCity.getCityCode();
            String address=Config.provinceApi+"/"+provinceCode+"/"+cityCode;
            queryFromServer(address,Config.county);
        }

    }
/**
 * 从服务器上查询
 * */
    private void queryFromServer(String address, final String type) {
        showProgressDialog();
        HttpUtil.getInstance().sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {//runOnUiThread回到主线程处理逻辑
                    @Override
                    public void run() {
                        closeProgressDialog();
                        Toast.makeText(getActivity(),"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            String responseText=response.body().string();
            Log.i(TAG, "onResponse: "+responseText);
            boolean result=false;
            switch (type){
                case "province":
                    result= Utility.getInstance().handleProvinceResponse(responseText);
                    break;
                case "city":
                    result=Utility.getInstance().handleCityResponse(responseText,selectedProvince.getProvinceCode());
                    break;
                case "county":
                    result=Utility.getInstance().handleCountyResponse(responseText,selectedCity.getCityCode());
                    break;
                    default:
                        Log.i(TAG, "onResponse: default");
                        break;
            }
            if(result){
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closeProgressDialog();
                        switch (type){
                            case "province":
                          queryProvinces();
                                break;
                            case "city":
                          queryCities();
                                break;
                            case "county":
                          queryCounties();
                                break;
                            default:
                                break;
                        }
                    }
                });
            }


            }
        });
    }
/**
 * 显示进度条对话框
 * */
    private void showProgressDialog() {
        if(progressDialog==null){
            progressDialog=new ProgressDialog(getActivity());
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }
    /**
     * 关闭对话框*/
    private void closeProgressDialog() {
        if(progressDialog!=null){
            progressDialog.dismiss();
        }

    }


}
