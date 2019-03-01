package com.example.lp.myfragment;

import android.app.Activity;

import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class down01Fragment  extends Fragment {
    private static final String TAG="down01Fragment";
    private TextView textView;

    public down01Fragment() {
        // Required empty public constructor
        Log.i(TAG, "down01Fragment: ");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "down01Fragment onCreateView: ");
        View view= inflater.inflate(R.layout.fragment_down01, container, false);
        textView=view.findViewById(R.id.tv_01);
        textView.setTextColor(Color.GREEN);
        textView.setText("down01Fragment");
        return view;
    }



    @Override
    public void onAttach(Activity activity) {
        Log.i(TAG, "down01Fragment onAttach: ");
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        Log.i(TAG, "down01Fragment onDetach: ");
        super.onDetach();

    }

}

