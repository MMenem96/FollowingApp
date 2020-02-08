package com.domain.aly.followingapp.apiinterface;

import com.domain.aly.followingapp.apputil.UrlConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create();
            retrofit = new Retrofit.Builder().baseUrl(UrlConstants.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        }

        return retrofit;
    }

}

