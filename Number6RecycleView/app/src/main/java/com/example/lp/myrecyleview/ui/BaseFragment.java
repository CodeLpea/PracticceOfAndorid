package com.example.lp.myrecyleview.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * lp
 * 2019年3月30日
 */
public abstract class BaseFragment extends Fragment {

    public FragmentActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        View view = initView(inflater, container, savedInstanceState);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
    }


    public abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    public abstract void initData(@Nullable Bundle savedInstanceState);


}
