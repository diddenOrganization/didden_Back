 package com.diden.demo.mail.controller;

import com.diden.demo.mail.service.MailApiService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping(value="/sendmail")
    public String SendMail(@RequestParam String to, @RequestParam String from, @RequestParam String subject, @RequestParam String content) throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        try{

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");

            mimeMessageHelper.setFrom(from, from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content, true);

            javaMailSender.send(message);
            log.info("Email Send to : "+to+" from :"+from+" subject :"+subject+" content :"+content);
        }catch (MessagingException e){
            log.info("[MessagingException] = ", e.getMessage());
        }catch (MailSendException e){
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
    @GetMapping(value="/mail")
    public String Mail(HttpServletRequest request, @RequestParam String to, @RequestParam String from) throws MessagingException, UnsupportedEncodingException {

        HttpSession session = request.getSession();

        mailApiService.authEmail(session, to, from);

        return "";
    }

    /**
     * 메일 발송
     *
     * @param "/sendmail"
     * @param
     */
    @GetMapping(value="/certification")
    public boolean Certification(HttpServletRequest request, @RequestParam String userEmail, @RequestParam String code) throws MessagingException, UnsupportedEncodingException {

        HttpSession session = request.getSession();

        boolean result = mailApiService.emailCertification(session, userEmail, Integer.parseInt(code));

        return result;
    }

}
