package com.diden.demo.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

  boolean existsUserEmail(@Param("userEmail") final String userEmail);

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
