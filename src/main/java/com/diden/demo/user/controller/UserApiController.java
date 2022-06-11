package com.diden.demo.user.controller;

import com.diden.demo.config.vo.TokenVo;
import com.diden.demo.user.service.UserService;
import com.diden.demo.user.service.UserSocialService;
import com.diden.demo.user.vo.UserVo;
import com.diden.demo.utils.JwtTokenUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
@Slf4j
public class UserApiController {

    private final UserService userService;
    private final UserSocialService userSocialService;
    private final JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

    public UserApiController(UserService userService, UserSocialService userSocialService) {
        this.userService = userService;
        this.userSocialService = userSocialService;
    }

    @GetMapping(value = "/user/info")
    public String loginCheck(){
        return "check";
    }

    @GetMapping(value = "/user/list")
    public ResponseEntity<String> userList() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .body(new Gson().toJson(userService.userList()));
    }

    @PostMapping(value = "/user", produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> userInfo(@RequestBody(required = false) UserVo userVo) {
        UserVo userInfo = userService.userInfo(userVo);
        JsonObject userResult = new JsonObject();
        userResult.addProperty("result", userInfo != null);
        return new ResponseEntity<>(userResult.toString(), HttpStatus.OK);
    }

    @PostMapping("/user/api/social/json")
    public ResponseEntity<JsonObject> socialLoginLogic(@RequestBody JsonObject param){
        log.info("{}", param);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(param)
                ;
    }

    @PostMapping("/user/social/login")
    public ResponseEntity<String> userSocialLogin(@RequestBody JsonObject param){
        boolean result = false;

        if("kakao".equals("kakao")){
            result = userSocialService.signup(param.getAsJsonObject("loginParam"));
        }

        JsonObject obj = new JsonObject();
        obj.addProperty("result", result);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(obj.toString());
    }


    // Exception 어노테이션.
    // Json Exception 공통.
    // View Exception 공통.
    // UserLogin 시 성공/실패에 따라 회원가입 페이지 또는 메인페이지로 이동 시키는 로직 개발할 것.
    @PostMapping(value = "/user/login")
    public ResponseEntity<String> userLogin(@RequestBody(required = false) UserVo userVo) {
        try {
            JsonObject userResult = new JsonObject();

            if (Objects.isNull(userVo)) {
                throw new Exception("파라미터 null");
            }

            UserVo userVoData = userService.userInfo(userVo);
            if (Objects.isNull(userVoData)) {
                return new ResponseEntity<>(errorMethod("login", null), HttpStatus.OK);
            }

            if (Objects.toString(userVo.getUserId(), "").equals(userVoData.getUserId())) {
                if (Objects.toString(userVo.getUserPassword(), "").equals(userVoData.getUserPassword())) {
                    TokenVo token = new TokenVo();
                    token.setAccessJwsToken(jwtTokenUtil.makeJwtAccToken(userVo).getAccessJwsToken());
                    token.setRefreshJwsToken(jwtTokenUtil.makeJwtRefToken(userVo).getRefreshJwsToken());

                    userResult.addProperty("result", true);
                    userResult.addProperty("token_acc", token.getAccessJwsToken());
                    userResult.addProperty("token_ref", token.getRefreshJwsToken());

                    userVo.setUserRefreshToken(token.getRefreshJwsToken());
                    userService.userRefTokenUpdate(userVo);

                    return new ResponseEntity<>(userResult.toString(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(errorMethod("login", null), HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>(errorMethod("login", null), HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(errorMethod("", e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/user/logout")
    public ResponseEntity<String> userLogout(@RequestBody(required = false) UserVo userVo) {
        JsonObject userResult = new JsonObject();

        try {
            if (!Objects.toString(userVo.getUserId(), "").equals("")
                    && !Objects.toString(userVo.getUserPassword(), "").equals("")) {
                UserVo getUserVo = userService.userInfo(userVo);

                getUserVo.setUserRefreshToken("");
                userService.userUpdate(getUserVo);
                userResult.addProperty("result", true);

                return new ResponseEntity<>(userResult.toString(), HttpStatus.OK);
            } else {
                throw new Exception("잘못된 접근.");
            }

        } catch (Exception e) {
            return new ResponseEntity<>(errorMethod("", e), HttpStatus.OK);
        }
    }

    @PutMapping(value = "/user/insert")
    public ResponseEntity<String> userInsert(@RequestBody(required = false) UserVo userVo) {
        JsonObject userResult = new JsonObject();
        try {
            if (Objects.isNull(userVo)) {
                throw new Exception("파라미터 null");
            }

            UserVo userCheck = new UserVo();
            userCheck.setUserId(userVo.getUserId());
            userCheck.setUserPassword(userVo.getUserPassword());

            userCheck = userService.userInfo(userCheck);
            if (Objects.isNull(userCheck)) {
                userService.userInsert(userVo);
                userResult.addProperty("result", true);
                userResult.addProperty("put", "insert");
            }

        } catch (Exception e) {
            e.printStackTrace();
            userResult.addProperty("result", false);
            userResult.addProperty("error", e.getMessage());
            return new ResponseEntity<>(userResult.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(userResult.toString(), HttpStatus.OK);
    }

    @PutMapping(value = "/user/update")
    public ResponseEntity<String> userUpdate(@RequestBody(required = false) UserVo userVo) {
        JsonObject userResult = new JsonObject();
        try {
            if (Objects.isNull(userVo)) {
                throw new Exception("파라미터 null");
            }

            UserVo userCheck = new UserVo();
            userCheck.setUserId(userVo.getUserId());
            userCheck.setUserPassword(userVo.getUserPassword());

            userCheck = userService.userInfo(userCheck);
            if (!Objects.isNull(userCheck)) {
                userService.userUpdate(userVo);
                userResult.addProperty("result", true);
                userResult.addProperty("put", "update");
            }

        } catch (Exception e) {
            e.printStackTrace();
            userResult.addProperty("result", false);
            userResult.addProperty("error", e.getMessage());
            return new ResponseEntity<>(userResult.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(userResult.toString(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/user")
    public ResponseEntity<String> userDelete(@RequestBody(required = false) UserVo userVo) {
        JsonObject userResult = new JsonObject();
        try {
            if (Objects.isNull(userVo)) {
                throw new Exception("파라미터 null");
            }

            UserVo userCheck = new UserVo();
            userCheck.setUserId(userVo.getUserId());
            userCheck.setUserPassword(userVo.getUserPassword());

            userCheck = userService.userInfo(userCheck);
            if (Objects.isNull(userCheck)) {
                throw new Exception("삭제할 계정이 존재하지 않습니다.");
            } else {
                userService.userDelete(userVo);
                userResult.addProperty("result", true);
            }

        } catch (Exception e) {
            e.printStackTrace();
            userResult.addProperty("result", false);
            userResult.addProperty("error", e.getMessage());
            return new ResponseEntity<>(userResult.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(userResult.toString(), HttpStatus.OK);
    }

    public String errorMethod(String errorType, Exception e) {
        JsonObject errorResult = new JsonObject();

        if (e != null) {
            errorResult.addProperty("result", false);
            errorResult.addProperty("error", e.getMessage());
        } else if ("login".equals(errorType)) {
            errorResult.addProperty("result", false);
            errorResult.addProperty("error", "계정이 존재하지 않습니다.");
        }

        return errorResult.toString();
    }
}
