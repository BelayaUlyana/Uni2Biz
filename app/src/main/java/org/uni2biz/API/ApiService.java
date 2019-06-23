package org.uni2biz.API;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @Headers({
            "Content-Type: application/json",
            "Api-version: v1"})

    @POST("auth")
    Call<AuthorizationResponse> auth(@Body AuthorizationRequest request);


    @Headers({
            "Content-Type: application/json",
            "Api-version: v1"
    })

    @POST("profile")
    Call<RegistrationResponse> register(@Body RegistrationRequest request);

}
