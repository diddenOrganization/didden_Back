package com.diden.demo.social;

import com.diden.demo.user.UserMapper;
import com.diden.demo.user.UserVo;
import com.diden.demo.utils.AccountTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SocialServiceImpl implements SocialService {
  private final UserMapper userMapper;
  private final Map<AccountTypeEnum, SocialAdepter> socialAdepterMap;

  @Override
  public void socialLoginAndSignupProcess(AccountTypeEnum loginType, String accessToken)
      throws IOException {
    final SocialAdepter adepterHandler = socialAdepterMap.get(loginType);
    final UserVo userVo = adepterHandler.process(accessToken);

    if (userMapper.userEmailAndLoginTypeFindCount(userVo.getUserEmail(), loginType.getAccountType())
        > 0) {
      userMapper.userTokenUpdate(userVo);
    } else {
      userMapper.userInsert(userVo);
    }
  }
}
