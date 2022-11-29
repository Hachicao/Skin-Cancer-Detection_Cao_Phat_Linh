package com.example.projectairetrofit.sendimage;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DemoService {
    @GET("test")
    Call<ResponseBody> getInformation();

    @FormUrlEncoded
    @POST("/getimage")
    Call<Result> getimage(@Field("file") String file, @Field("confidence_score") String score);

}
