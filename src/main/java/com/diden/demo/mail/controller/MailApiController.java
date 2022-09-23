 package com.diden.demo.mail.controller;

import com.diden.demo.mail.service.MailApiService;

import com.diden.demo.mail.vo.SendMailVo;
import com.diden.demo.mail.vo.SertificationVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

import lombok.extern.slf4j.Slf4j;

 @Slf4j
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
    @PostMapping(value="/mail")
    public String Mail(@RequestBody(required = false) SendMailVo sendMailVo) throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");

            mimeMessageHelper.setFrom(sendMailVo.getFrom(), sendMailVo.getFrom());
            mimeMessageHelper.setTo(sendMailVo.getTo());
            mimeMessageHelper.setSubject(sendMailVo.getSubject());
            mimeMessageHelper.setText(sendMailVo.getContent(), true);

            javaMailSender.send(message);
            log.info("Email Send to : "+sendMailVo.getTo()+" from :"+sendMailVo.getFrom()+" subject :"+sendMailVo.getSubject()+" content :"+sendMailVo.getContent());
        }catch (MessagingException e){
            log.info("[MessagingException] = ", e.getMessage());
        }catch (MailSendException e){
            log.info("[MailSendException] = ", e.getMessage());
        }

        return "";
    }

    /**
     * 회원가입 인증번호 메일 발송
     *
     * @param "/sendmail"
     * @param
     */
    @PostMapping(value="/sendmail")
    public String Mail(HttpServletRequest request, @RequestBody(required = false) SendMailVo sendMailVo) throws MessagingException, UnsupportedEncodingException {
        HttpSession session = request.getSession();

        mailApiService.authEmail(session, sendMailVo.getTo());

        return "";
    }

    /**
     * 회원가입 인증번호 확인
     *
     * @param "/sendmail"
     * @param
     */
    @PostMapping(value="/certification")
    public boolean Certification(HttpServletRequest request, @RequestBody(required = false) SertificationVo sertificationVo) throws MessagingException, UnsupportedEncodingException {
        HttpSession session = request.getSession();

        boolean result = mailApiService.emailCertification(session, sertificationVo.getUserEmail(), Integer.parseInt(sertificationVo.getCode()));

        return result;
    }
}