package com.diden.demo.common.security.handler;

import com.diden.demo.common.utils.LazyHolderObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.diden.demo.common.response.HttpResponse.toResponse;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        responseHandlers(request, response, authentication);
    }

    private void responseHandlers(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        final HttpStatus ok = HttpStatus.OK;

        response.setStatus(ok.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        //response.getWriter().write(LazyHolderObject.getGson().toJson(toResponse(ok, "로그아웃 성공.")));
    }
}
