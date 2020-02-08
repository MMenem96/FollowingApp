package com.domain.aly.followingapp.lightutil;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class LightSensor implements SensorEventListener {

    public static interface LightSensorListener {
        abstract void onLightSensorChanged(double lux);
    }

    private LightSensorListener listener;

    private SensorManager mSensorManagerx;
    private Sensor lightManager;

    public LightSensor(Context context) {
        mSensorManagerx = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        lightManager = mSensorManagerx.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    public void setListener(LightSensorListener listener) {
        this.listener = listener;
    }

    public boolean register() {
        return mSensorManagerx.registerListener(this, lightManager, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (listener != null) {
            listener.onLightSensorChanged(Double.valueOf(event.values[0]));
        }
    }

    public void unregister() {
        mSensorManagerx.unregisterListener(this);
    }
}



