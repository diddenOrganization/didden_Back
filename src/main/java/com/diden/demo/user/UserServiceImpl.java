package com.diden.demo.user;

import com.diden.demo.error.exception.BadRequestException;
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
  private final List<SocialAdepter> socialAdepterList;

  public boolean existsUserEmail(final String userEmail) {
    if (StringUtils.isBlank(userEmail)) {
      throw new BadRequestException("이메일이 존재하지 않습니다.");
    }

    return userMapper.existsUserEmail(userEmail);
  }

  public boolean emailDuplicateCheck(final String userEmail) {
    return userMapper.emailDuplicateCheck(userEmail);
  }

  public int userCheck(UserVo userVo) {
    return userMapper.userCheck(userVo);
  }

  @Override
  public int userCount(UserVo userVo) {
    return userMapper.userCount(userVo);
  }

  @Override
  public List<UserVo> userList() {
    return userMapper.userList();
  }

  @Override
  public UserVo userInfo(UserVo userVo) {
    return userMapper.userInfo(userVo);
  }

  @Override
  public UserVo findEmailByUser(final String userEmail) {
    return userMapper.findEmailByUser(userEmail);
  }

  @Override
  public void userInsert(UserVo userVo) {
    try {
      userMapper.userInsert(userVo);
    } catch (Exception e) {
      e.printStackTrace();
      throw new IllegalArgumentException("회원가입 실패");
    }
  }

  @Override
  public void userUpdate(UserVo userVo) {
    try {
      userMapper.userUpdate(userVo);
    } catch (Exception e) {
      e.printStackTrace();
      throw new IllegalArgumentException("수정 실패");
    }
  }

  @Override
  public int userTokenUpdate(UserVo userVo) {
    return userMapper.userTokenUpdate(userVo);
  }

  @Override
  public void userDelete(UserVo userVo) {
    userMapper.userDelete(userVo);
  }

  @Override
  public UserVo userRefreshTokenInfo(UserVo userVo) {
    return userMapper.userRefreshTokenInfo(userVo);
  }

  @Override
  public void socialSignup(final String loginType, final String accessToken) {

    for (SocialAdepter socialAdepter : this.socialAdepterList) {
      if (socialAdepter.supports(loginType)) {
        final UserVo socialUserVo = socialAdepter.process(accessToken);
        userInsert(socialUserVo);
        return;
      }
    }

    throw new IllegalArgumentException("소셜 회원가입 실패");
  }

  @Override
  public String findByLoginType(final String authorization) {
    return userMapper.findByLoginType(authorization);
  }

  @Override
  public List<UserVo> pageable(Integer rowStartNumber) {
    return userMapper.pageable(rowStartNumber);
  }

}
