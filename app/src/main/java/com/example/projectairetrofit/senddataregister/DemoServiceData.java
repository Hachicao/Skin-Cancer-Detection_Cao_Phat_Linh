package com.example.projectairetrofit.senddataregister;

import com.example.projectairetrofit.sendimage.Result;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DemoServiceData {
    @GET("test")
    Call<ResponseBody> getInformation();

    @FormUrlEncoded
    @POST("/register")
    Call<Result> getData(@Field("name") String name, @Field("email") String email, @Field("pass") String pass, @Field("phone") int phone);

}
