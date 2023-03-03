package com.diden.demo.utils;

import com.diden.demo.common.config.properties.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static com.diden.demo.common.config.properties.JwtProperties.SECRET;

class JwtTokenUtilTest {

  @Test
  void createAccessToken() {
    String accessToken =
        Jwts.builder()
            .setSubject("whySoSerious")
            .setExpiration(new Date(System.currentTimeMillis()))
            .claim("type", JwtProperties.ACCESS_TOKEN)
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact();

    System.out.println(accessToken);
  }

  @Test
  void tokenDecoding() {
    String accessToken =
        "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ3aHlTb1NlcmlvdXMiLCJleHAiOjE2NTYzMTUzNTYsInR5cGUiOiJhY2Nlc3NUb2tlbiJ9.szNUELK7AOs-OcOcIZMcPBDNmfzK0MpfY5Wv6ZTY20tI-PIX_8qm85FlRvCHAN7k41_iSQLzYqBX2pBiBnaoxg";
    System.out.println(Jwts.parser().setSigningKey(SECRET).parse(accessToken));
  }
}
