package com.diden.demo.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
  boolean emailDuplicateCheck(final String userEmail);

  boolean existsUserEmail(@Param("userEmail") final String userEmail);

  int userCount(UserVo userVo);

  int userCheck(UserVo userVo);

  List<UserVo> userList();

  UserVo userInfo(UserVo userVo);

  UserVo findEmailByUser(@Param("userEmail") String userEmail);

  void userInsert(UserVo userVo);

  void userUpdate(UserVo userVo);

  void userTokenUpdate(UserVo userVo);

  void userDelete(UserVo userVo);

  UserVo userRefreshTokenInfo(UserVo userVo);

  String findByLoginType(@Param("authorization") final String authorization);
}
