package com.diden.demo.api.mail.dto.request;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@ToString
public class SendMailDtoRequest {
    @Schema(title = "수신자 이메일", description = "")
    @SerializedName("to")
    @NotBlank(message = "이메일이 존재하지 않습니다.")
    private String to = "";
    @Schema(title = "발신자 이메일", description = "")
    @SerializedName("from")
    @NotBlank(message = "발신자 이메일이 존재하지 않습니다. 관리자에게 문의하세요.")
    private String from = "";
    @Schema(title = "", description = "")
    @SerializedName("subject")
    private String subject = "";
    @Schema(title = "내용", description = "")
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
