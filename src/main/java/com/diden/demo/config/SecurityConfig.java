package com.diden.demo.config;

import com.diden.demo.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

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
        .authorizeRequests()
        .antMatchers("/info/**", "/img/**", "/main/**").permitAll()
        .antMatchers("/mail/**").permitAll()
        .antMatchers("/user/email-check").permitAll()
        .antMatchers(HttpMethod.POST, "/user", "/user/social").permitAll()
        .anyRequest().authenticated()
        .and()
        .addFilter(new JwtAuthenticationFilter(userService))
        .addFilterAfter(exceptionHandlerFilter, FilterSecurityInterceptor.class)
        .addFilterAfter(
            new JwtAuthorizationFilter(tokenAdepterInterface), FilterSecurityInterceptor.class)
        .formLogin()
        .disable()
        .httpBasic()
        .disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .build();

    /*
        Security filter chain: [
      WebAsyncManagerIntegrationFilter
      SecurityContextPersistenceFilter
      HeaderWriterFilter
      LogoutFilter
      JwtAuthenticationFilter
      RequestCacheAwareFilter
      SecurityContextHolderAwareRequestFilter
      AnonymousAuthenticationFilter
      SessionManagementFilter
      ExceptionTranslationFilter
      FilterSecurityInterceptor
      ExceptionHandlerFilter
      JwtAuthorizationFilter
    ]*/
  }
}
