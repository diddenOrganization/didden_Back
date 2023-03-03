package com.diden.demo.domain.user.enums;

import com.diden.demo.common.error.exception.BadRequestException;

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

  public static AccountTypeEnum getAccountEnumType(final String accountType) {
    for(AccountTypeEnum type : AccountTypeEnum.values()) {
      if(type.getAccountType().equals(accountType)) {
        return type;
      }
    }
    throw new BadRequestException("서버에 정의된 로그인 타입이 존재하지 않습니다.");
  }
}
