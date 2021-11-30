package com.diden.config;

import java.util.Map;

import com.diden.config.vo.TokenVo;
import com.diden.user.vo.UserVo;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenProvider {

    private static String ACCESS_KEY = "accessTokenKey";
    private static String REFRESH_KEY = "refreshTokenKey";

    public Claims parseJwtToken(TokenVo tokenVo) {
        validationAuthorizationHeader(tokenVo.getAccessJwsToken());
        validationAuthorizationHeader(tokenVo.getRefreshJwsToken());
        String token = null;

        Claims accessClaims = null;
        Claims refreshClaims = null;

        try {
            System.out.println("============================================= ");
            token = extractToken(tokenVo.getAccessJwsToken());
            System.out.println("token : " + token);
            accessClaims = Jwts.parser()
                    .setSigningKey(ACCESS_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println("accessClaims : " + accessClaims);
            return accessClaims;
        } catch (Exception e) {
            token = extractToken(tokenVo.getRefreshJwsToken());
            refreshClaims = Jwts.parser()
                    .setSigningKey(REFRESH_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
            Map<String, Object> userMapData = (Map<String, Object>) refreshClaims.get("userVo");
            UserVo userVo = new UserVo();

            userVo.setUserId((String) userMapData.get("userId"));
            userVo.setUserName((String) userMapData.get("userName"));
            userVo.setUserPassword((String) userMapData.get("userPassword"));
            userVo.setUserNickname((String) userMapData.get("userNickname"));
            userVo.setUserBirthday((String) userMapData.get("userBirthday"));
            userVo.setUserGender((String) userMapData.get("userGender"));
            userVo.setUserEmail((String) userMapData.get("userEmail"));
            userVo.setUserPhoneNumber((String) userMapData.get("userPhoneNumber"));
            userVo.setUserCreateDate((String) userMapData.get("userCreateDate"));
            userVo.setUserUpdateDate((String) userMapData.get("userUpdateDate"));
            userVo.setUserPrivacyConsent((String) userMapData.get("userPrivacyConsent"));
            userVo.setUserSocialLoginType((String) userMapData.get("userSocialLoginType"));

            TokenVo newToken = jwtTokenUtil.makeJwtToken(userVo);
            Claims newAccessClaims = parseJwtToken(newToken);

            return newAccessClaims;
        }

    }

    private void validationAuthorizationHeader(String header) {
        if (header == null || !header.startsWith("Bearer ")) {
            throw new IllegalArgumentException();
        }
    }

    private String extractToken(String authorizationHeader) {
        return authorizationHeader.substring("Bearer ".length());
    }
}
