package com.example.lp.mydraw.MyView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.lp.mydraw.R;

public class MoveView extends View {

    private Bitmap bitmap;
    private float planeX=1f;
    private float planeY=1f;
    public Paint paint = new Paint();
    public MoveView(Context context) {
        super(context);
    }

    public MoveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setDither(true);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tanke);//通过使用bitmapfactory的decoderesource来获得bitmap
    }

    public MoveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
         //canvas.drawCircle(planeX, planeY, 20, paint);
        canvas.drawBitmap(bitmap,planeX-bitmap.getWidth()/2,planeY-bitmap.getHeight()/2,paint);
    }
    public void setPlane(float x,float y) {
        planeX=x;
        planeY=y;

    }
}
