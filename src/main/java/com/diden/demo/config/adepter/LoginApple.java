package com.diden.demo.config.adepter;

public class LoginApple implements LoginAdepter{
    @Override
    public boolean supports(String handler) {
        return ("apple".equals(handler));
    }

    @Override
    public boolean process(String temp) {
        return false;
    }
}
