package com.diden.config;

import java.io.Serializable;
import java.time.Duration;
import java.util.Date;

import com.diden.user.vo.UserVo;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
    public String makeJwtToken(UserVo userVo) {
        Date now = new Date();
        return Jwts.builder().setHeaderParam(Header.TYPE, Header.JWT_TYPE).setIssuer("fresh").setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis())).claim("data", userVo)
                .signWith(SignatureAlgorithm.HS512, "secret").compact();
    }
}
