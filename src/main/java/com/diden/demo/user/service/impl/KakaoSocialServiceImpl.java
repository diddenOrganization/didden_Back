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
    public boolean signup(JsonObject param) {
        try {
            String accessToken = param.get("accessToken").getAsString();
            String refreshToken = param.get("refreshToken").getAsString();

            JsonObject jsonObject = jwtSocialKakaoTokenUtils.socialAccessToken(accessToken);
            JsonObject kakao_account = jsonObject.getAsJsonObject("kakao_account");

            UserVo userVo = UserVo
                    .builder()
                    .userId(param.get("id").getAsString())
                    .userPassword(accessToken)
                    .userEmail(kakao_account.get("email").getAsString())
                    .userName(kakao_account.getAsJsonObject("profile").get("nickname").getAsString())
                    .userNickname(kakao_account.getAsJsonObject("profile").get("nickname").getAsString())
                    .userPrivacyConsent("Y")
                    .userSocialLoginType("카카오")
                    .userRefreshToken(refreshToken)
                    .userAccessToken(accessToken)
                    .build();

            if(userMapper.userCheck(userVo) > 0){
                userMapper.userUpdate(userVo);
            } else {
                userMapper.userInsert(userVo);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //{loginParam={accessTokenExpiresAt=2022-06-05 04:01:16, refreshToken=McK4Qmqkd9Qt5Fz1KVlfV5cYlAXSTxl49cDKy7iKCisMqAAAAYEthKeh, refreshTokenExpiresAt=2022-08-03 16:01:16, accessToken=-5r-0No2bLvxeUy57pigD4hx-lAhcZmp6teKJTt4CisMqAAAAYEthKej, scopes=[account_email, profile_nickname], isEmailValid=true, birthday=null, emailNeedsAgreement=false, nickname=이승호, birthyearNeedsAgreement=null, ageRangeNeedsAgreement=true, ageRange=null, phoneNumberNeedsAgreement=null, birthdayNeedsAgreement=null, isKoreanNeedsAgreement=null, gender=null, id=2024434274, email=sh5623789@naver.com, birthyear=null, genderNeedsAgreement=true, profileImageUrl=null, profileNeedsAgreement=null, thumbnailImageUrl=null, isKorean=null, isEmailVerified=true, birthdayType=null, phoneNumber=null}}
}
