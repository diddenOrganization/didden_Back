package com.diden.config;

import com.diden.config.vo.TokenVo;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenProvider {

    private static String ACCESS_KEY = "accessTokenKey";
    private static String REFRESH_KEY = "refreshTokenKey";

    public boolean jwtAccTokenCheck(TokenVo tokenVo) {
        String token = null;
        Claims accessClaims = null;
        validationAuthorizationHeader(tokenVo.getAccessJwsToken());

        token = extractToken(tokenVo.getAccessJwsToken());
        accessClaims = Jwts.parser()
                .setSigningKey(ACCESS_KEY)
                .parseClaimsJws(token)
                .getBody();

        if ((boolean) accessClaims.get("result")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean jwtRefTokenCheck(TokenVo tokenVo) {
        String token = null;
        Claims refreshClaims = null;
        validationAuthorizationHeader(tokenVo.getRefreshJwsToken());

        token = extractToken(tokenVo.getRefreshJwsToken());
        refreshClaims = Jwts.parser()
                .setSigningKey(REFRESH_KEY)
                .parseClaimsJws(token)
                .getBody();

        if ((boolean) refreshClaims.get("result")) {
            return true;
        } else {
            return false;
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

    public void parseJwtToken(TokenVo tokenVo) {
        // String token = null;
        // Claims accessClaims = null;
        // Claims refreshClaims = null;

        // try {
        // validationAuthorizationHeader(tokenVo.getAccessJwsToken());

        // token = extractToken(tokenVo.getAccessJwsToken());
        // accessClaims = Jwts.parser()
        // .setSigningKey(ACCESS_KEY)
        // .parseClaimsJws(token)
        // .getBody();

        // return accessClaims;
        // } catch (Exception e) {
        // e.printStackTrace();
        // return accessClaims;
        // userService.userInfo(userVo);
        // validationAuthorizationHeader(tokenVo.getRefreshJwsToken());
        // token = extractToken(tokenVo.getRefreshJwsToken());
        // refreshClaims = Jwts.parser()
        // .setSigningKey(REFRESH_KEY)
        // .parseClaimsJws(token)
        // .getBody();

        // JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        // Map<String, Object> userMapData = (Map<String, Object>)
        // refreshClaims.get("userVo");
        // UserVo userVo = new UserVo();

        // userVo.setUserId((String) userMapData.get("userId"));
        // userVo.setUserName((String) userMapData.get("userName"));
        // userVo.setUserPassword((String) userMapData.get("userPassword"));
        // userVo.setUserNickname((String) userMapData.get("userNickname"));
        // userVo.setUserBirthday((String) userMapData.get("userBirthday"));
        // userVo.setUserGender((String) userMapData.get("userGender"));
        // userVo.setUserEmail((String) userMapData.get("userEmail"));
        // userVo.setUserPhoneNumber((String) userMapData.get("userPhoneNumber"));
        // userVo.setUserCreateDate((String) userMapData.get("userCreateDate"));
        // userVo.setUserUpdateDate((String) userMapData.get("userUpdateDate"));
        // userVo.setUserPrivacyConsent((String) userMapData.get("userPrivacyConsent"));
        // userVo.setUserSocialLoginType((String)
        // userMapData.get("userSocialLoginType"));

        // TokenVo newToken = jwtTokenUtil.makeJwtToken(userVo);
        // Claims newAccessClaims = parseJwtToken(newToken);

        // return newAccessClaims;
        // }

    }

}
