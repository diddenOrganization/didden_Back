package com.diden.demo.user.service;

import java.util.List;

import com.diden.demo.user.vo.UserVo;
import com.google.gson.JsonObject;

public interface UserService {
    int userCount(UserVo userVo);

    List<UserVo> userList();

    UserVo userInfo(UserVo userVo);

    void userInsert(UserVo userVo);

    void userUpdate(UserVo userVo);

    void userRefTokenUpdate(UserVo userVo);

    void userDelete(UserVo userVo);

    UserVo userRefreshTokenInfo(UserVo userVo);

    boolean socialSignup(JsonObject param);
}
