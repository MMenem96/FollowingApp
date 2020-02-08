package com.domain.aly.followingapp.apiinterface;

import com.domain.aly.followingapp.accelerometerutil.AccelerometerPostRequest;
import com.domain.aly.followingapp.accelerometerutil.AccelerometerResponse;
import com.domain.aly.followingapp.apputil.UrlConstants;
import com.domain.aly.followingapp.followersutil.FollowersResponse;
import com.domain.aly.followingapp.followingutil.FollowingResponse;
import com.domain.aly.followingapp.lightutil.LightPostRequest;
import com.domain.aly.followingapp.lightutil.LightResponse;
import com.domain.aly.followingapp.locationutil.LocationPostRequest;
import com.domain.aly.followingapp.locationutil.LocationResponse;
import com.domain.aly.followingapp.loginutil.Login;
import com.domain.aly.followingapp.noiseutil.NoisePostRequest;
import com.domain.aly.followingapp.noiseutil.NoiseResponse;
import com.domain.aly.followingapp.notificationutil.AcceptFollowingPostRequest;
import com.domain.aly.followingapp.signuputil.Signup;
import com.domain.aly.followingapp.speedutil.SpeedPostRequest;
import com.domain.aly.followingapp.speedutil.SpeedResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    //User Login
    @GET(UrlConstants.USER_BY_PHONE)
    Call<Login> postLoginResponse(@Path("phone") Long phone);

    //User Signup
    @POST(UrlConstants.CREATE_A_NEW_USER)
    Call<ResponseBody> postSignupResponse(@Body Signup newUser);

    //Get Records.

    //Get All Light Records.
    @GET(UrlConstants.LIGHT_RECORDS_BY_PHONE)
    Call<List<LightResponse>> getLightResponse(@Path("phone") Long phone);


    //Get All Noise Records.
    @GET(UrlConstants.Noise_RECORDS_BY_PHONE)
    Call<List<NoiseResponse>> getNoiseResponse(@Path("phone") Long phone);

    //Get All Accelerometer Records.
    @GET(UrlConstants.ACCELEROMETER_RECORDS_BY_PHONE)
    Call<List<AccelerometerResponse>> getAccelerometerResponse(@Path("phone") Long phone);

    //Get All Speed Records.
    @GET(UrlConstants.SPEED_RECORDS_BY_PHONE)
    Call<List<SpeedResponse>> getSpeedResponse(@Path("phone") Long phone);

    //Get All Location Records.
    @GET(UrlConstants.LOCATION_RECORDS_BY_PHONE)
    Call<List<LocationResponse>> getLocationResponse(@Path("phone") Long phone);

    //Get All Followers.
    @GET(UrlConstants.FOLLOWERS_BY_PHONE)
    Call<List<FollowersResponse>> getFollowersResponse(@Path("phone") Long phone);

    //Get All Following.
    @GET(UrlConstants.FOLLOWING_BY_PHONE)
    Call<List<FollowingResponse>> getFollowingResponse(@Path("phone") Long phone);


    //Delete  Follower.
    @DELETE(UrlConstants.DELETE_FOLLOWERS_BY_PHONE)
    Call<ResponseBody> deleteFollowerResponse(@Path("id") Integer id);


    //Post Records

    //Post Light Record
    @POST(UrlConstants.POST_LIGHT_RECORDS_BY_PHONE)
    Call<ResponseBody> lightPostRequest(@Path("phone") Long phone, @Body LightPostRequest lightPostRequest);

    //Post Acceleroemeter Record
    @POST(UrlConstants.POST_ACCELEROMETER_RECORDS_BY_PHONE)
    Call<ResponseBody> accelerometerPostRequest(@Path("phone") Long phone, @Body AccelerometerPostRequest accelerometerPostRequest);

    //Post Noise Record
    @POST(UrlConstants.POST_NOISE_RECORDS_BY_PHONE)
    Call<ResponseBody> noisePostRequest(@Path("phone") Long phone, @Body NoisePostRequest noisePostRequest);

    //Post Speed Record
    @POST(UrlConstants.POST_SPEED_RECORDS_BY_PHONE)
    Call<ResponseBody> speedPostRequest(@Path("phone") Long phone, @Body SpeedPostRequest speedPostRequest);

    //Post Location Record
    @POST(UrlConstants.POST_LOCATION_RECORDS_BY_PHONE)
    Call<ResponseBody> locationPostRequest(@Path("phone") Long phone, @Body LocationPostRequest locationPostRequest);

    //Post Accept following request.
    @POST("following/post/{phone}")
    Call<ResponseBody> acceptFollowingPostRequest(@Path("phone") Long phone,  @Body AcceptFollowingPostRequest acceptFollowingPostRequest);
}
