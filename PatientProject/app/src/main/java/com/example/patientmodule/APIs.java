package com.example.patientmodule;


import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface APIs {

    //String Base_Url = ApiClient.BASE_URL;
//
//    @Headers("Content-Type: application/json")
//    @PATCH("profile/update")
//    Call<Boolean> userSignup(@Header("Authorization") String token, @Body UserSignUpModelUpdate userSignUpModelUpdate);

    @Headers("Content-Type: application/json")
    @POST("auth/signin")
    Call<LoginModel> userLogin(@Body LoginModel loginModel);
//
//    @Headers("Content-Type: application/json")
//    @GET("case/{caseId}/docs/{id}/edit")
//        //@GET("case/4/docs/7/edit")
//    Call<PetitionModel> getContract(@Header("Authorization") String token, @Path("caseId") int caseId, @Path("id") int docsId);

}
