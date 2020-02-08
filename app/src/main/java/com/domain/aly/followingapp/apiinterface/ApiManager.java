package com.domain.aly.followingapp.apiinterface;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.domain.aly.followingapp.R;
import com.domain.aly.followingapp.accelerometerutil.AccelerometerPostRequest;
import com.domain.aly.followingapp.apputil.AppConst;
import com.domain.aly.followingapp.apputil.UserCachedInfo;
import com.domain.aly.followingapp.locationutil.LocationPostRequest;
import com.domain.aly.followingapp.noiseutil.NoisePostRequest;
import com.domain.aly.followingapp.notificationutil.AcceptFollowingPostRequest;
import com.domain.aly.followingapp.notificationutil.FollowingRequest;
import com.domain.aly.followingapp.homeutil.HomeActivity;
import com.domain.aly.followingapp.lightutil.LightAdapter;
import com.domain.aly.followingapp.lightutil.LightPostRequest;
import com.domain.aly.followingapp.lightutil.LightResponse;
import com.domain.aly.followingapp.loginutil.Login;
import com.domain.aly.followingapp.loginutil.LoginActivity;
import com.domain.aly.followingapp.signuputil.Signup;
import com.domain.aly.followingapp.signuputil.SignupActivity;
import com.domain.aly.followingapp.speedutil.SpeedPostRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiManager {
    private Context context;
    private ProgressDialog progressBar;
    private List<LightResponse> lightResponses;
    private RecyclerView lightRecyclerViews;
    private LightAdapter lightAdapters;

    public ApiManager(Context context) {
        this.context = context;
        progressBar = new ProgressDialog(context);
    }

    //Login with exist user.
    public void makeLoginRequest(final Long phone) {
        showDialog(context.getString(R.string.login_txt));

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<Login> call = apiService.postLoginResponse(phone);
        call.enqueue(new Callback<Login>() {


            @Override
            public void onResponse(Call<Login> call, retrofit2.Response<Login> response) {
                closeDialog();
                if (response.isSuccessful() && response.body() != null) {
                    UserCachedInfo userCachedInfo = new UserCachedInfo(context);
                    userCachedInfo.savePhone(AppConst.USER_PHONE, phone);
                    userCachedInfo.saveName(AppConst.USER_NAME, response.body().getName());
                    openHomeActivity();
                    ((LoginActivity) context).finish();
                } else if (response.code() == 409) {
                    Toast.makeText(context, context.getString(R.string.user_doesnt_txt), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failed to login-response-code: " + response.code(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                closeDialog();
                Log.d("networkerror", t.getMessage().toString());
                Toast.makeText(context, context.getString(R.string.network_txt), Toast.LENGTH_LONG).show();
                return;
            }
        });
    }

    // Create a new user.
    public void makeSignupRequest(final Signup newUser) {
        showDialog(context.getString(R.string.signup_txt));
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiService.postSignupResponse(newUser);
        call.enqueue(new Callback<ResponseBody>() {


            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                closeDialog();
                if (response.isSuccessful()) {
                    UserCachedInfo userCachedInfo = new UserCachedInfo(context);
                    userCachedInfo.savePhone(AppConst.USER_PHONE, newUser.getPhone());
                    userCachedInfo.saveName(AppConst.USER_NAME, newUser.getName());
                    openHomeActivity();
                    ((SignupActivity) context).finish();
                } else if (response.code() == 409) {
                    Toast.makeText(context, context.getString(R.string.user_already_exist_txt), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failed to signup-response-code: " + response.code(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                closeDialog();
                Toast.makeText(context, context.getString(R.string.network_txt), Toast.LENGTH_LONG).show();
                return;
            }
        });
    }

    //delete follower record
    public void deleteFollower(int id) {
        showDialog(context.getString(R.string.delete_txt));
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.deleteFollowerResponse(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    closeDialog();
                    Toast.makeText(context, context.getString(R.string.deletedSuccefully_txt), Toast.LENGTH_SHORT).show();
                } else {
                    closeDialog();
                    Toast.makeText(context, context.getString(R.string.deletefailed_txt) + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                closeDialog();
                Toast.makeText(context, context.getString(R.string.network_txt), Toast.LENGTH_SHORT).show();

            }
        });
    }

    //delete following record.
    public void deleteFollowing(int id) {
        showDialog(context.getString(R.string.delete_txt));
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.deleteFollowerResponse(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    closeDialog();
                    Toast.makeText(context, context.getString(R.string.deletedSuccefully_txt), Toast.LENGTH_SHORT).show();

                } else {
                    closeDialog();
                    Toast.makeText(context, context.getString(R.string.deletefailed_txt) + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                closeDialog();
                Toast.makeText(context, context.getString(R.string.network_txt), Toast.LENGTH_SHORT).show();

            }
        });
    }

    //Accept Following Request.


    //Post light record
    public void postLightRecord(Long phone, LightPostRequest lightPostRequest) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.lightPostRequest(phone, lightPostRequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "light record is posted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "light record failed to posted: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    //Post Location record
    public void postLocationRecord(Long phone, LocationPostRequest locationPostRequest) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.locationPostRequest(phone, locationPostRequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Location record is posted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Location record failed to posted: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    //Post Speed record
    public void postSpeedRecord(Long phone, SpeedPostRequest speedPostRequest) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.speedPostRequest(phone, speedPostRequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Speed record is posted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Speed record failed to posted: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    //Post Noise record
    public void postNoiseRecord(Long phone, NoisePostRequest noisePostRequest) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.noisePostRequest(phone, noisePostRequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Noise record is posted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Noise record failed to posted: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    //Post Accelerometer record
    public void postAccelerometerRecord(Long phone, AccelerometerPostRequest accelerometerPostRequest) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.accelerometerPostRequest(phone, accelerometerPostRequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Accelerometer record is posted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Accelerometer record failed to posted: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void openHomeActivity() {
        Intent homeActivity = new Intent(context.getApplicationContext(), HomeActivity.class);
        context.startActivity(homeActivity);

    }

    public void showDialog(String message) {
        progressBar.setMessage(message);
        progressBar.setCancelable(false);
        progressBar.show();
    }

    public void closeDialog() {
        if (progressBar != null && progressBar.isShowing()) {
            progressBar.dismiss();
        }
    }


    public void IsUserExistRequest(final Long phone, final String userName, final Long userPhone) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<Login> call = apiService.postLoginResponse(phone);
        call.enqueue(new Callback<Login>() {


            @Override
            public void onResponse(Call<Login> call, retrofit2.Response<Login> response) {
                if (response.isSuccessful() && response.body() != null) {

                    sendRequestToFirebase(String.valueOf(phone), userName, String.valueOf(userPhone));

                } else if (response.code() == 409) {
                    Toast.makeText(context, "Not a user!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failed to add follower: " + response.code(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(context, context.getString(R.string.network_txt), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void sendRequestToFirebase(String followerPhone, String userName, String userPhone) {
        DatabaseReference followingDatabase = FirebaseDatabase.getInstance().getReference("followingRequest").child(followerPhone);


        followingDatabase.setValue(new FollowingRequest(userName, userPhone)).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Toast.makeText(context, "Posted Successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Toast.makeText(HomeActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
