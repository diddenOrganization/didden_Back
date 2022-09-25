package com.diden.demo.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCallUtils {
    public static JwtSocialTokenRetrofitCallInterface getApiService(final String baseUrl) {
        return getInstance(baseUrl).create(JwtSocialTokenRetrofitCallInterface.class);
    }

    private static Retrofit getInstance(final String baseUri) {
        Gson gson = new GsonBuilder().setLenient().create();

        return new Retrofit.Builder()
                .baseUrl(baseUri)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
