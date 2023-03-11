package com.diden.demo.domain.user.service;

import com.diden.demo.common.error.exception.BadRequestException;
import com.diden.demo.domain.user.repository.UserMapper;
import com.diden.demo.domain.user.vo.request.UserVoRequest;
import com.diden.demo.domain.user.vo.response.UserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;

  public boolean existsUserEmail(final String userEmail) {
    if (StringUtils.isBlank(userEmail)) {
      throw new BadRequestException("이메일이 존재하지 않습니다.");
    }

    return userMapper.existsUserEmail(userEmail);
  }

  public boolean emailDuplicateCheck(final String userEmail) {
    return userMapper.emailDuplicateCheck(userEmail);
  }

  public int userCheck(UserVoRequest userVoRequest) {
    return userMapper.userCheck(userVoRequest);
  }

  @Override
  public int userCount(UserVoRequest userVoRequest) {
    return userMapper.userCount(userVoRequest);
  }

  @Override
  public List<UserVo> userList() {
    return userMapper.userList();
  }

  @Override
  public UserVo userInfo(UserVoRequest userVoRequest) {
    return userMapper.userInfo(userVoRequest);
  }

  @Override
  public UserVo findEmailByUser(final String userEmail) {
    return userMapper.findEmailByUser(userEmail);
  }

  @Override
  public void userInsert(UserVoRequest userVoRequest) {
    try {
      userMapper.userInsert(userVoRequest);
    } catch (Exception e) {
      e.printStackTrace();
      throw new IllegalArgumentException("회원가입 실패");
    }
  }

  @Override
  public void userUpdate(UserVoRequest userVoRequest) {
    try {
      userMapper.userUpdate(userVoRequest);
    } catch (Exception e) {
      e.printStackTrace();
      throw new IllegalArgumentException("수정 실패");
    }
  }

  @Override
  public int userTokenUpdate(UserVoRequest userVoRequest) {
    return userMapper.userTokenUpdate(userVoRequest);
  }

  @Override
  public void userDelete(UserVoRequest userVoRequest) {
    userMapper.userDelete(userVoRequest);
  }

  @Override
  public UserVo userRefreshTokenInfo(UserVoRequest userVoRequest) {
    return userMapper.userRefreshTokenInfo(userVoRequest);
  }

  @Override
  public String findByLoginType(final String authorization) {
    return userMapper.findByLoginType(authorization);
  }

  @Override
  public List<UserVo> pageable(final Integer rowStartNumber, final Integer rowLimitNumber) {
    final List<UserVo> pageableUserList = userMapper.pageable(rowStartNumber, rowLimitNumber);
    if (pageableUserList.size() > rowLimitNumber) {
      pageableUserList.remove(rowLimitNumber.intValue());
    }

    return pageableUserList;
  }
}
