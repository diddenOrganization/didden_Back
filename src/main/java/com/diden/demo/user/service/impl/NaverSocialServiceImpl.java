package com.diden.demo.user.service.impl;

import com.diden.demo.user.vo.UserVo;
import com.google.gson.JsonObject;

public class NaverSocialServiceImpl implements SocialAdepter {
    @Override
    public boolean supports(String handler) {
        return ("naver".equals(handler));
    }

    @Override
    public UserVo process(JsonObject param) {
        return new UserVo();
    }
}
