package com.diden.token;

import javax.servlet.http.HttpServletRequest;

import com.diden.config.vo.TokenVo;
import com.diden.user.service.UserService;
import com.diden.user.vo.UserVo;
import com.diden.utils.JwtTokenUtil;
import com.google.gson.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenApiController {

    @Autowired
    private UserService userService;

    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/token/api/acc")
    public ResponseEntity<String> accessTokenApi(@RequestBody(required = false) UserVo userVo,
            HttpServletRequest request) {
        TokenVo token = new TokenVo();
        UserVo pmUserVo = new UserVo();
        JsonObject userResult = new JsonObject();

        pmUserVo.setUserRefreshToken(userVo.getUserRefreshToken());
        int getUserCount = userService.userCount(pmUserVo);

        if (getUserCount > 0) {
            UserVo newUserVo = userService.userRefreshTokenInfo(pmUserVo);

            token.setAccessJwsToken(jwtTokenUtil.makeJwtAccToken(newUserVo).getAccessJwsToken());
            userResult.addProperty("result", true);
            userResult.addProperty("token_acc", token.getAccessJwsToken());
        } else {
            userResult.addProperty("result", false);
            userResult.addProperty("error", "재 로그인 필요.");
        }

        return new ResponseEntity<>(userResult.toString(), HttpStatus.OK);
    }

    @PostMapping("")
    public void refrashTokenApi() {

    }
}
