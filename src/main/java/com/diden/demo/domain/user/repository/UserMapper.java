package com.diden.demo.domain.user.repository;

import com.diden.demo.domain.user.vo.request.UserVoRequest;
import com.diden.demo.domain.user.vo.response.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
  boolean emailDuplicateCheck(final String userEmail);

  boolean existsUserEmail(@Param("userEmail") final String userEmail);

  int userCount(UserVoRequest userVoRequest);

  int userCheck(UserVoRequest userVoRequest);

  List<UserVo> userList();

  UserVo userInfo(UserVoRequest userVoRequest);

  UserVo findEmailByUser(@Param("userEmail") String userEmail);

  void userInsert(UserVoRequest userVoRequest);

  void userUpdate(UserVoRequest userVoRequest);

  int userTokenUpdate(UserVoRequest userVoRequest);

  void userDelete(UserVoRequest userVoRequest);

  UserVo userRefreshTokenInfo(UserVoRequest userVoRequest);

  String findByLoginType(@Param("authorization") final String authorization);

  List<UserVo> pageable(
      @Param("rowStartNumber") final Integer rowStartNumber,
      @Param("rowLimitNumber") final Integer rowLimitNumber);

  int userEmailAndLoginTypeFindCount(
      @Param("userEmail") final String userEmail, @Param("loginType") final String loginType);
}
