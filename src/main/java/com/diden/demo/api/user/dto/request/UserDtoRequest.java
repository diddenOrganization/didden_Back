package com.diden.demo.api.user.dto.request;

import com.diden.demo.domain.user.enums.GenderEnum;
import com.diden.demo.domain.user.enums.PrivacyConsentEnum;
import com.diden.demo.domain.user.vo.request.UserVoRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@ToString
public class UserDtoRequest {
  @NotBlank(message = "사용자 비밀번호가 존재하지 않습니다.")
  private String userPassword;

  @NotBlank(message = "사용자 닉네임이 존재하지 않습니다.")
  private String userNickname;

  @NotBlank(message = "사용자 생년월일이 존재하지 않습니다.")
  private String userBirthday;

  @NotNull(message = "사용자 성별이 존재하지 않습니다.")
  private GenderEnum userGenderEnum;

  @NotNull(message = "사용자 이메일이 존재하지 않습니다.")
  @Email(message = "이메일 형식이 아닙니다.")
  private String userEmail;

  @NotBlank(message = "사용자 휴대폰 번호가 존재하지 않습니다.")
  private String userPhoneNumber;

  private String userCreateDate;
  private String userUpdateDate;

  @NotNull(message = "사용자 개인정보동의가 존재하지 않습니다.")
  private PrivacyConsentEnum userPrivacyConsentEnum;

  @NotBlank(message = "사용자 로그인 타입이 존재하지 않습니다.")
  private String userLoginType;

  private String userAccessToken;
  private String userRefreshToken;
  private String nextData;

  public UserDtoRequest() {}

  @Builder
  public UserDtoRequest(
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

  public static UserVoRequest transportDataByUserDtoRequest(UserDtoRequest userDtoRequest) {
    return UserVoRequest.builder()
        .userPassword(userDtoRequest.getUserPassword())
        .userNickname(userDtoRequest.getUserNickname())
        .userBirthday(userDtoRequest.getUserBirthday())
        .userGenderEnum(userDtoRequest.getUserGenderEnum())
        .userEmail(userDtoRequest.getUserEmail())
        .userPhoneNumber(userDtoRequest.getUserPhoneNumber())
        .userCreateDate(userDtoRequest.getUserCreateDate())
        .userUpdateDate(userDtoRequest.getUserUpdateDate())
        .userPrivacyConsentEnum(userDtoRequest.getUserPrivacyConsentEnum())
        .userLoginType(userDtoRequest.getUserLoginType())
        .userAccessToken(userDtoRequest.getUserAccessToken())
        .userRefreshToken(userDtoRequest.getUserRefreshToken())
        .nextData(userDtoRequest.getNextData())
        .build();
  }
}
