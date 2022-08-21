package com.diden.demo.user;

import com.diden.demo.error.exception.BadRequestException;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;
  private final List<SocialAdepter> socialAdepterList;

  public boolean existsUserEmail(final String userEmail) {
    if(StringUtils.isBlank(userEmail)){
      throw new BadRequestException("이메일을 잘못입력했거나 존재하지 않습니다.");
    }

    return userMapper.existsUserEmail(userEmail);
  }

  public boolean emailDuplicateCheck(final String userEmail){
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
      throw new RuntimeException("회원가입 실패");
    }

  }

  @Override
  public void userUpdate(UserVo userVo) {
    try {
      userMapper.userUpdate(userVo);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("수정 실패");
    }
  }

  @Override
  public void userRefTokenUpdate(UserVo userVo) {
    userMapper.userRefTokenUpdate(userVo);
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
  public boolean socialSignup(JsonObject param) {
    final String loginType = param.get("login_type").getAsString();
    final JsonObject loginParam = param.getAsJsonObject("loginParam");
    final SocialAdepter adepter;

    for (SocialAdepter socialAdepter : this.socialAdepterList) {
      if (socialAdepter.supports(loginType)) {
        adepter = socialAdepter;
        final UserVo userVo = adepter.process(loginParam);

        if (userMapper.userCheck(userVo) > 0) userMapper.userUpdate(userVo);
        else userMapper.userInsert(userVo);
        return true;
      }
    }

    log.info("소셜 로그인 실패\n로그인 타입에 맞는 어댑터가 존재하지 않습니다.");
    return false;
  }
}
