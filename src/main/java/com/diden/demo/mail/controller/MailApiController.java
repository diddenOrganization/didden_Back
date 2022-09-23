package com.diden.demo.mail.controller;

import com.diden.demo.mail.service.MailApiService;
import com.diden.demo.mail.vo.SendMailVo;
import com.diden.demo.mail.vo.SertificationVo;
import com.diden.demo.utils.HttpResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public String SendMail(@RequestBody(required = false) SendMailVo sendMailVo)
            throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");

            mimeMessageHelper.setFrom(sendMailVo.getFrom(), sendMailVo.getFrom());
            mimeMessageHelper.setTo(sendMailVo.getTo());
            mimeMessageHelper.setSubject(sendMailVo.getSubject());
            mimeMessageHelper.setText(sendMailVo.getContent(), true);

            javaMailSender.send(message);
            log.info(
                    "Email Send to : "
                            + sendMailVo.getTo()
                            + " from :"
                            + sendMailVo.getFrom()
                            + " subject :"
                            + sendMailVo.getSubject()
                            + " content :"
                            + sendMailVo.getContent());
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
    @PostMapping(value = "/mail")
    public HttpResponse<Void> Mail(
            HttpServletRequest request,
            @RequestBody(required = false) SendMailVo sendMailVo)
            throws MessagingException, UnsupportedEncodingException {

        HttpSession session = request.getSession();

        mailApiService.authEmail(session, sendMailVo.getTo(), sendMailVo.getFrom());

        return HttpResponse.toResponse(HttpStatus.OK, "메일이 발송됐습니다.");
    }

    /**
     * 메일 발송
     *
     * @param "/sendmail"
     * @param
     */
    @PostMapping(value = "/certification")
    public HttpResponse<Void> Certification(
            HttpServletRequest request,
            @RequestBody(required = false) SertificationVo sertificationVo)
            throws MessagingException, UnsupportedEncodingException {

        HttpSession session = request.getSession();

        if(mailApiService.emailCertification(session, sertificationVo.getUserEmail(), Integer.parseInt(sertificationVo.getCode()))) {
            return HttpResponse.toResponse(HttpStatus.ACCEPTED, "인증이 완료됐습니다.");
        } else {
            return HttpResponse.toResponse(HttpStatus.BAD_REQUEST, "인증코드가 일치하지 않습니다.");
        }

    }
}
