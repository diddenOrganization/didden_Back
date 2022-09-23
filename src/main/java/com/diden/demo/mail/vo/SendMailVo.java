package com.diden.demo.mail.vo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SendMailVo {
    @SerializedName("to")
    private String to = "";
    @SerializedName("from")
    private String from = "";
    @SerializedName("subject")
    private String subject = "";
    @SerializedName("content")
    private String content = "";
}
