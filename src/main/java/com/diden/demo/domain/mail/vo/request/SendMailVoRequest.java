package com.diden.demo.domain.mail.vo.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class SendMailVoRequest {
    private String to;
    private String from;
    private String subject;
    private String content;

    @Builder
    public SendMailVoRequest(String to, String from, String subject, String content) {
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.content = content;
    }
}
