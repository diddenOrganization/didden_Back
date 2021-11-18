package com.diden.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    private static final String SESSION_ID = "sessionId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // String requestURI = request.getRequestURI();

        // log.info("인증 체크 인터셉터 실행 {}", requestURI);

        // HttpSession session = request.getSession();

        // if (session == null || session.getAttribute(SESSION_ID) == null) {
        // log.info("미인증 사용자 요청");
        // JsonObject errorResult = new JsonObject();
        // errorResult.addProperty("result", false);
        // errorResult.addProperty("error", "로그인을 해주세요.");
        // new ResponseEntity<>(errorResult.toString(), HttpStatus.OK);
        // // 로그인으로 redirect
        // // response.setDateHeader(name, date);
        // // response.sendRedirect("/user/login");

        // return false;
        // }

        return true;
    }
}
