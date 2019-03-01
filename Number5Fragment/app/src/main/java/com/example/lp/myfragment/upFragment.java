package com.example.lp.myfragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class upFragment extends Fragment {
    private static final String TAG="upFragment";
    private TextView textView;

    public upFragment() {
        // Required empty public constructor
        Log.i(TAG, "upFragment: ");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i(TAG, "onCreateView: ");
        View view= inflater.inflate(R.layout.fragment_up, container, false);
        textView=view.findViewById(R.id.tv_up);
        textView.setText("测试上方碎片");
        textView.setTextColor(Color.BLUE);
        return view;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i(TAG, "onAttach: ");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach: ");

    }

}
