package com.diden.demo.user;

import com.google.gson.JsonObject;

import java.util.List;

public interface UserService {
  boolean existsUserEmail(final String userEmail);

  boolean emailDuplicateCheck(final String userEmail);

  int userCheck(UserVo userVo);

  int userCount(UserVo userVo);

  List<UserVo> userList();

  UserVo userInfo(UserVo userVo);

  void userInsert(UserVo userVo);

  void userUpdate(UserVo userVo);

  void userRefTokenUpdate(UserVo userVo);

  void userDelete(UserVo userVo);

  UserVo userRefreshTokenInfo(UserVo userVo);

  boolean socialSignup(JsonObject param);
}
