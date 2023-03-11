package com.diden.demo.domain.user.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum GenderEnum {
    MALE("M"),
    FEMALE("F");

    private final String gender;

    GenderEnum(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public static GenderEnum getGenderEnumType(final String gender) {
        for (GenderEnum value : GenderEnum.values()) {
            if (StringUtils.equals(value.getGender(), gender)) {
                return value;
            }
        }
        throw new IllegalArgumentException("성별이 형식에 맞지 않습니다.");
    }
}