package com.diden.demo.token;

import com.diden.demo.config.vo.TokenVo;
import com.diden.demo.utils.JwtTokenUtil;
import com.diden.demo.user.service.UserService;
import com.diden.demo.user.service.impl.UserServiceImpl;
import com.diden.demo.user.vo.UserVo;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TokenApiController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    public TokenApiController(UserServiceImpl userServiceImpl, JwtTokenUtil jwtTokenUtil) {
        this.userService = userServiceImpl;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/token/api/acc")
    public ResponseEntity<String> accessTokenApi(@RequestBody(required = false) UserVo userVo,
            HttpServletRequest request) {
        TokenVo token = new TokenVo();
        UserVo pmUserVo = new UserVo();
        JsonObject tokenResult = new JsonObject();

        pmUserVo.setUserRefreshToken(userVo.getUserRefreshToken());
        int getUserCount = userService.userCount(pmUserVo);

        if (getUserCount > 0) {
            UserVo newUserVo = userService.userRefreshTokenInfo(pmUserVo);

            token.setAccessJwsToken(jwtTokenUtil.makeJwtAccToken(newUserVo).getAccessJwsToken());
            tokenResult.addProperty("result", true);
            tokenResult.addProperty("token_acc", token.getAccessJwsToken());
        } else {
            tokenResult.addProperty("result", false);
            tokenResult.addProperty("error", "재 로그인 필요.");
        }

        return new ResponseEntity<>(tokenResult.toString(), HttpStatus.OK);
    }

    @PostMapping("")
    public void refrashTokenApi() {

    }
}
