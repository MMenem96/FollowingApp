package com.domain.aly.followingapp.noiseutil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.domain.aly.followingapp.R;
import com.domain.aly.followingapp.apiinterface.ApiClient;
import com.domain.aly.followingapp.apiinterface.ApiInterface;
import com.domain.aly.followingapp.apputil.AppConst;
import com.domain.aly.followingapp.apputil.UserCachedInfo;
import com.domain.aly.followingapp.lightutil.LightAdapter;
import com.domain.aly.followingapp.lightutil.LightSensor;
import com.domain.aly.followingapp.loginutil.LoginActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class NoiseReportsActivity extends AppCompatActivity {
    private Date currentTime;
    private Retrofit retrofit;
    private Long userPhone;
    private RecyclerView noiseRecyclerView;
    private List<NoiseResponse> noiseResponse;
    private NoiseAdapter noiseAdapter;
    private String activityTag;
    private long followingPhone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noise_report);
        currentTime = Calendar.getInstance().getTime();
        retrofit = ApiClient.getClient();
        noiseRecyclerView = (RecyclerView) findViewById(R.id.rvNoises);
        noiseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        try {
            activityTag = getIntent().getExtras().getString(AppConst.ACTIVITY_TAG);
        } catch (NullPointerException e) {
            Log.e(activityTag, "null");
        }
        checkUserIsLoggedIn();

    }

    private void checkUserIsLoggedIn() {
        UserCachedInfo userCachedInfo = new UserCachedInfo(this);
        if (userCachedInfo.getPhone() != 0 && !userCachedInfo.getName().isEmpty()) {
            userPhone = userCachedInfo.getPhone();
            if (activityTag.equals(AppConst.USER_REPORTS_FRAGMENT_TAG)) {
                makeNoiseRequest(userPhone);
            } else {
                try {
                    followingPhone = getIntent().getExtras().getLong(AppConst.FOLLOWING_PHONE);
                    makeNoiseRequest(followingPhone);
                } catch (NullPointerException e) {
                    Log.e(activityTag, "null");
                }
            }


        } else {
            openLoginActivity();
        }
    }

    private void openLoginActivity() {
        Intent intent = new Intent(NoiseReportsActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    public void makeNoiseRequest(final Long phone) {
        // showDialog(context.getString(R.string.getting_light_txt));
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<List<NoiseResponse>> call = apiService.getNoiseResponse(phone);
        call.enqueue(new Callback<List<NoiseResponse>>() {


            @Override
            public void onResponse(Call<List<NoiseResponse>> call, retrofit2.Response<List<NoiseResponse>> response) {
                // closeDialog();
                if (response.isSuccessful()) {
                    noiseResponse = response.body();
                    if (noiseResponse != null) {
                        noiseAdapter = new NoiseAdapter(noiseResponse, getApplicationContext());
                        noiseRecyclerView.setAdapter(noiseAdapter);

                    }
                } else {
                    Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.no_data_txt), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<NoiseResponse>> call, Throwable t) {
                //  closeDialog();
                Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.network_txt), Toast.LENGTH_LONG).show();
            }
        });
    }


}
