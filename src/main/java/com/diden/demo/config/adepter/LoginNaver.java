package com.diden.demo.config.adepter;
import static com.diden.demo.utils.AccountTypeEnum.*;
public class LoginNaver implements LoginAdepter{
    @Override
    public boolean supports(String handler) {
        return (NAVER.getAccountType().equals(handler));
    }
    @Override
    public boolean process(String Authorization) {
        return false;
    }
}
