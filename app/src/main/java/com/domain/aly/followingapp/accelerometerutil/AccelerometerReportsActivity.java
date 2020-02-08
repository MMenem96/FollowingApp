package com.domain.aly.followingapp.accelerometerutil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.domain.aly.followingapp.R;
import com.domain.aly.followingapp.apiinterface.ApiClient;
import com.domain.aly.followingapp.apiinterface.ApiInterface;
import com.domain.aly.followingapp.apputil.AppConst;
import com.domain.aly.followingapp.apputil.UserCachedInfo;
import com.domain.aly.followingapp.loginutil.LoginActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AccelerometerReportsActivity extends AppCompatActivity {
    private Long userPhone;
    private Date currentTime;
    private Retrofit retrofit;
    private RecyclerView accelerometerRecyclerView;
    private List<AccelerometerResponse> accelerometerResponse;
    private AccelerometerAdapter accelerometerAdapter;
    private String activityTag;
    private long followingPhone;
    private UserCachedInfo userCachedInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_report);
        currentTime = Calendar.getInstance().getTime();
        retrofit = ApiClient.getClient();
        accelerometerRecyclerView = (RecyclerView) findViewById(R.id.rvAccelerometers);
        accelerometerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        try {
            activityTag = getIntent().getExtras().getString(AppConst.ACTIVITY_TAG);
        } catch (NullPointerException e) {
            Log.e(activityTag, "null");
        }
        checkUserIsLoggedIn();

    }

    private void checkUserIsLoggedIn() {
        userCachedInfo = new UserCachedInfo(this);
        if (userCachedInfo.getPhone() != 0 && !userCachedInfo.getName().isEmpty()) {
            userPhone = userCachedInfo.getPhone();
            if (activityTag.equals(AppConst.USER_REPORTS_FRAGMENT_TAG)) {
                makeAccelerometerRequest(userPhone);
            } else {
                try {
                    followingPhone = getIntent().getExtras().getLong(AppConst.FOLLOWING_PHONE);
                    makeAccelerometerRequest(followingPhone);
                } catch (NullPointerException e) {
                    Log.e(activityTag, "null");
                }
            }
        } else {
            openLoginActivity();
        }
    }

    private void openLoginActivity() {
        Intent intent = new Intent(AccelerometerReportsActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    public void makeAccelerometerRequest(Long phone) {
        // showDialog(context.getString(R.string.getting_light_txt));
        Log.e("phone", phone + "");
        ApiInterface apiService = retrofit.create(ApiInterface.class);
        Call<List<AccelerometerResponse>> call = apiService.getAccelerometerResponse(phone);
        call.enqueue(new Callback<List<AccelerometerResponse>>() {


            @Override
            public void onResponse(Call<List<AccelerometerResponse>> call, Response<List<AccelerometerResponse>> response) {
                // closeDialog();
                if (response.isSuccessful()) {
                    accelerometerResponse = response.body();
                    if (accelerometerResponse != null) {
                        accelerometerAdapter = new AccelerometerAdapter(accelerometerResponse, getApplicationContext());
                        accelerometerRecyclerView.setAdapter(accelerometerAdapter);

                    }
                } else {
                    Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.no_data_txt), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<AccelerometerResponse>> call, Throwable t) {
                //  closeDialog();
                Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.network_txt), Toast.LENGTH_LONG).show();
                Log.e("acc", t.getMessage());

            }
        });
    }

}
