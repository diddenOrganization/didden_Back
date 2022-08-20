package com.diden.demo.error;

import com.diden.demo.error.exception.BadRequestException;
import com.diden.demo.error.exception.TokenException;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GeneralExceptionHandler {
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({NullPointerException.class})
  public ExceptionVo nullPointer(final NullPointerException e) {
    e.printStackTrace();
    return ExceptionVo.builder().message(e.getMessage()).build();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({BadRequestException.class, TokenException.class})
  public ExceptionVo badRequest(final RuntimeException e) {
    e.printStackTrace();
    return ExceptionVo.builder().message(e.getMessage()).build();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({ConstraintViolationException.class})
  public ExceptionVo constraintViolation(final ConstraintViolationException e) {
    log.error(e.toString());
    e.printStackTrace();
    return ExceptionVo.builder()
            .message(
                    e.getConstraintViolations().stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.joining()))
            .status(HttpStatus.BAD_REQUEST)
            .build();
  }

  @Getter
  private static class ExceptionVo<T> {
    private final HttpStatus status;
    private final String message;
    private final T data;

    @Builder
    public ExceptionVo(HttpStatus status, String message, T data) {
      this.status = status;
      this.message = message;
      this.data = data;
    }
  }
}
