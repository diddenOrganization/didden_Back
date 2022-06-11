package com.diden.demo.utils;

import com.google.gson.JsonObject;

public interface JwtSocialTokenCheckInterface {
    default JsonObject socialAccessToken(String auth){
        return null;
    }

    default JsonObject socialRefreshToken(){
        return null;
    }
}
