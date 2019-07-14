package com.example.patientmodule.rest;


import com.example.patientmodule.model.LoginModel;
import com.example.patientmodule.model.RegistrationModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIs {

    //String Base_Url = ApiClient.BASE_URL;
//
//    @Headers("Content-Type: application/json")
//    @PATCH("profile/update")
//    Call<Boolean> userSignup(@Header("Authorization") String token, @Body UserSignUpModelUpdate userSignUpModelUpdate);

    @Headers("Content-Type: application/json")
    @POST("register.php")
    Call<RegistrationModel> userRegistration(@Body RegistrationModel loginModel);

    @Headers("Content-Type: application/json")
    @POST("login.php")
    Call<LoginModel> userLogin(@Body LoginModel loginModel);
//
//    @Headers("Content-Type: application/json")
//    @GET("case/{caseId}/docs/{id}/edit")
//        //@GET("case/4/docs/7/edit")
//    Call<PetitionModel> getContract(@Header("Authorization") String token, @Path("caseId") int caseId, @Path("id") int docsId);

}
