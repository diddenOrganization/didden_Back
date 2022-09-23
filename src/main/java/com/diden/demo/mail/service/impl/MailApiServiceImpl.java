package com.diden.demo.mail.service.impl;

import com.diden.demo.mail.service.MailApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@Service
public class MailApiServiceImpl implements MailApiService {

  @Autowired private JavaMailSender javaMailSender;

  public void authEmail(HttpSession session, String to, String from) {
    int authKey = (int) (Math.random() * (99999 - 10000 + 1)) + 10000; // 인증번호 5자리 생성
    session.setAttribute("" + to, authKey);

    Timer timer = new Timer();
    TimerTask task =
        new TimerTask() {
          @Override
          public void run() {
            session.setAttribute("" + to, 999999999);
          }
        };
    timer.schedule(task, 60000);

    try {
      sendMail(to, from, authKey);
    } catch (MessagingException e) {
      log.info("[MessagingException] = {}", e.getMessage());
    } catch (UnsupportedEncodingException e) {
      log.info("[UnsupportedEncodingException] = {}", e.getMessage());
    }
  }

  public void sendMail(String to, String from, int authKey)
      throws MessagingException, UnsupportedEncodingException {
    MimeMessage message = javaMailSender.createMimeMessage();

    try {
      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");

      StringBuffer content = new StringBuffer();
      content.append("<div style='margin:100px;'>");
      content.append("<h1> 안녕하세요 adidden입니다. </h1>");
      content.append("<br>");
      content.append("<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요<p>");
      content.append("<br>");
      content.append("<p>감사합니다!<p>");
      content.append("<br>");
      content.append("<div align='center' style='border:1px solid black; font-family:verdana';>");
      content.append("<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>");
      content.append("CODE : <strong>");
      content.append(authKey + "</strong><div><br/> ");
      content.append("</div>");

      String subject = "adidden 회원가입 인증번호";

      mimeMessageHelper.setFrom(from, from);
      mimeMessageHelper.setTo(to);
      mimeMessageHelper.setSubject(subject);
      mimeMessageHelper.setText(content.toString(), true);

      javaMailSender.send(message);
      log.info(
          "Email Send to : "
              + to
              + " from :"
              + from
              + " subject :"
              + subject
              + " content :"
              + content.toString());
    } catch (MessagingException e) {
      log.info("[MessagingException] = {}", e.getMessage());
    } catch (MailSendException e) {
      log.info("[MailSendException] = {}", e.getMessage());
    }
  }

  public boolean emailCertification(HttpSession session, String userEmail, int code) {
    try {
      int generationCode = (int) session.getAttribute(userEmail);

      if (generationCode == code) {
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      throw e;
    }
  }
}
