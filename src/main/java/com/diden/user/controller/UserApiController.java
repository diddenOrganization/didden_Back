package com.diden.user.controller;

import java.util.List;
import java.util.Objects;

import com.diden.user.service.UserService;
import com.diden.user.vo.UserVo;
import com.google.gson.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/user/list", produces = "application/json; charset=UTF-8")
    public String userList() {
        List<UserVo> userVoList = userService.userList();
        JsonObject userJsonList = new JsonObject();

        for (int i = 0; i < userVoList.size(); i++) {
            // Map<String, UserVo> map = userVo.get(i);
            JsonObject userJson = new JsonObject();
            UserVo userVo = (UserVo) userVoList.get(i);
            userJson.addProperty("userId", userVo.getUserId());
            userJson.addProperty("userName", userVo.getUserName());
            userJson.addProperty("userPassword", userVo.getUserPassword());
            userJson.addProperty("userNickname", userVo.getUserNickname());
            userJson.addProperty("userBirthday", userVo.getUserBirthday());
            userJson.addProperty("userGender", userVo.getUserGender());
            userJson.addProperty("userEmail", userVo.getUserEmail());
            userJson.addProperty("userPhoneNumber", userVo.getUserPhoneNumber());
            userJson.addProperty("userCreateDate", userVo.getUserCreateDate());
            userJson.addProperty("userUpdateDate", userVo.getUserUpdateDate());
            userJson.addProperty("userPrivacyConsent", userVo.getUserPrivacyConsent());
            userJsonList.add("user" + i, userJson);
        }

        return userJsonList.toString();
    }

    @PostMapping(value = "/user/login", produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> userData(@RequestBody(required = false) UserVo userVo) throws Exception {
        try {
            if (Objects.isNull(userVo)) {
                throw new Exception("파라미터 null");
            }

            UserVo userVoData = userService.userInfo(userVo);
            if (Objects.isNull(userVoData)) {
                throw new Exception("계정이 존재하지 않습니다.");
            }

            JsonObject userResult = new JsonObject();

            if (Objects.toString(userVo.getUserId(), "").equals(userVoData.getUserId())) {
                if (Objects.toString(userVo.getUserPassword(), "").equals(userVoData.getUserPassword())) {
                    userResult.addProperty("result", true);
                    return new ResponseEntity<>(userResult.toString(), HttpStatus.OK);
                } else {
                    throw new Exception("비밀번호가 틀리거나, 입력하지 않았습니다.");
                }
            } else {
                throw new Exception("계정이 없거나, 아이디가 틀립니다.");
            }
        } catch (Exception e) {
            JsonObject userResult = new JsonObject();
            userResult.addProperty("result", false);
            userResult.addProperty("error", e.getMessage());
            return new ResponseEntity<>(userResult.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/user", produces = "application/json; charset=UTF-8")
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
            } else {
                userService.userUpdate(userVo);
                userResult.addProperty("result", true);
                userResult.addProperty("put", "update");
            }

        } catch (Exception e) {
            // TODO: handle exception
            userResult.addProperty("result", false);
            userResult.addProperty("error", e.getMessage());
            return new ResponseEntity<>(userResult.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(userResult.toString(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/user", produces = "application/json; charset=UTF-8")
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
            // TODO: handle exception
            userResult.addProperty("result", false);
            userResult.addProperty("error", e.getMessage());
            return new ResponseEntity<>(userResult.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(userResult.toString(), HttpStatus.OK);
    }
}
