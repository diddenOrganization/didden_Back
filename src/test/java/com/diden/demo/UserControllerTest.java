package com.diden.demo;

import com.diden.config.JwtTokenProvider;
import com.diden.config.JwtTokenUtil;
import com.diden.config.vo.TokenVo;
import com.diden.user.service.UserService;
import com.diden.user.vo.UserVo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Spy
    JwtTokenUtil jwtTokenUtil;

    @Spy
    UserService userService;

    @Spy
    JwtTokenProvider jwtTokenProvider;

    @Test
    @Transactional
    public void 토큰생성() {
        UserVo userVo = new UserVo();
        userVo.setUserId("test");
        userVo.setUserPassword("test");

        UserVo resultUserVo = userService.userInfo(userVo);
        TokenVo tokenVo = jwtTokenUtil.makeJwtToken(resultUserVo);

        System.out.println(tokenVo.getAccessJwsToken());
        System.out.println(tokenVo.getRefreshJwsToken());

        토큰체크(tokenVo.getAccessJwsToken(), tokenVo.getRefreshJwsToken());
    }

    @Test
    public void 토큰체크(String AccToken, String RefToken) {
        try {
            TokenVo tokenVo = new TokenVo();

            tokenVo.setAccessJwsToken(AccToken);
            tokenVo.setRefreshJwsToken(RefToken);

            Jws<Claims> JwsClaims = Jwts.parser()
                    .setSigningKey("accessTokenKey")
                    .parseClaimsJws(AccToken);

            TokenVo responseTokenVo = new TokenVo();
            responseTokenVo.setAccessJwsToken("Bearer " + tokenVo.getAccessJwsToken());
            responseTokenVo.setRefreshJwsToken("Bearer " + tokenVo.getRefreshJwsToken());

            Claims claims = jwtTokenProvider.parseJwtToken(responseTokenVo);
            System.out.println(JwsClaims);
            System.out.println(claims);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
