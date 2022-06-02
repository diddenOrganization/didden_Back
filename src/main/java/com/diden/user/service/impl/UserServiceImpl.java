package com.diden.user.service.impl;

import com.diden.user.mapper.UserMapper;
import com.diden.user.service.UserService;
import com.diden.user.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int userCount(UserVo userVo) {
        return userMapper.userCount(userVo);
    }

    @Override
    public List<UserVo> userList() {
        return userMapper.userList();
    }

    @Override
    public UserVo userInfo(UserVo userVo) {
        return userMapper.userInfo(userVo);
    }

    @Override
    public void userInsert(UserVo userVo) {
        userMapper.userInsert(userVo);
    }

    @Override
    public void userUpdate(UserVo userVo) {
        userMapper.userUpdate(userVo);
    }

    @Override
    public void userRefTokenUpdate(UserVo userVo) {
        userMapper.userRefTokenUpdate(userVo);
    }

    @Override
    public void userDelete(UserVo userVo) {
        userMapper.userDelete(userVo);
    }

    @Override
    public UserVo userRefreshTokenInfo(UserVo userVo) {
        return userMapper.userRefreshTokenInfo(userVo);
    }
}
