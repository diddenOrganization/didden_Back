package com.diden.user.service.impl;

import java.util.List;

import com.diden.user.mapper.UserMapper;
import com.diden.user.service.UserService;
import com.diden.user.vo.UserVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserVo> userList() {
        return userMapper.userList();
    }

    @Override
    public UserVo userInfo(UserVo userVo) {
        // TODO Auto-generated method stub
        return userMapper.userInfo(userVo);
    }

    @Override
    public void userInsert(UserVo userVo) {
        // TODO Auto-generated method stub
        userMapper.userInsert(userVo);
    }

}
