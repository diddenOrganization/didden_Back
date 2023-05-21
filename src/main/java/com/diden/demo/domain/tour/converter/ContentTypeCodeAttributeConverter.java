package com.diden.demo.domain.tour.converter;

import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ContentTypeCodeAttributeConverter implements AttributeConverter<ServiceContentTypeCode, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ServiceContentTypeCode attribute) {
        return attribute.getCode();
    }

    @Override
    public ServiceContentTypeCode convertToEntityAttribute(Integer dbData) {
        return ServiceContentTypeCode.codeToEnum(dbData);
    }
}
