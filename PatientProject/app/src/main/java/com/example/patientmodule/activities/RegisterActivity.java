package com.example.patientmodule.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patientmodule.R;
import com.example.patientmodule.model.RegistrationModel;
import com.example.patientmodule.rest.ModelManager;
import com.example.patientmodule.rest.ModelManagerListener;
import com.example.patientmodule.utils.Utils;

import retrofit2.Call;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private TextView fullName;
    private TextView email;
    private TextView username;
    private TextView password;
    private TextView age;
    private RadioGroup radioGroup;
    private String gender = "Male";
    RegistrationModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initUi();
    }

    private void initUi() {
        fullName = (TextView) findViewById(R.id.fullName);
        email = (TextView) findViewById(R.id.email);
        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);
        age = (TextView) findViewById(R.id.age);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radioMale:
                gender = "Male";
                break;
            case R.id.radioFemale:
                gender = "Female";
                break;
            default:
                break;
        }
    }

    public void onClickRegisterBtn(View view) {

//        if (validate()) {
        callApi();
//        }
    }

    private boolean validate() {
        if (!TextUtils.isEmpty(fullName.getText().toString()) &&
                !TextUtils.isEmpty(email.getText().toString()) &&
                !TextUtils.isEmpty(username.getText().toString()) &&
                !TextUtils.isEmpty(password.getText().toString()) &&
                !TextUtils.isEmpty(age.getText().toString())) {
            return true;
        } else {
            Toast.makeText(this, "Please Enter all Fields", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void callApi() {
        populateModel();

        ModelManager.register(model, new ModelManagerListener() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.body() != null) {
                    RegistrationModel model = (RegistrationModel) response.body();
                    if (!model.getError()) {
                        Toast.makeText(RegisterActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
                    } else if (model.getError()) {
                        Toast.makeText(RegisterActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    String message = Utils.getResponseErrorMessage(RegisterActivity.this, response);
                    Toast.makeText(RegisterActivity.this, ":" + message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void populateModel() {
        model = new RegistrationModel();
        model.setFullName(fullName.getText().toString());
        model.setEmail(email.getText().toString());
        model.setUsername(username.getText().toString());
        model.setPassword(password.getText().toString());
        model.setAge(332);
        model.setGender(gender);
    }
}
