package com.diden.demo.config;

import com.diden.demo.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  private final UserService userService;
  private final TokenAdepterInterface tokenAdepterInterface;
  private final ExceptionHandlerFilter exceptionHandlerFilter;

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .csrf()
        .disable()
        .addFilter(new JwtAuthenticationFilter(userService))
        .addFilterAfter(
            new JwtAuthorizationFilter(tokenAdepterInterface), JwtAuthenticationFilter.class)
        .formLogin()
        .disable()
        .httpBasic()
        .disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(exceptionHandlerFilter, JwtAuthorizationFilter.class)
        .build();
  }
}
