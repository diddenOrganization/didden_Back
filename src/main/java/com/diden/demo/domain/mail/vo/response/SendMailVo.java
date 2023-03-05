package com.diden.demo.domain.mail.vo.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
@Getter
@Setter
public class SendMailVo {
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
}
