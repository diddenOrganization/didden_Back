package com.diden.demo.domain.mail.vo.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class CertificationVoRequest {
    private String userEmail;
    private String code;

    @Builder
    public CertificationVoRequest(String userEmail, String code) {
        this.userEmail = userEmail;
        this.code = code;
    }
}
