package com.diden.demo.api.mail.controller;

import com.diden.demo.api.mail.dto.request.CertificationDtoRequest;
import com.diden.demo.api.mail.dto.request.SendMailDtoRequest;
import com.diden.demo.common.error.exception.BadRequestException;
import com.diden.demo.common.response.HttpResponse;
import com.diden.demo.domain.mail.service.MailApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;

@Tag(name = "메일 API", description = "메일 관련된 API 목록입니다.")
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/mail", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MailApiController {
  private final MailApiService mailApiService;


  @Operation(summary = "메일 발송", description = "인증코드 메일 발송 기능입니다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "성공")
  })
  @PostMapping(value = "/send")
  public HttpResponse<Void> Mail(
      HttpServletRequest request,
      @RequestBody(required = false) @Valid @NotNull(message = "메일 발송 정보가 존재하지 않습니다.")
          final SendMailDtoRequest sendMailDtoRequest)
      throws MessagingException, UnsupportedEncodingException {
    final HttpSession session = request.getSession();

    mailApiService.authEmail(session, sendMailDtoRequest.getTo(), sendMailDtoRequest.getFrom());
    return HttpResponse.toResponse(HttpStatus.OK, "메일이 발송됐습니다.");
  }

  /**
   * 메일 발송
   *
   * @param "/sendmail"
   * @param
   */
  @Operation(summary = "이메일 인증 코드 검증", description = "이메일 본인인증 코드 검증 기능입니다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "성공")
  })
  @PostMapping(value = "/certification")
  public HttpResponse<Void> Certification(
      HttpServletRequest request,
      @RequestBody(required = false) @Valid @NotNull(message = "인증코드 정보가 존재하지 않습니다.")
          CertificationDtoRequest certificationDtoRequest)
      throws MessagingException, UnsupportedEncodingException {

    if (!StringUtils.isNumeric(certificationDtoRequest.getCode())) {
      throw new BadRequestException("인증코드는 숫자만 입력할 수 있습니다.");
    }

    final HttpSession session = request.getSession();

    if (mailApiService.emailCertification(session, certificationDtoRequest.getUserEmail(), Integer.parseInt(certificationDtoRequest.getCode()))) {
      return HttpResponse.toResponse(HttpStatus.ACCEPTED, "인증이 완료됐습니다.");
    } else {
      return HttpResponse.toResponse(HttpStatus.BAD_REQUEST, "인증코드가 일치하지 않습니다.");
    }
  }
}
