package com.example.didalife;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import static android.content.Context.SENSOR_SERVICE;
import static android.content.Context.VIBRATOR_SERVICE;


public class LuckFragment extends Fragment {
    private SensorManager sensorManager;
    private Vibrator vibrator;
    private luck mLuckyPan;

    Toolbar toolbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view4 = inflater.inflate(R.layout.frame_luck, container, false);
        //显示标题栏
        toolbar=view4.findViewById(R.id.toolbar2);
        toolbar.setLogo(R.mipmap.dida);
        toolbar.setTitle("DiDa Life");
        toolbar.setSubtitle("您的生活小助手");
        LuckFragment.this.setSupportActionBar(toolbar);

        sensorManager = (SensorManager)getActivity().getSystemService(SENSOR_SERVICE);
        vibrator = (Vibrator)getActivity().getSystemService(VIBRATOR_SERVICE);
        mLuckyPan = view4.findViewById(R.id.id_luckyPan);


        return view4;
    }
    public void onResume(){
        super.onResume();
        if (sensorManager !=null){//注册监听器
            sensorManager.registerListener(sensorEventListener,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
            //第一个参数是listener，第二个参数是所得传感器类型，第三个参数值获取传感器信息的频率
        }
    }
    public void  onStop(){
        super.onStop();
        if (sensorManager !=null){ //取消监听器
            sensorManager.unregisterListener(sensorEventListener);
        }

    }
    //重力感应监听器
    private SensorEventListener sensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            //传感器信息改变时执行该方法
            float[] values = event.values;
            float x = values[0]; //x轴方向的重力加速度，向右为正
            float y = values[1]; //y轴方向的重力加速度，向前为正
            float z = values[2]; //z轴方向的重力加速度，向上为正

            //在这个方向的重力加速度达到40就达到了手机摇晃的状态
            int medumValue = 10;
            if(Math.abs(x)>medumValue || Math.abs(y)>medumValue || Math.abs(z)>medumValue){
                vibrator.vibrate(200);
                Message msg = new Message();
                msg.what = 10;
                handler.sendMessage(msg);
            }else if (Math.abs(x)<medumValue || Math.abs(y)<medumValue || Math.abs(z)<medumValue){
                Message msg = new Message();
                msg.what = 20;
                handler.sendMessage(msg);


            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case 10:
                    mLuckyPan.luckyStart();
                    Toast.makeText(getActivity(),"停止摇动,转盘停止",Toast.LENGTH_LONG).show();
                case 20:
                    mLuckyPan.luckyEnd();
                    
                    break;
            }
        }
    };
    private Object getSystemService(String sensorService) {
        return true;
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }
}
