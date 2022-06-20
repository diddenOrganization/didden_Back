package com.diden.demo.config.adepter;

import static com.diden.demo.utils.AccountTypeEnum.*;

public class LoginApple implements LoginAdepter{
    @Override
    public boolean supports(String handler) {
        return (APPLE.getAccountType().equals(handler));
    }

    @Override
    public boolean process(String temp) {
        return false;
    }
}
