package com.example.patientmodule.rest;


import com.example.patientmodule.model.LoginModel;
import com.example.patientmodule.model.PatientsMainModel;
import com.example.patientmodule.model.RegistrationModel;
import com.example.patientmodule.model.ResponseReturnModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIs {

    @FormUrlEncoded
    @POST("UserRegistration.php")
    Call<ResponseReturnModel> userRegistration(@Field("username") String username,
                                             @Field("password") String password,
                                             @Field("full_name") String full_name,
                                             @Field("email") String email,
                                             @Field("gender") String gender,
                                             @Field("age") int age);
    @FormUrlEncoded
    @POST("UserLogin.php")
    Call<ResponseReturnModel> userLogin(@Field("username") String username,
                               @Field("password") String password);

    @FormUrlEncoded
    @POST("UserForgot.php")
    Call<ResponseReturnModel> userForgot(@Field("email") String email,
                                        @Field("new_password") String newPassword);


    @Headers("Content-Type: application/json")
    @GET("getAllPatients.php")
    Call<PatientsMainModel> getPatients();

}
