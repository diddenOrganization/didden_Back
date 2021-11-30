package com.diden.config;

import java.io.Serializable;
import java.util.Date;

import com.diden.config.vo.TokenVo;
import com.diden.user.vo.UserVo;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

    private static String ACCESS_KEY = "accessTokenKey";
    private static String REFRESH_KEY = "refreshTokenKey";

    public TokenVo makeJwtToken(UserVo userVo) {
        TokenVo tokenVo = new TokenVo();
        String accessToken = createToken(ACCESS_KEY, userVo, 2L);
        String refreshToken = createToken(REFRESH_KEY, userVo, 168L);

        tokenVo.setAccessJwsToken(accessToken);
        tokenVo.setRefreshJwsToken(refreshToken);
        return tokenVo;
    }

    private String createToken(String tokenKey, UserVo userVo, Long expHours) {
        Date now = new Date();

        Long expiredTime = 1000 * 60L * 60L * expHours;
        Date ext = new Date(); // 토큰 만료 시간

        ext.setTime(ext.getTime() + expiredTime);
        // UserVo userTestVo = new UserVo();
        // userTestVo.setUserId("test");
        // userTestVo.setUserName("test");
        // userTestVo.setUserPassword("test");
        // userTestVo.setUserNickname("test");
        // userTestVo.setUserBirthday("test");
        // userTestVo.setUserGender("test");
        // userTestVo.setUserEmail("test");
        // userTestVo.setUserPhoneNumber("test");
        // userTestVo.setUserCreateDate("20211102061731");
        // userTestVo.setUserUpdateDate("20211102061731");
        // userTestVo.setUserPrivacyConsent("1");

        System.out.println("=========================" + userVo);
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("fresh")
                .setSubject("TEST")
                .claim("result", true)
                .claim("userVo", userVo)
                .setIssuedAt(now)
                .setExpiration(ext)
                .signWith(SignatureAlgorithm.HS512, tokenKey).compact();
    }
}
