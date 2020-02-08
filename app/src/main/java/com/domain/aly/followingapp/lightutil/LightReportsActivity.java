package com.domain.aly.followingapp.lightutil;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
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
import com.domain.aly.followingapp.apiinterface.ApiManager;
import com.domain.aly.followingapp.apputil.AppConst;
import com.domain.aly.followingapp.apputil.UserCachedInfo;
import com.domain.aly.followingapp.loginutil.LoginActivity;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class LightReportsActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private RecyclerView lightRecyclerView;
    private LightAdapter lightAdapter;
    private LightSensor mLightSensor;
    Button postButton;
    private List<LightResponse> lightResponse;
    private Double value;
    private Long userPhone;
    private Date currentTime;
    private CharSequence dateFormat;
    private String activityTag;
    private long followingPhone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_report);
        currentTime = Calendar.getInstance().getTime();
        retrofit = ApiClient.getClient();
        lightRecyclerView = (RecyclerView) findViewById(R.id.rvLights);
        lightRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        try {
            activityTag = getIntent().getExtras().getString(AppConst.ACTIVITY_TAG);
        } catch (NullPointerException e) {
            Log.e(activityTag, "null");
        }
        checkUserIsLoggedIn();


//        postButton = (Button) findViewById(R.id.post);
//        postButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dateFormat = android.text.format.DateFormat.format("yyyy-MM-dd HH:mm:ss", new java.util.Date());
//                ApiManager apiManager = new ApiManager(getApplicationContext());
//                apiManager.postLightRecord(userPhone, new LightPostRequest(value, currentTime));
//            }
//        });
    }

    private void checkUserIsLoggedIn() {
        UserCachedInfo userCachedInfo = new UserCachedInfo(this);
        if (userCachedInfo.getPhone() != 0 && !userCachedInfo.getName().isEmpty()) {
            userPhone = userCachedInfo.getPhone();
//            makeLightRequest(userPhone);
            if (activityTag.equals(AppConst.USER_REPORTS_FRAGMENT_TAG)) {
                makeLightRequest(userPhone);
            } else {
                try {
                    followingPhone = getIntent().getExtras().getLong(AppConst.FOLLOWING_PHONE);
                    makeLightRequest(followingPhone);
                } catch (NullPointerException e) {
                    Log.e(activityTag, "null");
                }
            }
        } else {
            openLoginActivity();
        }
    }

    private void openLoginActivity() {
        Intent intent = new Intent(LightReportsActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    public void makeLightRequest(final Long phone) {
        // showDialog(context.getString(R.string.getting_light_txt));
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<List<LightResponse>> call = apiService.getLightResponse(phone);
        call.enqueue(new Callback<List<LightResponse>>() {
            @Override
            public void onResponse(Call<List<LightResponse>> call, retrofit2.Response<List<LightResponse>> response) {
                // closeDialog();
                if (response.isSuccessful()) {
                    lightResponse = response.body();
                    if (lightResponse != null) {
                        lightAdapter = new LightAdapter(lightResponse, getApplicationContext());
                        lightRecyclerView.setAdapter(lightAdapter);

                    }
                } else {
                    Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.no_data_txt), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<LightResponse>> call, Throwable t) {
                //  closeDialog();
                Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.network_txt), Toast.LENGTH_LONG).show();
            }
        });
    }


}
