package com.diden.demo.user.service.impl;

import com.diden.demo.user.vo.UserVo;
import com.google.gson.JsonObject;

public interface SocialAdepter {
    boolean supports(String handler);
    UserVo process(JsonObject param);
}
