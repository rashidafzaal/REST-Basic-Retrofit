package com.example.patientmodule.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.patientmodule.R;
import com.example.patientmodule.model.ResponseReturnModel;
import com.example.patientmodule.rest.ModelManager;
import com.example.patientmodule.rest.ModelManagerListener;
import com.example.patientmodule.utils.Utils;

import retrofit2.Call;
import retrofit2.Response;

public class ForgotActivity extends AppCompatActivity {

    private EditText email;
    private EditText newPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        initUi();
    }

    private void initUi() {
        email = (EditText) findViewById(R.id.email);
        newPassword = (EditText) findViewById(R.id.newPassword);
    }

    public void onClickSubmitButton(View view) {

        if (validate()) {
            callApi();
        }
    }

    private void callApi() {

        ModelManager.forgot(email.getText().toString(), newPassword.getText().toString(), new ModelManagerListener() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.body() != null) {
                    ResponseReturnModel model = (ResponseReturnModel) response.body();
                    if (!model.getError()) {
                        Toast.makeText(ForgotActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ForgotActivity.this, LoginActivity.class));
                    } else if (model.getError()) {
                        Toast.makeText(ForgotActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    String message = Utils.getResponseErrorMessage(ForgotActivity.this, response);
                    Toast.makeText(ForgotActivity.this, ":" + message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    private boolean validate() {
        if (!TextUtils.isEmpty(email.getText().toString()) &&
                !TextUtils.isEmpty(newPassword.getText().toString())) {
            return true;
        } else {
            Toast.makeText(this, "Please Enter all Fields", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
