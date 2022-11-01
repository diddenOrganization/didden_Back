package com.diden.demo;

import com.diden.demo.config.adepter.*;
import com.diden.demo.social.AppleSocialAdepterImpl;
import com.diden.demo.social.KakaoSocialAdepterImpl;
import com.diden.demo.social.NaverSocialAdepterImpl;
import com.diden.demo.social.SocialAdepter;
import com.diden.demo.utils.AccountTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {
  private final List<LoginLogoutAdepter> loginLogoutAdepterList = new ArrayList<>();
  private final Map<AccountTypeEnum, SocialAdepter> socialAdepterMap = new HashMap<>();
  private final Map<AccountTypeEnum, LoginLogoutAdepter> loginLogoutAdepterMap = new HashMap<>();

  @Bean
  public List<LoginLogoutAdepter> loginAdepterList() {
    this.loginLogoutAdepterList.add(new LoginLogoutApple());
    this.loginLogoutAdepterList.add(new LoginLogoutDefault());
    this.loginLogoutAdepterList.add(new LoginLogoutKakao());
    this.loginLogoutAdepterList.add(new LoginLogoutNaver());
    return loginLogoutAdepterList;
  }

  @Bean
  public Map<AccountTypeEnum, LoginLogoutAdepter> loginLogoutAdepterMap() {
    this.loginLogoutAdepterMap.put(AccountTypeEnum.NAVER, new LoginLogoutNaver());
    this.loginLogoutAdepterMap.put(AccountTypeEnum.KAKAO, new LoginLogoutKakao());
    this.loginLogoutAdepterMap.put(AccountTypeEnum.APPLE, new LoginLogoutApple());
    this.loginLogoutAdepterMap.put(AccountTypeEnum.DEFAULT, new LoginLogoutDefault());
    return loginLogoutAdepterMap;
  }

  @Bean
  public Map<AccountTypeEnum, SocialAdepter> socialAdepterMap() {
    this.socialAdepterMap.put(AccountTypeEnum.NAVER, new NaverSocialAdepterImpl());
    this.socialAdepterMap.put(AccountTypeEnum.KAKAO, new KakaoSocialAdepterImpl());
    this.socialAdepterMap.put(AccountTypeEnum.APPLE, new AppleSocialAdepterImpl());
    return socialAdepterMap;
  }
}
