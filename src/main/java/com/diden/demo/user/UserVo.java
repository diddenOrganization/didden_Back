package com.diden.demo.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor
public class UserVo {
  @NotBlank(message = "사용자 비밀번호가 존재하지 않습니다.")
  private String userPassword;

  @NotBlank(message = "사용자 닉네임이 존재하지 않습니다.")
  private String userNickname;

  @NotBlank(message = "사용자 생년월일이 존재하지 않습니다.")
  private String userBirthday;

  @NotNull(message = "사용자 성별이 존재하지 않습니다.")
  private Gender userGender;

  @NotNull(message = "사용자 이메일이 존재하지 않습니다.")
  @Email(message = "이메일 형식이 아닙니다.")
  private String userEmail;

  @NotBlank(message = "사용자 휴대폰 번호가 존재하지 않습니다.")
  private String userPhoneNumber;

  private String userCreateDate;
  private String userUpdateDate;

  @NotNull(message = "사용자 개인정보동의가 존재하지 않습니다.")
  private PrivacyConsent userPrivacyConsent;

  @NotBlank(message = "사용자 로그인 타입이 존재하지 않습니다.")
  private String userLoginType;

  private String userAccessToken;
  private String userRefreshToken;
  private String nextData;

  @Builder
  public UserVo(
      String userPassword,
      String userNickname,
      String userBirthday,
      Gender userGender,
      String userEmail,
      String userPhoneNumber,
      String userCreateDate,
      String userUpdateDate,
      PrivacyConsent userPrivacyConsent,
      String userLoginType,
      String userAccessToken,
      String userRefreshToken,
      String nextData) {
    this.userPassword = userPassword;
    this.userNickname = userNickname;
    this.userBirthday = userBirthday;
    this.userGender = userGender;
    this.userEmail = userEmail;
    this.userPhoneNumber = userPhoneNumber;
    this.userCreateDate = userCreateDate;
    this.userUpdateDate = userUpdateDate;
    this.userPrivacyConsent = userPrivacyConsent;
    this.userLoginType = userLoginType;
    this.userAccessToken = userAccessToken;
    this.userRefreshToken = userRefreshToken;
    this.nextData = nextData;
  }

  public enum PrivacyConsent {
    AGREED("Y"),
    DISAGREEABLE("N");

    private final String choice;

    PrivacyConsent(String choice) {
      this.choice = choice;
    }

    public String getChoice() {
      return choice;
    }

    public static UserVo.PrivacyConsent getPrivacyConsentEnumType(final String privacyConsent) {
      for (PrivacyConsent value : PrivacyConsent.values()) {
        if (StringUtils.equals(value.getChoice(), privacyConsent)) {
          return value;
        }
      }
      throw new IllegalArgumentException("개인정보동의 형식이 맞지 않습니다.");
    }
  }

  public enum Gender {
    MALE("M"),
    FEMALE("F");

    private final String gender;

    Gender(String gender) {
      this.gender = gender;
    }

    public String getGender() {
      return gender;
    }

    public static UserVo.Gender getGenderEnumType(final String gender) {
      for (Gender value : Gender.values()) {
        if (StringUtils.equals(value.getGender(), gender)) {
          return value;
        }
      }
      throw new IllegalArgumentException("성별이 형식에 맞지 않습니다.");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UserVo userVo = (UserVo) o;

    if (!Objects.equals(userPassword, userVo.userPassword)) return false;
    if (!Objects.equals(userNickname, userVo.userNickname)) return false;
    if (!Objects.equals(userBirthday, userVo.userBirthday)) return false;
    if (!Objects.equals(userGender, userVo.userGender)) return false;
    if (!Objects.equals(userEmail, userVo.userEmail)) return false;
    if (!Objects.equals(userPhoneNumber, userVo.userPhoneNumber)) return false;
    if (!Objects.equals(userCreateDate, userVo.userCreateDate)) return false;
    if (!Objects.equals(userUpdateDate, userVo.userUpdateDate)) return false;
    if (!Objects.equals(userPrivacyConsent, userVo.userPrivacyConsent)) return false;
    if (!Objects.equals(userLoginType, userVo.userLoginType)) return false;
    if (!Objects.equals(userAccessToken, userVo.userAccessToken)) return false;
    return Objects.equals(userRefreshToken, userVo.userRefreshToken);
  }

  @Override
  public int hashCode() {
    int result = userPassword != null ? userPassword.hashCode() : 0;
    result = 31 * result + (userNickname != null ? userNickname.hashCode() : 0);
    result = 31 * result + (userBirthday != null ? userBirthday.hashCode() : 0);
    result = 31 * result + (userGender != null ? userGender.hashCode() : 0);
    result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
    result = 31 * result + (userPhoneNumber != null ? userPhoneNumber.hashCode() : 0);
    result = 31 * result + (userCreateDate != null ? userCreateDate.hashCode() : 0);
    result = 31 * result + (userUpdateDate != null ? userUpdateDate.hashCode() : 0);
    result = 31 * result + (userPrivacyConsent != null ? userPrivacyConsent.hashCode() : 0);
    result = 31 * result + (userLoginType != null ? userLoginType.hashCode() : 0);
    result = 31 * result + (userAccessToken != null ? userAccessToken.hashCode() : 0);
    result = 31 * result + (userRefreshToken != null ? userRefreshToken.hashCode() : 0);
    return result;
  }
}
