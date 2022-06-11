package com.diden.demo.user.mapper;

import java.util.List;

import com.diden.demo.user.vo.UserVo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int userCount(UserVo userVo);

    int userCheck(UserVo userVo);

    List<UserVo> userList();

    UserVo userInfo(UserVo userVo);

    void userInsert(UserVo userVo);

    void userUpdate(UserVo userVo);

    void userRefTokenUpdate(UserVo userVo);

    void userDelete(UserVo userVo);

    UserVo userRefreshTokenInfo(UserVo userVo);
}
