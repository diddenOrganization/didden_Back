package com.diden.demo.domain.user.service;

import com.diden.demo.domain.user.vo.request.UserVoRequest;
import com.diden.demo.domain.user.vo.response.UserVo;

import java.util.List;

public interface UserService {
  boolean existsUserEmail(final String userEmail);

  boolean emailDuplicateCheck(final String userEmail);

  int userCheck(UserVoRequest userVoRequest);

  int userCount(UserVoRequest userVoRequest);

  List<UserVo> userList();

  UserVo userInfo(UserVoRequest userVoRequest);

  UserVo findEmailByUser(final String userEmail);

  void userInsert(UserVoRequest userVoRequest);

  void userUpdate(UserVoRequest userVoRequest);

  int userTokenUpdate(UserVoRequest userVoRequest);

  void userDelete(UserVoRequest userVoRequest);

  UserVo userRefreshTokenInfo(UserVoRequest userVoRequest);

  String findByLoginType(String authorization);

  List<UserVo> pageable(Integer rowStartNumber, Integer rowLimitNumber);
}
