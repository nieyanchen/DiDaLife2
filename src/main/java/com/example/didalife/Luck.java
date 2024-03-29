package com.example.didalife;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class luck extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    //绘制线程
    private Thread t;
    // 线程控制开关
    private  boolean isRunning;
    //名称
    private String[]mStr = new String[]{"食堂","米线","火锅","汉堡","烧烤","修仙不吃"};
   //图片
    private int[] mImgs = new int[]{R.drawable.shitang,R.drawable.miantiao,R.drawable.huoguo,R.drawable.hanbao,R.drawable.shaokao,R.drawable.buchi};
    private  Bitmap[]mImgsBitmap;
    private Bitmap mBgBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.pan);
    private  int[] mColors =new int[]{0xFFFC2025,0XFFF17E01,0xFFFC2025,0XFFF17E01,0xFFFC2025,0XFFF17E01,};

    private  int mItemCount = 6;
    private Paint mArcPaint;
    private Paint mTextPaint;



    private  float mTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,20,getResources().getDisplayMetrics());
    private RectF mRange = new RectF();
    private  int mRadius;
    private  double mSpeed ;
    //角度
    private  volatile int mStartAngle = 0;
    //是否停止
    private  boolean isShouldEnd;
    //中心位置
    private  int mCenter;

    private  int mPadding;



    public luck(Context context) {
        this(context,null);
    }

    public luck(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = Math.min(getMeasuredWidth(),getMeasuredHeight());
        mPadding = getPaddingLeft();
        mRadius = width - mPadding*2;//半径
        mCenter = width/2;//中心
        setMeasuredDimension(width,width);

    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setDither(true);

        mTextPaint= new Paint();
        mTextPaint.setColor(0xffffffff);
        mTextPaint.setTextSize(mTextSize);
        mRange = new RectF(mPadding,mPadding,mPadding+mRadius,mPadding+mRadius);
        mImgsBitmap = new Bitmap[mItemCount];
        for (int i = 0; i < mItemCount;i++){
            mImgsBitmap[i] = BitmapFactory.decodeResource(getResources(),mImgs[i]);
        }

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
            long start = System.currentTimeMillis();
            draw();
            long end =System.currentTimeMillis();
            if (end - start<50){
                try {


                    Thread.sleep(50 - (end - start));
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public  void  draw() {
        try {


        mCanvas = mHolder.lockCanvas();
        if (mCanvas != null) {
            drawBg();
            //盘
            float tmpAngle = mStartAngle;
            float sweepAngle = 360/mItemCount;
            for(int i = 0; i < mItemCount;i++){

                mArcPaint.setColor(mColors[i]);
                mCanvas.drawArc(mRange,tmpAngle,sweepAngle,true,mArcPaint);
                drawText(tmpAngle,sweepAngle,mStr[i]);

                drawIcon(tmpAngle,mImgsBitmap[i]);
                tmpAngle +=sweepAngle;

            }
            mStartAngle += mSpeed;
            if (isShouldEnd)
            {
                mSpeed-=1;
            }
            if (mSpeed<=0){
                mSpeed=0;
                isShouldEnd =false;
            }

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
    public void luckyStart(){
        mSpeed=50;
        isShouldEnd=false;
    }
    public void luckyEnd(){
        isShouldEnd=true;
    }
    public boolean isStart(){
        return mSpeed!=0;

    }
    public boolean isShouldEnd(){
        return isShouldEnd;
    }

    private void drawIcon(float tmpAngle, Bitmap bitmap) {

        int imgWidth = mRadius/8;
        float angle = (float) ((tmpAngle+360/mItemCount/2)*Math.PI/180);
        int x = (int) (mCenter+mRadius/2/2*Math.cos(angle));
        int y = (int) (mCenter+mRadius/2/2*Math.sin(angle));

        Rect rect = new Rect(x-imgWidth/2,y-imgWidth/2,x+imgWidth/2,y+imgWidth/2);
        mCanvas.drawBitmap(bitmap,null,rect,null);
    }

    private void drawText(float tmpAngle, float sweepAngle, String string) {
        Path path =new Path();
        path.addArc(mRange,tmpAngle,sweepAngle);

        //文字居中
        float textWidth = mTextPaint.measureText(string);
        int hOffset =(int)(mRadius *Math.PI/mItemCount/2-textWidth/2);
        int vOffset = mRadius/2/6;
        mCanvas.drawTextOnPath(string,path,hOffset,vOffset,mTextPaint);


    }

    private void drawBg() {
        mCanvas.drawColor(0xFFFFFFFF);
        mCanvas.drawBitmap(mBgBitmap,null,new Rect(mPadding/2,mPadding/2,getMeasuredWidth()-mPadding/2,getMeasuredWidth()-mPadding/2),null);
    }

}
