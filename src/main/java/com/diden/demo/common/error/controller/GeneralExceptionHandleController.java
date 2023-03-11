package com.diden.demo.common.error.controller;

import com.diden.demo.common.error.dto.response.ExceptionDtoResponse;
import com.diden.demo.common.error.exception.*;
import com.diden.demo.domain.user.exception.SocialProcessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GeneralExceptionHandleController {

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({DataNotProcessExceptions.class, NullPointerException.class})
  public ExceptionDtoResponse dataNotProcessExceptions(final RuntimeException e) {
    log.error(e.toString());
    e.printStackTrace();
    return ExceptionDtoResponse.builder()
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .message(e.getMessage())
        .build();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({
    NotFoundDataException.class,
    BadRequestException.class,
    TokenException.class,
    IllegalArgumentException.class,
    HttpMessageNotReadableException.class,
    MissingServletRequestParameterException.class,
    SocialProcessException.class,
  })
  public ExceptionDtoResponse badRequest(final RuntimeException e) {
    log.error(e.toString());

    return ExceptionDtoResponse.builder().status(HttpStatus.BAD_REQUEST).message(e.getMessage()).build();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({ConstraintViolationException.class})
  public ExceptionDtoResponse constraintViolation(final ConstraintViolationException e) {
    log.error(e.toString());

    return ExceptionDtoResponse.builder()
        .message(
            e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .get())
        .status(HttpStatus.BAD_REQUEST)
        .build();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ExceptionDtoResponse processValidationError(final MethodArgumentNotValidException e) {
    log.error(e.toString());

    return ExceptionDtoResponse.builder()
        .message(
            e.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(FieldError::getDefaultMessage)
                .stream()
                .collect(Collectors.joining()))
        .status(HttpStatus.BAD_REQUEST)
        .build();
  }
}
