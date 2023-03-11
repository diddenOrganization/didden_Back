package com.diden.demo.domain.user.service;

import com.diden.demo.domain.user.adepter.SocialAdepter;
import com.diden.demo.domain.user.enums.AccountTypeEnum;
import com.diden.demo.domain.user.repository.UserMapper;
import com.diden.demo.domain.user.vo.request.UserVoRequest;
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
    final UserVoRequest userVoRequest = adepterHandler.process(accessToken);

    if (userMapper.userEmailAndLoginTypeFindCount(userVoRequest.getUserEmail(), loginType.getAccountType()) > 0) {
      userMapper.userTokenUpdate(userVoRequest);
    } else {
      userMapper.userInsert(userVoRequest);
    }
  }
}
