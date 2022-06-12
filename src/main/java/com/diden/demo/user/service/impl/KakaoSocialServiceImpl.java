package com.diden.demo.user.service.impl;

import com.diden.demo.user.mapper.UserMapper;
import com.diden.demo.user.service.UserSocialService;
import com.diden.demo.user.vo.UserVo;
import com.diden.demo.utils.JwtSocialKakaoTokenUtils;
import com.diden.demo.utils.JwtSocialTokenCheckInterface;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

@Service
public class KakaoSocialServiceImpl implements UserSocialService {
    private final UserMapper userMapper;

    public KakaoSocialServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    private final JwtSocialTokenCheckInterface jwtSocialKakaoTokenUtils = new JwtSocialKakaoTokenUtils();

    @Override
    public boolean signup(final JsonObject param) {
        try {
            final String accessToken = param.get("accessToken").getAsString();
            final String refreshToken = param.get("refreshToken").getAsString();

            final JsonObject jsonObject = jwtSocialKakaoTokenUtils.socialAccessToken(accessToken);
            final JsonObject kakaoAccount = jsonObject.getAsJsonObject("kakao_account");

            final UserVo userVo = UserVo.builder()
                    .userId(param.get("id").getAsString())
                    .userPassword(accessToken)
                    .userEmail(kakaoAccount.get("email").getAsString())
                    .userName(kakaoAccount.getAsJsonObject("profile").get("nickname").getAsString())
                    .userNickname(kakaoAccount.getAsJsonObject("profile").get("nickname").getAsString())
                    .userPrivacyConsent("Y")
                    .userSocialLoginType("kakao")
                    .userRefreshToken(refreshToken)
                    .userAccessToken(accessToken)
                    .build();

            if (userMapper.userCheck(userVo) > 0) userMapper.userUpdate(userVo);
            else userMapper.userInsert(userVo);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //{loginParam={accessTokenExpiresAt=2022-06-05 04:01:16, refreshToken=McK4Qmqkd9Qt5Fz1KVlfV5cYlAXSTxl49cDKy7iKCisMqAAAAYEthKeh, refreshTokenExpiresAt=2022-08-03 16:01:16, accessToken=-5r-0No2bLvxeUy57pigD4hx-lAhcZmp6teKJTt4CisMqAAAAYEthKej, scopes=[account_email, profile_nickname], isEmailValid=true, birthday=null, emailNeedsAgreement=false, nickname=이승호, birthyearNeedsAgreement=null, ageRangeNeedsAgreement=true, ageRange=null, phoneNumberNeedsAgreement=null, birthdayNeedsAgreement=null, isKoreanNeedsAgreement=null, gender=null, id=2024434274, email=sh5623789@naver.com, birthyear=null, genderNeedsAgreement=true, profileImageUrl=null, profileNeedsAgreement=null, thumbnailImageUrl=null, isKorean=null, isEmailVerified=true, birthdayType=null, phoneNumber=null}}
}
