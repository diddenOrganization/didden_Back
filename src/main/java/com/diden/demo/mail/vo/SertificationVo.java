package com.diden.demo.mail.vo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
@Getter
@Setter
public class SertificationVo {
    @SerializedName("userEmail")
    @NotBlank(message = "이메일이 존재하지 않습니다.")
    private String userEmail = "";
    @SerializedName("code")
    @NotBlank(message = "인증코드가 존재하지 않습니다.")
    private String code = "";
}
