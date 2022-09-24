package com.diden.demo.mail.service;

import javax.servlet.http.HttpSession;

public interface MailApiService {
  void authEmail(HttpSession session, String to, String from);
  boolean emailCertification(HttpSession session, String userEmail, int code);
}
