package com.diden.demo;

import com.diden.demo.config.adepter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SpringConfig {
  private final List<LoginAdepter> loginAdepterList = new ArrayList<>();

  @Bean
  public List<LoginAdepter> loginAdepterList() {
    this.loginAdepterList.add(new LoginApple());
    this.loginAdepterList.add(new LoginDefault());
    this.loginAdepterList.add(new LoginKakao());
    this.loginAdepterList.add(new LoginNaver());
    return loginAdepterList;
  }
}
