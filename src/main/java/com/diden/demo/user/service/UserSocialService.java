package com.diden.demo.user.service;

import com.google.gson.JsonObject;

public interface UserSocialService {
    boolean process(JsonObject param);
}
