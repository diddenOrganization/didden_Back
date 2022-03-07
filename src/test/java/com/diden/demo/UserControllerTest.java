package com.diden.demo;

import java.util.Base64;
import java.util.List;

import com.diden.config.JwtTokenProvider;
import com.diden.config.vo.TokenVo;
import com.diden.user.service.UserService;
import com.diden.user.service.impl.UserServiceImpl;
import com.diden.user.vo.UserVo;
import com.diden.utils.JwtTokenUtil;
import com.diden.utils.ParsingJson;

import org.apache.catalina.User;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserControllerTest {
    @Autowired
    UserService userService;

    @Test
    // @Transactional
    public void 토큰생성() {
        UserVo userVo = new UserVo();
        userVo.setUserId("test");
        userVo.setUserPassword("test");

        UserVo resultUserVo = userService.userInfo(userVo);
        System.out.println(resultUserVo);
        /*TokenVo tokenVo = new TokenVo();
        tokenVo.setAccessJwsToken(jwtTokenUtil.makeJwtAccToken(resultUserVo).getAccessJwsToken());
        tokenVo.setRefreshJwsToken(jwtTokenUtil.makeJwtRefToken(resultUserVo).getRefreshJwsToken());

        System.out.println(tokenVo);

        토큰체크(tokenVo.getAccessJwsToken(), tokenVo.getRefreshJwsToken());*/
    }

    //@Test
    public void 토큰해제() {
        ParsingJson parsingJson = new ParsingJson();
        String accToken = "eeyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhY2MiLCJzdWIiOiJURVNUIiwicmVzdWx0Ijp0cnVlLCJpYXQiOjE2Mzg1NDExOTksImV4cCI6MTYzODU0ODM5OX0.pkq_V2OQDIS5YUCN5EAjA7of4LMzUJcEdJsHCmJbL47vfE_A9um6H1ECQ-FGj8stszFoXW_0nQFmgJK7eIT6Dg";
        List<Object> payLoad = Arrays.asList(accToken.split("\\."));
        String payload = (String) payLoad.get(1);

        byte[] decodedBytes = Base64.getDecoder().decode(payload);
        String jsonPayload = new String(decodedBytes);
        String iss = parsingJson.stringToJsonObject(jsonPayload).get("iss").toString();

        System.out.println("\"iss\"");
        System.out.println("\"acc\"".equals(iss));
        System.out.println("acc".equals(iss));

        System.out.println(parsingJson.stringToJsonObject(jsonPayload));
    }



    // @Test
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

            // Claims claims = jwtTokenProvider.parseJwtToken(responseTokenVo);
            // System.out.println(JwsClaims);
            // System.out.println(claims);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
