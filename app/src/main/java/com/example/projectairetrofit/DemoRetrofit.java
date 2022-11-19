package com.example.projectairetrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DemoRetrofit {
    public static Retrofit retrofit;

    public static Retrofit getInstance(String url) {
        // System.out.println(url +" Url--------------");
        if (url == null) {
            System.out.println("Url is null--------");
//            url = "https://fa17-103-17-88-39.ap.ngrok.io";
        }
         else if (retrofit == null) {
            retrofit = new Retrofit.Builder()
//                    .baseUrl("http:/10.0.2.2:5000/")
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
