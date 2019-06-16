package com.example.didalife;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class SurfaceViewTempalte extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    //绘制线程
    private Thread t;
    // 线程控制开关
    private  boolean isRunning;

    public SurfaceViewTempalte(Context context) {
        this(context,null);
    }

    public SurfaceViewTempalte(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHolder = getHolder();
        mHolder.addCallback(this);
        //可获得焦点
        setFocusable(true);
        setFocusableInTouchMode(true);
        //设置常量
        setKeepScreenOn(true);
    }



    private void setSupportActionBar(Toolbar toolbar) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRunning = true;
        t = new Thread((Runnable) this);
        t.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRunning = false;

    }
    public void run(){
        while (isRunning){
            draw();
        }
    }
    public  void  draw() {
        try {


        mCanvas = mHolder.lockCanvas();
        if (mCanvas != null) {

        }
    }catch(Exception e)

    {
        e.printStackTrace();
    }
    finally {

            if (mCanvas!= null){
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }

    }

}
