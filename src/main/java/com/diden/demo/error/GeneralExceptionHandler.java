package com.diden.demo.error;

import com.diden.demo.error.exception.BadRequestException;
import com.diden.demo.error.exception.DataNotProcessExceptions;
import com.diden.demo.error.exception.TokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({DataNotProcessExceptions.class})
  public ExceptionVo dataNotProcessExceptions(final DataNotProcessExceptions e) {
    e.printStackTrace();
    return ExceptionVo.builder()
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .message(e.getMessage())
        .build();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({NullPointerException.class})
  public ExceptionVo nullPointer(final NullPointerException e) {
    e.printStackTrace();
    return ExceptionVo.builder().status(HttpStatus.BAD_REQUEST).message(e.getMessage()).build();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({
    BadRequestException.class,
    TokenException.class,
    IllegalArgumentException.class,
  })
  public ExceptionVo badRequest(final RuntimeException e) {
    e.printStackTrace();
    return ExceptionVo.builder().status(HttpStatus.BAD_REQUEST).message(e.getMessage()).build();
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

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ExceptionVo processValidationError(final MethodArgumentNotValidException e) {
    log.error(e.toString());
    e.printStackTrace();

    return ExceptionVo.builder()
        .message(
            e.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(FieldError::getDefaultMessage)
                .stream()
                .collect(Collectors.joining()))
        .status(HttpStatus.BAD_REQUEST)
        .build();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({HttpMessageNotReadableException.class})
  public ExceptionVo notReadable(final HttpMessageNotReadableException e) {
    log.error(e.toString());
    e.printStackTrace();

    return ExceptionVo.builder()
        .message(e.getLocalizedMessage())
        .status(HttpStatus.BAD_REQUEST)
        .build();
  }
}
