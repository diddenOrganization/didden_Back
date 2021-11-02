package com.diden.user.service;

import java.util.List;

import com.diden.user.vo.UserVo;

public interface UserService {
    public List<UserVo> userList();

    public UserVo userInfo(UserVo userVo);

    public void userInsert(UserVo userVo);

    public void userUpdate(UserVo userVo);

    public void userDelete(UserVo userVo);
}
