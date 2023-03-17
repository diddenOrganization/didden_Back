package com.diden.demo.common.config;

import com.diden.demo.common.response.HttpResponse;
import com.diden.demo.common.security.filter.ExceptionHandlerFilter;
import com.diden.demo.common.security.filter.JwtAuthenticationFilter;
import com.diden.demo.common.security.filter.JwtAuthorizationFilter;
import com.diden.demo.common.security.handler.CustomAccessDeniedHandler;
import com.diden.demo.common.security.handler.CustomAuthenticationEntryPoint;
import com.diden.demo.common.security.handler.CustomLogoutHandler;
import com.diden.demo.common.security.handler.CustomLogoutSuccessHandler;
import com.diden.demo.common.utils.LazyHolderObject;
import com.diden.demo.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  private final UserService userService;
  private final ExceptionHandlerFilter exceptionHandlerFilter;
  private final JwtAuthorizationFilter jwtAuthorizationFilter;
  private final CustomLogoutHandler customLogoutHandler;
  private final CustomAccessDeniedHandler customAccessDeniedHandler;
  private final CustomLogoutSuccessHandler customLogoutSuccessHandler;
  private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    return httpSecurity
        .csrf().disable()
        .formLogin().disable()
        .httpBasic().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests(
            request ->
                request
                    .antMatchers("/info/**", "/img/**", "/main/**", "/mail/**", "/user/email-check")
                    .permitAll()
                    .antMatchers(HttpMethod.POST, "/user", "/user/social")
                    .permitAll()
                    .antMatchers("/social/**")
                    .permitAll()
                    .anyRequest()
                    .permitAll())
        .addFilter(new JwtAuthenticationFilter(userService))
        .addFilterBefore(exceptionHandlerFilter, WebAsyncManagerIntegrationFilter.class)
        .addFilterAfter(jwtAuthorizationFilter, JwtAuthenticationFilter.class)
        .logout()
        .logoutUrl("/logout")
        .addLogoutHandler(customLogoutHandler)
        .logoutSuccessHandler(customLogoutSuccessHandler)
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(customAuthenticationEntryPoint)
        .accessDeniedHandler(customAccessDeniedHandler)
        .and()
        .build();

    /*
          Security filter chain: [
    ExceptionHandlerFilter
    WebAsyncManagerIntegrationFilter
    SecurityContextPersistenceFilter
    HeaderWriterFilter
    LogoutFilter
    JwtAuthenticationFilter
    JwtAuthorizationFilter
    RequestCacheAwareFilter
    SecurityContextHolderAwareRequestFilter
    AnonymousAuthenticationFilter
    SessionManagementFilter
    ExceptionTranslationFilter
    FilterSecurityInterceptor
      ]*/
  }

  private void responseHandlers(HttpStatus status, HttpServletResponse response, String message)
      throws IOException {
    response.setStatus(status.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    HttpResponse<Void> httpResponse =
        HttpResponse.<Void>builder().status(status).message(message).build();
    //response.getWriter().write(LazyHolderObject.getGson().toJson(httpResponse));
  }
}
