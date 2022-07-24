package com.diden.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Controller
public class MailApiController {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 메일 발송
     *
     * @param "/tour/api/info/detailCommon"
     * @param
     */
    @GetMapping(value="/sendmail", produces = "application/json;application/xml; charset=UTF-8")
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

}
