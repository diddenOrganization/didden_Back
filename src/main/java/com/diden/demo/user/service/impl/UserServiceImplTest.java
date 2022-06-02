package com.diden.demo.user.service.impl;

import com.diden.demo.user.mapper.UserMapper;
import com.diden.demo.user.service.UserService;
import com.diden.demo.user.vo.UserVo;

import java.util.List;

public class UserServiceImplTest implements UserService {
    private final UserMapper userMapper;

    public UserServiceImplTest(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int userCount(UserVo userVo) {
        return 0;
    }

    @Override
    public List<UserVo> userList() {
        return null;
    }

    @Override
    public UserVo userInfo(UserVo userVo) {
        return null;
    }

    @Override
    public void userInsert(UserVo userVo) {

    }

    @Override
    public void userUpdate(UserVo userVo) {

    }

    @Override
    public void userRefTokenUpdate(UserVo userVo) {

    }

    @Override
    public void userDelete(UserVo userVo) {

    }

    @Override
    public UserVo userRefreshTokenInfo(UserVo userVo) {
        return null;
    }
}
