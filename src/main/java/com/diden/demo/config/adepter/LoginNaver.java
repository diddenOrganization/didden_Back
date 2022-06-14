package com.diden.demo.config.adepter;

public class LoginNaver implements LoginAdepter{
    @Override
    public boolean supports(String handler) {
        return ("naver".equals(handler));
    }
    @Override
    public boolean process(String Authorization) {
        return false;
    }
}
