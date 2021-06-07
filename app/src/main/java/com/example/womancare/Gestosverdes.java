package com.example.womancare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class Gestosverdes extends AppCompatActivity {
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    int whip=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestosverdes);
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensor= sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        if (sensor==null)
            finish();

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float x = sensorEvent.values[0];
                if (x < -5 && whip == 0) {
                    whip++;
                } else if (x >5 && whip == 1) {
                    whip++;
                    Intent intent = new Intent(Gestosverdes.this, SosActivity.class);
                    startActivity( intent );
                }
                if (whip == 2) {
                    whip = 0;

                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }

        };
    }
    private  void star(){
        sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
    private void stop(){
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onPause() {
        stop();
        super.onPause();
    }

    @Override
    protected void onResume() {
        star();
        super.onResume();
    }
}