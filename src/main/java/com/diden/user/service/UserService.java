package com.diden.user.service;

import java.util.List;
import java.util.Map;

import com.diden.user.vo.UserVo;

public interface UserService {
    public List<UserVo> userList();

    public UserVo userInfo(Map<String, Object> userData);
}
