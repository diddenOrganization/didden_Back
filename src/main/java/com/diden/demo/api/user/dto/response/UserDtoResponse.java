package com.diden.demo.api.user.dto.response;

import com.diden.demo.domain.user.enums.GenderEnum;
import com.diden.demo.domain.user.enums.PrivacyConsentEnum;
import com.diden.demo.domain.user.vo.response.UserVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserDtoResponse {
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

  public UserDtoResponse() {
  }

  @Builder
  public UserDtoResponse(String userPassword, String userNickname, String userBirthday, GenderEnum userGenderEnum, String userEmail, String userPhoneNumber, String userCreateDate, String userUpdateDate, PrivacyConsentEnum userPrivacyConsentEnum, String userLoginType, String userAccessToken, String userRefreshToken, String nextData) {
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

  public static UserDtoResponse transportDataByUserVo(UserVo userVo) {
    return UserDtoResponse.builder()
            .userPassword(userVo.getUserPassword())
            .userNickname(userVo.getUserNickname())
            .userBirthday(userVo.getUserBirthday())
            .userGenderEnum(userVo.getUserGenderEnum())
            .userEmail(userVo.getUserEmail())
            .userPhoneNumber(userVo.getUserPhoneNumber())
            .userCreateDate(userVo.getUserCreateDate())
            .userUpdateDate(userVo.getUserUpdateDate())
            .userPrivacyConsentEnum(userVo.getUserPrivacyConsentEnum())
            .userLoginType(userVo.getUserLoginType())
            .userAccessToken(userVo.getUserAccessToken())
            .userRefreshToken(userVo.getUserRefreshToken())
            .nextData(userVo.getNextData())
            .build();
  }
}
