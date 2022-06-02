package com.diden.demo.user.service.impl;

import com.diden.demo.user.mapper.UserMapper;
import com.diden.demo.user.service.UserService;
import com.diden.demo.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Autowired
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
