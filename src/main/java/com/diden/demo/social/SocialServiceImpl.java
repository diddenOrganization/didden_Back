package com.diden.demo.social;

import com.diden.demo.user.UserMapper;
import com.diden.demo.user.UserVo;
import com.diden.demo.utils.AccountTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SocialServiceImpl implements SocialService {
  private final UserMapper userMapper;
  private final Map<AccountTypeEnum, SocialAdepter> socialAdepterMap;

  @Override
  public void socialAccessTokenUpdate(final String accessToken, final String loginType) {
    final SocialAdepter adepterHandler =
        socialAdepterMap.get(AccountTypeEnum.getAccountEnumType(loginType));
    final UserVo userVo = adepterHandler.process(accessToken);

    final UserVo updateUserVo =
        UserVo.builder().userEmail(userVo.getUserEmail()).userAccessToken(accessToken).build();

    userMapper.userTokenUpdate(updateUserVo);
  }

  @Override
  public void socialSignup(final String loginType, final String accessToken) {
    final SocialAdepter adepterHandler =
        socialAdepterMap.get(AccountTypeEnum.getAccountEnumType(loginType));
    final UserVo userVo = adepterHandler.process(accessToken);
    userMapper.userInsert(userVo);
  }
}
