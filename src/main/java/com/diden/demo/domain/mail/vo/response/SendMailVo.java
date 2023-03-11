package com.diden.demo.domain.mail.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SendMailVo {
    private String to = "";
    private String from = "";
    private String subject = "";
    private String content = "";

    @Builder
    public SendMailVo(String to, String from, String subject, String content) {
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.content = content;
    }
}
