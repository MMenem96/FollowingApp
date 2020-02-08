package com.domain.aly.followingapp.followingutil;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.domain.aly.followingapp.R;
import com.domain.aly.followingapp.accelerometerutil.AccelerometerReportsActivity;
import com.domain.aly.followingapp.apputil.AppConst;
import com.domain.aly.followingapp.lightutil.LightReportsActivity;
import com.domain.aly.followingapp.locationutil.LocationReportsActivity;
import com.domain.aly.followingapp.noiseutil.NoiseReportsActivity;
import com.domain.aly.followingapp.speedutil.SpeedReportsActivity;

public class FollowingReportsActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout lightCardLayout, speedCardLayout, noiseCardLayout, accelerometerCardLayout, locationCardLayout;

    private TextView tvFollowingName, tvFollowingPhone;
    private String followingName;
    private Long followingPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following_reports);
        lightCardLayout = (LinearLayout) findViewById(R.id.llLight);
        lightCardLayout.setOnClickListener(this);

        speedCardLayout = (LinearLayout) findViewById(R.id.llSpeed);
        speedCardLayout.setOnClickListener(this);

        noiseCardLayout = (LinearLayout) findViewById(R.id.llNoise);
        noiseCardLayout.setOnClickListener(this);

        accelerometerCardLayout = (LinearLayout) findViewById(R.id.llAccelerometer);
        accelerometerCardLayout.setOnClickListener(this);

        locationCardLayout = (LinearLayout) findViewById(R.id.llLocation);
        locationCardLayout.setOnClickListener(this);

        tvFollowingName = (TextView) findViewById(R.id.tvFollowinName);
        tvFollowingPhone = (TextView) findViewById(R.id.tvFollowingPhone);

        try {
            followingName = getIntent().getExtras().get(AppConst.FOLLOWING_NAME).toString();
            followingPhone = getIntent().getExtras().getLong(AppConst.FOLLOWING_PHONE);
            tvFollowingName.setText("Following name: " + followingName);
            tvFollowingPhone.setText("Following phone: " + followingPhone);
        } catch (NullPointerException e) {
            Log.e("followingNamePhone", e.getMessage());

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llLight:
                openLightReportsActivity();
                break;
            case R.id.llSpeed:
                openSpeedReportsActivity();
                break;
            case R.id.llNoise:
                openNoiseReportsActivity();
                break;
            case R.id.llAccelerometer:
                openAccelerometerReportsActivity();
                break;
            case R.id.llLocation:
                openLocationReportsActivity();
                break;

        }


    }


    private void openLocationReportsActivity() {
        Intent i = new Intent(this, LocationReportsActivity.class);
        i.putExtra(AppConst.FOLLOWING_PHONE, followingPhone);
        i.putExtra(AppConst.ACTIVITY_TAG, AppConst.FOLLOWING_REPORTS_ACTIVITY_TAG);
        startActivity(i);
    }

    private void openAccelerometerReportsActivity() {
        Intent i = new Intent(this, AccelerometerReportsActivity.class);
        i.putExtra(AppConst.FOLLOWING_PHONE, followingPhone);
        i.putExtra(AppConst.ACTIVITY_TAG, AppConst.FOLLOWING_REPORTS_ACTIVITY_TAG);
        startActivity(i);
    }

    private void openNoiseReportsActivity() {
        Intent i = new Intent(this, NoiseReportsActivity.class);
        i.putExtra(AppConst.FOLLOWING_PHONE, followingPhone);
        i.putExtra(AppConst.ACTIVITY_TAG, AppConst.FOLLOWING_REPORTS_ACTIVITY_TAG);
        startActivity(i);
    }

    private void openSpeedReportsActivity() {
        Intent i = new Intent(this, SpeedReportsActivity.class);
        i.putExtra(AppConst.FOLLOWING_PHONE, followingPhone);
        i.putExtra(AppConst.ACTIVITY_TAG, AppConst.FOLLOWING_REPORTS_ACTIVITY_TAG);
        startActivity(i);
    }

    private void openLightReportsActivity() {
        Intent i = new Intent(this, LightReportsActivity.class);
        i.putExtra(AppConst.FOLLOWING_PHONE, followingPhone);
        i.putExtra(AppConst.ACTIVITY_TAG, AppConst.FOLLOWING_REPORTS_ACTIVITY_TAG);
        startActivity(i);
    }
}
