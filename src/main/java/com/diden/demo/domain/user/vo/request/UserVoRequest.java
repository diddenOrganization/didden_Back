package com.diden.demo.domain.user.vo.request;

import com.diden.demo.domain.user.enums.GenderEnum;
import com.diden.demo.domain.user.enums.PrivacyConsentEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserVoRequest {
  private String userPassword;
  private String userNickname;
  private String userBirthday;
  private GenderEnum userGenderEnum;
  private String userEmail;
  private String userPhoneNumber;
  private String userCreateDate;
  private String userUpdateDate;
  private PrivacyConsentEnum userPrivacyConsentEnum;
  private String userLoginType;
  private String userAccessToken;
  private String userRefreshToken;
  private String nextData;

  public UserVoRequest() {
  }

  @Builder
  public UserVoRequest(
      String userPassword,
      String userNickname,
      String userBirthday,
      GenderEnum userGenderEnum,
      String userEmail,
      String userPhoneNumber,
      String userCreateDate,
      String userUpdateDate,
      PrivacyConsentEnum userPrivacyConsentEnum,
      String userLoginType,
      String userAccessToken,
      String userRefreshToken,
      String nextData) {
    this.userPassword = userPassword;
    this.userNickname = userNickname;
    this.userBirthday = userBirthday;
    this.userGenderEnum = userGenderEnum;
    this.userEmail = userEmail;
    this.userPhoneNumber = userPhoneNumber;
    this.userCreateDate = userCreateDate;
    this.userUpdateDate = userUpdateDate;
    this.userPrivacyConsentEnum = userPrivacyConsentEnum;
    this.userLoginType = userLoginType;
    this.userAccessToken = userAccessToken;
    this.userRefreshToken = userRefreshToken;
    this.nextData = nextData;
  }

}
