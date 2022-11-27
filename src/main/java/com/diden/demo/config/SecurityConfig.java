package com.diden.demo.config;

import com.diden.demo.user.UserService;
import com.diden.demo.utils.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  private final UserService userService;
  private final TokenAdepterInterface tokenAdepterInterface;
  private final ExceptionHandlerFilter exceptionHandlerFilter;
  private final JwtLogoutHandlerFilter jwtLogoutHandlerFilter;
  private final AuthenticationEntryPoint customAuthenticationEntryPoint;

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .csrf()
        .disable()
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
        .addFilterAfter(
            new JwtAuthorizationFilter(tokenAdepterInterface), JwtAuthenticationFilter.class)
        .formLogin()
        .disable()
        .httpBasic()
        .disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .logout(
            logout ->
                logout
                    .logoutUrl("/logout")
                    .addLogoutHandler(jwtLogoutHandlerFilter)
                    .logoutSuccessHandler(
                        (request, response, authentication) -> {
                          this.responseHandlers(HttpStatus.OK, response, "로그아웃이 되었습니다.");
                        }))
        .exceptionHandling()
        .authenticationEntryPoint(customAuthenticationEntryPoint)
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
    response.getWriter().write(LazyHolderObject.getGson().toJson(httpResponse));
  }
}
