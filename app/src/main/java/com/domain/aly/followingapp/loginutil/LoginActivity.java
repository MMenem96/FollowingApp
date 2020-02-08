package com.domain.aly.followingapp.loginutil;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.domain.aly.followingapp.R;
import com.domain.aly.followingapp.apiinterface.ApiClient;
import com.domain.aly.followingapp.apiinterface.ApiManager;
import com.domain.aly.followingapp.signuputil.SignupActivity;

import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private EditText etPhone, etPassword;
    private Button btnLogin;
    private Long userPhone;
    private Retrofit retrofit;
    private TextView tvSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        retrofit = ApiClient.getClient();

        etPhone = (EditText) findViewById(R.id.etPhone);

        //  etPassword = (EditText) findViewById(R.id.etPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();

            }
        });
        tvSignup = (TextView) findViewById(R.id.tvSignup);
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignupActivity();

            }
        });
    }


    private void openSignupActivity() {
        Intent i = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(i);
    }

    private void loginUser() {
        if (!etPhone.getText().toString().isEmpty()) {
            userPhone = Long.valueOf(etPhone.getText().toString().trim());
                ApiManager apiManager = new ApiManager(this);
                apiManager.makeLoginRequest(userPhone);
        } else {
            Toast.makeText(this, "Check Inputs!", Toast.LENGTH_SHORT).show();

        }


    }
}
