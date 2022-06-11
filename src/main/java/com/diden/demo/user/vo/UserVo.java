package com.diden.demo.user.vo;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private String userId = null;
    private String userName = null;
    private String userPassword = null;
    private String userNickname = null;
    private String userBirthday = null;
    private String userGender = null;
    private String userEmail = null;
    private String userPhoneNumber = null;
    private String userCreateDate = null;
    private String userUpdateDate = null;
    private String userPrivacyConsent = null;
    private String userSocialLoginType = null;
    private String userAccessToken = null;
    private String userRefreshToken = null;
}
