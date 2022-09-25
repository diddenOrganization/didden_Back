package com.diden.demo;

import com.diden.demo.config.adepter.*;
import com.diden.demo.user.AppleSocialAdepterImpl;
import com.diden.demo.user.KakaoSocialAdepterImpl;
import com.diden.demo.user.NaverSocialAdepterImpl;
import com.diden.demo.user.SocialAdepter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {
  private final List<LoginLogoutAdepter> loginLogoutAdepterList = new ArrayList<>();
  private final List<SocialAdepter> socialAdepterList = new ArrayList<>();

  @Bean
  public List<LoginLogoutAdepter> loginAdepterList() {
    this.loginLogoutAdepterList.add(new LoginLogoutApple());
    this.loginLogoutAdepterList.add(new LoginLogoutDefault());
    this.loginLogoutAdepterList.add(new LoginLogoutKakao());
    this.loginLogoutAdepterList.add(new LoginLogoutNaver());
    return loginLogoutAdepterList;
  }

  @Bean
  public List<SocialAdepter> socialAdepterList() {
    this.socialAdepterList.add(new AppleSocialAdepterImpl());
    this.socialAdepterList.add(new KakaoSocialAdepterImpl());
    this.socialAdepterList.add(new NaverSocialAdepterImpl());
    return socialAdepterList;
  }
}
