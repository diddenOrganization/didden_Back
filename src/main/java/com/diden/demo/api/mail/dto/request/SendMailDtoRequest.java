package com.diden.demo.api.mail.dto.request;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@ToString
public class SendMailDtoRequest {
    @SerializedName("to")
    @NotBlank(message = "이메일이 존재하지 않습니다.")
    private String to = "";
    @SerializedName("from")
    @NotBlank(message = "발신자 이메일이 존재하지 않습니다. 관리자에게 문의하세요.")
    private String from = "";
    @SerializedName("subject")
    private String subject = "";
    @SerializedName("content")
    private String content = "";

    @Builder
    public SendMailDtoRequest(String to, String from, String subject, String content) {
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.content = content;
    }
}
