package com.example.patientmodule.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.patientmodule.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void goToRegisterActivity(View view) {

        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void goToForgotActivity(View view) {
        startActivity(new Intent(this, ForgotActivity.class));
    }
}
