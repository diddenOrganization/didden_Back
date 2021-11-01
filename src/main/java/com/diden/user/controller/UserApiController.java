package com.diden.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.diden.user.service.UserService;
import com.diden.user.vo.UserVo;
import com.google.gson.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    UserService userService;

    @GetMapping("/user-list.json")
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

    @PostMapping("/user/api/get")
    public ResponseEntity<UserVo> userData(@RequestBody UserVo userVo) String userPassword) {

        Map<String, Object> userData = new HashMap<String, Object>();

        userData.put("userId", userId);
        userData.put("userPassword", userPassword);

        UserVo userVo = userService.userInfo(userData);
        JsonObject userResult = new JsonObject();

        if (userVo == null || Objects.toString(userVo.getUserId(), "").equals("")) {
            userResult.addProperty("result", false);
            return new ResponseEntity<>(UserVo, HttpStatus.OK);
        } else {
            userResult.addProperty("result", true);
            return new ResponseEntity<>(UserVo, HttpStatus.OK);
        }
    }
}
