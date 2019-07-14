package com.example.patientmodule.rest;

import com.example.patientmodule.model.LoginModel;
import com.example.patientmodule.model.RegistrationModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelManager {

    public static void login(LoginModel loginModel, final ModelManagerListener listener) {
        APIs api = ApiClient.getClient().create(APIs.class);
        Call<LoginModel> call = api.userLogin(loginModel);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                listener.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                listener.onFailure(call, t);
            }
        });
    }

    public static void register(RegistrationModel registrationModel, final ModelManagerListener listener) {
        APIs api = ApiClient.getClient().create(APIs.class);
        Call<RegistrationModel> call = api.userRegistration(registrationModel);
        call.enqueue(new Callback<RegistrationModel>() {
            @Override
            public void onResponse(Call<RegistrationModel> call, Response<RegistrationModel> response) {
                listener.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<RegistrationModel> call, Throwable t) {
                listener.onFailure(call, t);
            }
        });
    }

}
