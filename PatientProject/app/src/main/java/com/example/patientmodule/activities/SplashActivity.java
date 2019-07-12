package com.example.patientmodule.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.patientmodule.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thd = new Thread() {
            public void run() {
                try {
                    sleep(1000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        thd.start();
    }
}
