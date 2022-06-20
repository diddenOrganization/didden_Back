package com.diden.demo.user.service.impl;

import com.diden.demo.user.vo.UserVo;
import com.google.gson.JsonObject;
import static com.diden.demo.utils.AccountTypeEnum.*;
public class AppleSocialAdepterImpl implements SocialAdepter {
    @Override
    public boolean supports(String handler) {
        return (APPLE.getAccountType().equals(handler));
    }

    @Override
    public UserVo process(JsonObject param) {
        return new UserVo();
    }
}
