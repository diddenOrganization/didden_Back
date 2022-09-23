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
  private final List<LoginAdepter> loginAdepterList = new ArrayList<>();
  private final List<SocialAdepter> socialAdepterList = new ArrayList<>();

  @Bean
  public List<LoginAdepter> loginAdepterList() {
    this.loginAdepterList.add(new LoginApple());
    this.loginAdepterList.add(new LoginDefault());
    this.loginAdepterList.add(new LoginKakao());
    this.loginAdepterList.add(new LoginNaver());
    return loginAdepterList;
  }

  @Bean
  public List<SocialAdepter> socialAdepterList() {
    this.socialAdepterList.add(new AppleSocialAdepterImpl());
    this.socialAdepterList.add(new KakaoSocialAdepterImpl());
    this.socialAdepterList.add(new NaverSocialAdepterImpl());
    return socialAdepterList;
  }
}
