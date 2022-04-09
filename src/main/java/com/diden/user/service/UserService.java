package com.diden.user.service;

import java.util.List;

import com.diden.user.vo.UserVo;

public interface UserService {
    int userCount(UserVo userVo);

    List<UserVo> userList();

    UserVo userInfo(UserVo userVo);

    void userInsert(UserVo userVo);

    void userUpdate(UserVo userVo);

    void userRefTokenUpdate(UserVo userVo);

    void userDelete(UserVo userVo);

    UserVo userRefreshTokenInfo(UserVo userVo);
}
