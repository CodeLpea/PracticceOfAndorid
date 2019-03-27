package com.example.lp.myrecyleview.ItemDecoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
/**
 * lp
 * 2019年3月30日
 * 间隔
 * */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    int mSpace;
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;
        if (parent.getChildAdapterPosition(view) != 0) {//第一行不设置
            outRect.top = mSpace;
        }
    }
    public SpaceItemDecoration(int space) {

        this.mSpace = space;

    }

}