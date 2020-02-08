package com.domain.aly.followingapp.apputil;

import android.content.Context;
import android.content.SharedPreferences;

public class UserCachedInfo {

    private Context context;
    final static String PREFS_NAME = "followingApp_prefs";

    public UserCachedInfo(Context context) {
        this.context = context;
    }


    //Our class to cache user info using sharedPref.

    public void savePhone(String key, Long userPhone) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(key, userPhone);
        editor.apply();
    }

    public Long getPhone() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getLong(AppConst.USER_PHONE, 0);
    }

    public void saveName(String key, String userName) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, userName);
        editor.apply();
    }

    public String getName() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getString(AppConst.USER_NAME, "");
    }

    public void deleteAll() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear().apply();
    }


    //Get and Save reports sizes.

    public void saveLightReportsSize(Long lightReportSize) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(AppConst.LIGHT_REPORT_SIZE, lightReportSize);
        editor.apply();
    }

    public Long getLightReportsSize() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getLong(AppConst.LIGHT_REPORT_SIZE, 0);
    }

    public void saveSpeedReportsSize(Long speedReportSize) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(AppConst.SPEED_REPORT_SIZE, speedReportSize);
        editor.apply();
    }

    public Long getSpeedReportsSize() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getLong(AppConst.SPEED_REPORT_SIZE, 0);
    }

    public void saveNoiseReportsSize(Long noiseReportSize) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(AppConst.NOISE_REPORT_SIZE, noiseReportSize);
        editor.apply();
    }

    public Long getNoiseReportsSize() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getLong(AppConst.NOISE_REPORT_SIZE, 0);
    }

    public void saveLocationReportsSize(Long locationReportSize) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(AppConst.LOCATION_REPORT_SIZE, locationReportSize);
        editor.apply();
    }

    public Long getLocationReportsSize() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getLong(AppConst.LOCATION_REPORT_SIZE, 0);
    }

    public void saveAccelerometerReportsSize(Long accelerometerReportSize) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(AppConst.ACCELEROMETER_REPORT_SIZE, accelerometerReportSize);
        editor.apply();
    }

    public Long getAccelerometerReportsSize() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getLong(AppConst.ACCELEROMETER_REPORT_SIZE, 0);
    }


    //Get and save Sensors.

    public void saveLightSensor(float lightValue) {

        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat(AppConst.LIGHT_VALUE, lightValue);
        editor.apply();
    }

    public float getLightSensor() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getFloat(AppConst.LIGHT_VALUE, 0);
    }

    public void saveLightSensorCount(int lightCount) {

        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(AppConst.LIGHT_COUNT, lightCount);
        editor.apply();
    }

    public int getLightSensorCount() {

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getInt(AppConst.LIGHT_COUNT, 1);
    }

    public void saveNoiseSensor(float noiseValue) {

        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat(AppConst.NOISE_VALUE, noiseValue);
        editor.apply();
    }

    public float getNoiseSensor() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getFloat(AppConst.NOISE_VALUE, 0);
    }

    public void saveNoiseSensorCount(int noiseCount) {

        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(AppConst.NOISE_COUNT, noiseCount);
        editor.apply();
    }

    public int getNoiseSensorCount() {

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getInt(AppConst.NOISE_COUNT, 1);
    }

    public void saveAccelerometerSensor(float accelerometerValue) {

        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat(AppConst.ACCELEROMETER_VALUE, accelerometerValue);
        editor.apply();
    }

    public float getAccelerometerSensor() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getFloat(AppConst.ACCELEROMETER_VALUE, 0);
    }

    public void saveAccelerometerSensorCount(int accelerometerCount) {

        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(AppConst.ACCELEROMETER_COUNT, accelerometerCount);
        editor.apply();
    }

    public int getAccelerometerSensorCount() {

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getInt(AppConst.ACCELEROMETER_COUNT, 1);
    }


    public void saveSpeedSensor(float speedValue) {

        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat(AppConst.SPEED_VALUE, speedValue);
        editor.apply();
    }

    public float getSpeedSensor() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getFloat(AppConst.SPEED_VALUE, 0);
    }

    public void saveSpeedSensorCount(int speedCount) {

        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(AppConst.SPEED_COUNT, speedCount);
        editor.apply();
    }

    public int getSpeedensorCount() {

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getInt(AppConst.SPEED_COUNT, 1);
    }

    public void saveUrlSensor(String urlValue) {

        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(AppConst.URL_VALUE, urlValue);
        editor.apply();
    }

    public String getUrlSensor() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getString(AppConst.URL_VALUE, "");
    }

}
