package com.diden.demo.domain.mail.vo.response;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
public class CertificationVo {
    private String userEmail = "";
    private String code = "";

    @Builder
    public CertificationVo(String userEmail, String code) {
        this.userEmail = userEmail;
        this.code = code;
    }
}
