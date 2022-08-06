package com.diden.demo.user.service.impl;

import com.diden.demo.user.vo.UserVo;
import com.google.gson.JsonObject;
import static com.diden.demo.utils.AccountTypeEnum.*;
public class NaverSocialAdepterImpl implements SocialAdepter {
    @Override
    public boolean supports(String handler) {
        return (NAVER.getAccountType().equals(handler));
    }

    @Override
    public UserVo process(JsonObject param) {
        return new UserVo();
    }
}
