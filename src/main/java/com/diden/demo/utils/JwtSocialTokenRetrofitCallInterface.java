package com.diden.demo.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

import javax.validation.constraints.NotNull;
import java.util.concurrent.ConcurrentMap;

public interface JwtSocialTokenRetrofitCallInterface {

    @GET("/v2/user/me")
    Call<JsonObject> getJwtKakaoAccessToken(@NotNull @Header("Authorization") String auth);
}
