package com.example.patientmodule.rest;

import retrofit2.Call;
import retrofit2.Response;

public interface ModelManagerListener<T> {

    public void onResponse(Call<T> call, Response<T> response);
    public void onFailure(Call<T> call, Throwable t);
}
