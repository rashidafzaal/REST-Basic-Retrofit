package com.example.patientmodule.rest;

import com.example.patientmodule.model.LoginModel;
import com.example.patientmodule.model.PatientsMainModel;
import com.example.patientmodule.model.RegistrationModel;
import com.example.patientmodule.model.ResponseReturnModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelManager {

    public static void login(LoginModel loginModel, final ModelManagerListener listener) {
        APIs api = ApiClient.getClient().create(APIs.class);
        Call<ResponseReturnModel> call = api.userLogin(
                loginModel.getUsername(),
                loginModel.getPassword());
        call.enqueue(new Callback<ResponseReturnModel>() {
            @Override
            public void onResponse(Call<ResponseReturnModel> call, Response<ResponseReturnModel> response) {
                listener.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<ResponseReturnModel> call, Throwable t) {
                listener.onFailure(call, t);
            }
        });
    }

    public static void register(RegistrationModel registrationModel, final ModelManagerListener listener) {
        APIs api = ApiClient.getClient().create(APIs.class);
        Call<ResponseReturnModel> call = api.userRegistration(
                registrationModel.getUsername(),
                registrationModel.getPassword(),
                registrationModel.getFullName(),
                registrationModel.getEmail(),
                registrationModel.getGender(),
                registrationModel.getAge());
        call.enqueue(new Callback<ResponseReturnModel>() {
            @Override
            public void onResponse(Call<ResponseReturnModel> call, Response<ResponseReturnModel> response) {
                listener.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<ResponseReturnModel> call, Throwable t) {
                listener.onFailure(call, t);
            }
        });
    }

    public static void forgot(String email, String newPassword, final ModelManagerListener listener) {
        APIs api = ApiClient.getClient().create(APIs.class);
        Call<ResponseReturnModel> call = api.userForgot(email, newPassword);
        call.enqueue(new Callback<ResponseReturnModel>() {
            @Override
            public void onResponse(Call<ResponseReturnModel> call, Response<ResponseReturnModel> response) {
                listener.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<ResponseReturnModel> call, Throwable t) {
                listener.onFailure(call, t);
            }
        });
    }

    public static void getAllPatients(final ModelManagerListener listener) {
        APIs api = ApiClient.getClient().create(APIs.class);
        Call<PatientsMainModel> call = api.getPatients();
        call.enqueue(new Callback<PatientsMainModel>() {
            @Override
            public void onResponse(Call<PatientsMainModel> call, Response<PatientsMainModel> response) {
                listener.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<PatientsMainModel> call, Throwable t) {
                listener.onFailure(call, t);
            }
        });
    }



}
