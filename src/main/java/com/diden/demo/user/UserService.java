package com.diden.demo.user;

import java.util.List;

public interface UserService {
  boolean existsUserEmail(final String userEmail);

  boolean emailDuplicateCheck(final String userEmail);

  int userCheck(UserVo userVo);

  int userCount(UserVo userVo);

  List<UserVo> userList();

  UserVo userInfo(UserVo userVo);

  UserVo findEmailByUser(final String userEmail);

  void userInsert(UserVo userVo);

  void userUpdate(UserVo userVo);

  int userTokenUpdate(UserVo userVo);

  void userDelete(UserVo userVo);

  UserVo userRefreshTokenInfo(UserVo userVo);

  String findByLoginType(String authorization);

  List<UserVo> pageable(Integer rowStartNumber, Integer rowLimitNumber);
}
