package com.diden.demo.domain.tour.converter;

import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.diden.demo.domain.tour.enums.ServiceMiddleCode;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MiddleCodeAttributeConverter implements AttributeConverter<ServiceMiddleCode, String> {

    @Override
    public String convertToDatabaseColumn(ServiceMiddleCode attribute) {
        return attribute.getCode();
    }

    @Override
    public ServiceMiddleCode convertToEntityAttribute(String dbData) {
        return ServiceMiddleCode.codeToEnum(dbData);
    }
}
