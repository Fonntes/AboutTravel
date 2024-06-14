package com.example.abouttravel.network;

import com.example.abouttravel.data.entities.Session;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface EndPoints {
    @POST("auth/login")
    Call<ResponseBody> authenticateUser(@Body User user);
    @GET("home")
    Call<ResponseBody> home();

    @GET("auth/me")
    Call<ResponseBody> me();

    @POST("auth/refresh")
    Call<ResponseBody> refreshToken(@Body Map<String, String> refreshToken);

    @PATCH("auth/update")
    Call<ResponseBody> updateUser(@Body Session session);
}
