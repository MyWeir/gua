package com.example.yls.gua;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by yls on 2017/6/5.
 */

public class ScrapeView extends View{
    private Bitmap bpBackground;
    private Bitmap bpForeground;
    private Canvas mCanvas;
    private Paint pathPaint;
    private Path mPath;

    private Paint contentPaint;
    private String content="刮刮看咯";

    public ScrapeView(Context context) {
        super(context);
        init();
    }
    public ScrapeView(Context context, AttributeSet attrs) {
        super(context,attrs);
        init();
    }

    private void init() {
        pathPaint=new Paint();
        pathPaint.setAlpha(0);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(50);
        pathPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        pathPaint.setStrokeJoin(Paint.Join.ROUND);
        pathPaint.setStrokeCap(Paint.Cap.ROUND);

        mPath=new Path();
        BitmapFactory.Options option=new BitmapFactory.Options();
        option.outHeight=1280;
        option.outWidth=500;
        bpBackground= BitmapFactory.decodeResource(getResources(),R.drawable.test,option);
        bpForeground=Bitmap.createBitmap(bpBackground.getWidth(),bpBackground.getHeight(),Bitmap.Config.ARGB_8888);
        mCanvas=new Canvas(bpForeground);
        contentPaint=new Paint();
        contentPaint.setColor(Color.BLACK);
        contentPaint.setTextSize(100);
        contentPaint.setStrokeWidth(20);
        mCanvas.drawColor(Color.GRAY);
        mCanvas.drawText(content,mCanvas.getWidth()/4,mCanvas.getHeight()/2,contentPaint);

    }
    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(),event.getY());
                break;
        }
        mCanvas.drawPath(mPath,pathPaint);
        invalidate();
        return true;

    }
    protected  void  onDraw(Canvas canvas){
        canvas.drawBitmap(bpBackground,0,0,null);
        canvas.drawBitmap(bpForeground,0,0,null);
    }
}
