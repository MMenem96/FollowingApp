package com.domain.aly.followingapp.splashutil;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.domain.aly.followingapp.R;
import com.domain.aly.followingapp.apputil.AppConst;
import com.domain.aly.followingapp.apputil.UserCachedInfo;
import com.domain.aly.followingapp.homeutil.HomeActivity;
import com.domain.aly.followingapp.loginutil.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = getApplicationContext();
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // This method will be executed once the timer is over

                UserCachedInfo userCachedInfo = new UserCachedInfo(context);
                if (userCachedInfo.getName().isEmpty() || userCachedInfo.getPhone() == 0) {
                    openLoginActivity();
                } else {
                    openHomeActivity();
                }
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void openHomeActivity() {
        Intent i = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(i);
    }

    private void openLoginActivity() {
        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(i);
    }


}

