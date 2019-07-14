package com.example.patientmodule.rest;


import com.example.patientmodule.model.LoginModel;
import com.example.patientmodule.model.RegistrationModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIs {

    @FormUrlEncoded
    @POST("UserRegistration.php")
    Call<RegistrationModel> userRegistration(@Field("username") String username,
                                             @Field("password") String password,
                                             @Field("full_name") String full_name,
                                             @Field("email") String email,
                                             @Field("gender") String gender,
                                             @Field("age") int age);
    @FormUrlEncoded
    @POST("UserLogin.php")
    Call<LoginModel> userLogin(@Field("username") String username,
                               @Field("password") String password);

}
