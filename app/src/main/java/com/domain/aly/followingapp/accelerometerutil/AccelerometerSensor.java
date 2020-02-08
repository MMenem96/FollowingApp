package com.domain.aly.followingapp.accelerometerutil;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AccelerometerSensor implements SensorEventListener {

    public static interface AccelerometerSensorListener {
        abstract void onAccelerometerSensorChanged(Double x, Double y, Double z);
    }

    private AccelerometerSensorListener listener;

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;

    public AccelerometerSensor(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void setListener(AccelerometerSensorListener listener) {
        this.listener = listener;
    }

    public boolean register() {
        return sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (listener != null) {
            try {
                listener.onAccelerometerSensorChanged(Double.valueOf(event.values[0]), Double.valueOf(event.values[1]), Double.valueOf(event.values[2]));

            } catch (NullPointerException e) {

            }
        }
    }

    public void unregister() {
        sensorManager.unregisterListener(this);
    }

}
