package com.diden.demo.common.security.handler;

import com.diden.demo.common.error.dto.response.ExceptionDtoResponse;
import com.diden.demo.common.utils.LazyHolderObject;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final Gson gson = LazyHolderObject.getGson();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        final ExceptionDtoResponse<Object> exceptionDtoResponse =
                ExceptionDtoResponse.builder()
                        .status(HttpStatus.FORBIDDEN)
                        .message(accessDeniedException.getLocalizedMessage())
                        .build();

        accessDeniedException.printStackTrace();

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        //response.getWriter().write(gson.toJson(exceptionDtoResponse));
    }
}
