package com.diden.demo.utils;

public enum AccountTypeEnum {
    DEFAULT("default"),
    KAKAO("kakao"),
    NAVER("naver"),
    APPLE("apple");

    private final String signType;

    AccountTypeEnum(String signType) {
        this.signType = signType;
    }

    public String getAccountType() {
        return signType;
    }
}
