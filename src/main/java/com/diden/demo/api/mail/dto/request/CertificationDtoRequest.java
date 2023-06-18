package com.diden.demo.api.mail.dto.request;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class CertificationDtoRequest {
    @Schema(title = "사용자 이메일", description = "사용자 이메일을 입력합니다.")
    @SerializedName("userEmail")
    @NotBlank(message = "이메일이 존재하지 않습니다.")
    private String userEmail = "";
    @Schema(title = "인증코드", description = "수신 받은 인증코드를 입력합니다.")
    @SerializedName("code")
    @NotBlank(message = "인증코드가 존재하지 않습니다.")
    private String code = "";

    @Builder
    public CertificationDtoRequest(String userEmail, String code) {
        this.userEmail = userEmail;
        this.code = code;
    }
}
