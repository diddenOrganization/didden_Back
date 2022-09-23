package com.diden.demo.mail.vo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SertificationVo {
    @SerializedName("userEmail")
    private String userEmail = "";
    @SerializedName("code")
    private String code = "";
}
