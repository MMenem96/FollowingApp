package com.domain.aly.followingapp.locationutil;

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

public class LocationReportsActivity extends AppCompatActivity {

    private Date currentTime;
    private Retrofit retrofit;
    private Long userPhone, followingPhone;
    private RecyclerView locationRecyclerView;
    private List<LocationResponse> locationResponse;
    private LocationAdapter locationAdapter;
    private String activityTag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_report);
        currentTime = Calendar.getInstance().getTime();
        retrofit = ApiClient.getClient();
        locationRecyclerView = (RecyclerView) findViewById(R.id.rvLocations);
        locationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
                makeLocationRequest(userPhone);
            } else {
                try {
                    followingPhone = getIntent().getExtras().getLong(AppConst.FOLLOWING_PHONE);
                    makeLocationRequest(followingPhone);
                } catch (NullPointerException e) {
                    Log.e(activityTag, "null");
                }
            }

        } else {
            openLoginActivity();
        }
    }

    private void openLoginActivity() {
        Intent intent = new Intent(LocationReportsActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    public void makeLocationRequest(final Long phone) {
        // showDialog(context.getString(R.string.getting_light_txt));
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<List<LocationResponse>> call = apiService.getLocationResponse(phone);
        call.enqueue(new Callback<List<LocationResponse>>() {


            @Override
            public void onResponse(Call<List<LocationResponse>> call, Response<List<LocationResponse>> response) {
                // closeDialog();
                if (response.isSuccessful()) {
                    locationResponse = response.body();
                    if (locationResponse != null) {
                        locationAdapter = new LocationAdapter(locationResponse, getApplicationContext());
                        locationRecyclerView.setAdapter(locationAdapter);

                    }
                } else {
                    Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.no_data_txt), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<LocationResponse>> call, Throwable t) {
                //  closeDialog();
                Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.network_txt), Toast.LENGTH_LONG).show();
            }
        });
    }

}
