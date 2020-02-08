package com.domain.aly.followingapp.signuputil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.domain.aly.followingapp.R;
import com.domain.aly.followingapp.apiinterface.ApiClient;
import com.domain.aly.followingapp.apiinterface.ApiManager;

import retrofit2.Retrofit;

public class SignupActivity extends AppCompatActivity {

    private EditText etName, etPhone, etPassword;
    private Button btnSignup;
    private String userName;
    private Long userPhone;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        retrofit = ApiClient.getClient();

        etName = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etPhone);
        //etPassword=(EditText)findViewById(R.id.etPassword);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupUser();

            }
        });


    }

    private void signupUser() {
        userName = etName.getText().toString().trim();
        userPhone = Long.valueOf(etPhone.getText().toString().trim());
        if (!userName.isEmpty() && userPhone > 11) {

            ApiManager apiManager = new ApiManager(this);
            apiManager.makeSignupRequest(new Signup(userName, userPhone));
        } else {
            Toast.makeText(this, "Check Inputs!", Toast.LENGTH_SHORT).show();
        }

    }
}
