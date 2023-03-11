package com.diden.demo.api.mail.dto.request;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class CertificationDtoRequest {
    @SerializedName("userEmail")
    @NotBlank(message = "이메일이 존재하지 않습니다.")
    private String userEmail = "";
    @SerializedName("code")
    @NotBlank(message = "인증코드가 존재하지 않습니다.")
    private String code = "";

    @Builder
    public CertificationDtoRequest(String userEmail, String code) {
        this.userEmail = userEmail;
        this.code = code;
    }
}
