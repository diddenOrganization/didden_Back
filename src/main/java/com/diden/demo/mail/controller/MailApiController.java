package com.diden.demo.mail.controller;

import com.diden.demo.mail.service.MailApiService;
import com.diden.demo.utils.HttpResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import java.io.UnsupportedEncodingException;

@Slf4j
@Validated
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MailApiController {

  private final MailApiService mailApiService;

  private final JavaMailSender javaMailSender;

  /**
   * 회원가입 인증번호 메일 발송
   *
   * @param "/sendmail"
   * @param
   */
  @GetMapping(value = "/sendmail")
  public String SendMail(
      @RequestParam String to,
      @RequestParam String from,
      @RequestParam String subject,
      @RequestParam String content)
      throws MessagingException, UnsupportedEncodingException {

    MimeMessage message = javaMailSender.createMimeMessage();

    try {

      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");

      mimeMessageHelper.setFrom(from, from);
      mimeMessageHelper.setTo(to);
      mimeMessageHelper.setSubject(subject);
      mimeMessageHelper.setText(content, true);

      javaMailSender.send(message);
      log.info(
          "Email Send to : "
              + to
              + " from :"
              + from
              + " subject :"
              + subject
              + " content :"
              + content);
    } catch (MessagingException e) {
      log.info("[MessagingException] = ", e.getMessage());
    } catch (MailSendException e) {
      log.info("[MailSendException] = ", e.getMessage());
    }

    return "";
  }

  /**
   * 회원가입 인증번호 인증
   *
   * @param "/sendmail"
   * @param
   */
  @GetMapping(value = "/mail")
  public HttpResponse<Void> Mail(
      HttpServletRequest request,
      @RequestParam @NotBlank(message = "이메일이 존재하지 않습니다.") final String to,
      @RequestParam @NotBlank(message = "발신자 이메일이 존재하지 않습니다. 관리자에게 문의하세요.") final String from)
      throws MessagingException, UnsupportedEncodingException {

    HttpSession session = request.getSession();

    mailApiService.authEmail(session, to, from);

    return HttpResponse.toResponse(HttpStatus.OK, "메일이 발송됐습니다.");
  }

  /**
   * 메일 발송
   *
   * @param "/sendmail"
   * @param
   */
  @GetMapping(value = "/certification")
  public HttpResponse<Void> Certification(
      HttpServletRequest request,
      @RequestParam @NotBlank(message = "이메일이 존재하지 않습니다.") String userEmail,
      @RequestParam @NotBlank(message = "인증코드가 존재하지 않습니다.") String code)
      throws MessagingException, UnsupportedEncodingException {

    HttpSession session = request.getSession();

    if(mailApiService.emailCertification(session, userEmail, Integer.parseInt(code))) {
      return HttpResponse.toResponse(HttpStatus.ACCEPTED, "인증이 완료됐습니다.");
    } else {
      return HttpResponse.toResponse(HttpStatus.BAD_REQUEST, "인증코드가 일치하지 않습니다.");
    }

  }
}
