package com.domain.aly.followingapp.reportutil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.domain.aly.followingapp.R;
import com.domain.aly.followingapp.accelerometerutil.AccelerometerReportsActivity;
import com.domain.aly.followingapp.apputil.AppConst;
import com.domain.aly.followingapp.lightutil.LightReportsActivity;
import com.domain.aly.followingapp.locationutil.LocationReportsActivity;
import com.domain.aly.followingapp.noiseutil.NoiseReportsActivity;
import com.domain.aly.followingapp.speedutil.SpeedReportsActivity;

public class ReportFragment extends Fragment implements View.OnClickListener {
    private LinearLayout lightCardLayout, speedCardLayout, noiseCardLayout, accelerometerCardLayout, locationCardLayout;
    private View view;

    public ReportFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.reports_fragment, container, false);

        lightCardLayout = (LinearLayout) view.findViewById(R.id.llLight);
        lightCardLayout.setOnClickListener(this);

        speedCardLayout = (LinearLayout) view.findViewById(R.id.llSpeed);
        speedCardLayout.setOnClickListener(this);

        noiseCardLayout = (LinearLayout) view.findViewById(R.id.llNoise);
        noiseCardLayout.setOnClickListener(this);

        accelerometerCardLayout = (LinearLayout) view.findViewById(R.id.llAccelerometer);
        accelerometerCardLayout.setOnClickListener(this);

        locationCardLayout = (LinearLayout) view.findViewById(R.id.llLocation);
        locationCardLayout.setOnClickListener(this);
        return view;


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
        Intent i = new Intent(getActivity(), LocationReportsActivity.class);
        i.putExtra(AppConst.ACTIVITY_TAG, AppConst.USER_REPORTS_FRAGMENT_TAG);
        startActivity(i);
    }

    private void openAccelerometerReportsActivity() {
        Intent i = new Intent(getActivity(), AccelerometerReportsActivity.class);
        i.putExtra(AppConst.ACTIVITY_TAG, AppConst.USER_REPORTS_FRAGMENT_TAG);
        startActivity(i);
    }

    private void openNoiseReportsActivity() {
        Intent i = new Intent(getActivity(), NoiseReportsActivity.class);
        i.putExtra(AppConst.ACTIVITY_TAG, AppConst.USER_REPORTS_FRAGMENT_TAG);
        startActivity(i);
    }

    private void openSpeedReportsActivity() {
        Intent i = new Intent(getActivity(), SpeedReportsActivity.class);
        i.putExtra(AppConst.ACTIVITY_TAG, AppConst.USER_REPORTS_FRAGMENT_TAG);
        startActivity(i);
    }

    private void openLightReportsActivity() {
        Intent i = new Intent(getActivity(), LightReportsActivity.class);
        i.putExtra(AppConst.ACTIVITY_TAG, AppConst.USER_REPORTS_FRAGMENT_TAG);
        startActivity(i);
    }
}
