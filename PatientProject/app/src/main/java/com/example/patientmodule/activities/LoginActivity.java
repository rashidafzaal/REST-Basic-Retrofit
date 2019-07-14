package com.example.patientmodule.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.patientmodule.R;
import com.example.patientmodule.model.LoginModel;
import com.example.patientmodule.model.ResponseReturnModel;
import com.example.patientmodule.rest.ModelManager;
import com.example.patientmodule.rest.ModelManagerListener;
import com.example.patientmodule.utils.Utils;

import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText loginUsername;
    private EditText loginPassword;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = (EditText) findViewById(R.id.loginUsername);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
    }

    public void goToRegisterActivity(View view) {

        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void goToForgotActivity(View view) {
        startActivity(new Intent(this, ForgotActivity.class));
    }

    public void onClickLoginBtn(View view) {

        username = loginUsername.getText().toString();
        password = loginPassword.getText().toString();

        if (validate()) {
            callApi();
        }
    }

    private boolean validate() {

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            return true;
        } else {
            Toast.makeText(this, "Please Enter all Fields", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void callApi() {

        LoginModel model = new LoginModel();
        model.setUsername(username);
        model.setPassword(password);

        ModelManager.login(model, new ModelManagerListener() {
            @Override
            public void onResponse(Call call, Response response) {

                if (response.body() != null) {
                    ResponseReturnModel model = (ResponseReturnModel) response.body();
                    if (!model.getError()) {
                        Toast.makeText(LoginActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, ViewAllPatients.class));
                        finish();
                    } else if (model.getError()) {
                        Toast.makeText(LoginActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    String message = Utils.getResponseErrorMessage(LoginActivity.this, response);
                    Toast.makeText(LoginActivity.this, ":" + message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
