package com.example.abouttravel.api;

import com.example.abouttravel.helpers.CreateUser;
import com.example.abouttravel.data.entities.Local;
import com.example.abouttravel.data.entities.Trip;
import com.example.abouttravel.helpers.UserLogin;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface EndPoints {

    @GET("home")
    Call<ResponseBody> home();

    //auth
    @POST("auth/login")
    Call<ResponseBody> authenticateUser(@Body UserLogin user);

    @POST("auth/refresh")
    Call<ResponseBody> refreshToken(@Body Map<String, String> refreshToken);

    @POST("auth/logout")
    Call<ResponseBody> logout();

    @GET("auth/me")
    Call<ResponseBody> me();

    @PATCH("auth/update")
    Call<ResponseBody> updateUser(@Body CreateUser createUser);

    @POST("auth/register")
    Call<ResponseBody> registerUser(@Body CreateUser createUser);

    @DELETE("auth/delete")
    Call<ResponseBody> deleteUser();

    //user
    @GET("users")
    Call<ResponseBody> users();

    @GET("users/{id}")
    Call<ResponseBody> user();

    //trips

    @GET("trips")
    Call<ResponseBody> trips();

    @GET("trips/{id}")
    Call<ResponseBody> trip();

    @POST("trips")
    Call<ResponseBody> createTrip(@Body Trip trip);

    @PATCH("trips/{id}")
    Call<ResponseBody> updateTrip(@Body Trip trip);

    @DELETE("trips/{id}")
    Call<ResponseBody> deleteTrip();

    //locals

    @GET("trips/{id}/locals")
    Call<ResponseBody> locals();

    @GET("trips/{id}/locals/{id}")
    Call<ResponseBody> local();

    @POST("trips/{id}/locals")
    Call<ResponseBody> createLocal(@Body Local local);

    @PATCH("trips/{id}/locals/{id}")
    Call<ResponseBody> updateLocal(@Body Local local);

    @DELETE("trips/{id}/locals/{id}")
    Call<ResponseBody> deleteLocal();


}
