package com.diden.demo.domain.user.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum PrivacyConsentEnum {
    AGREED("Y"),
    DISAGREEABLE("N");

    private final String choice;

    PrivacyConsentEnum(String choice) {
        this.choice = choice;
    }

    public String getChoice() {
        return choice;
    }

    public static PrivacyConsentEnum getPrivacyConsentEnumType(final String privacyConsent) {
        for (PrivacyConsentEnum value : PrivacyConsentEnum.values()) {
            if (StringUtils.equals(value.getChoice(), privacyConsent)) {
                return value;
            }
        }
        throw new IllegalArgumentException("개인정보동의 형식이 맞지 않습니다.");
    }
}