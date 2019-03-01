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

public class down02Fragment  extends Fragment {
    private static final String TAG="down02Fragment";
   private TextView textView;

    public down02Fragment() {
        // Required empty public constructor
        Log.i(TAG, "down02Fragment: ");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "down02Fragment onCreateView: ");
        View view= inflater.inflate(R.layout.fragment_down02, container, false);
        textView=view.findViewById(R.id.tv_02);
        textView.setTextColor(Color.RED);
        textView.setText("down02Fragment");
        return view;
    }



    @Override
    public void onAttach(Activity activity) {
        Log.i(TAG, "down02Fragment onAttach: ");
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "down02Fragment onDetach: ");

    }

}

