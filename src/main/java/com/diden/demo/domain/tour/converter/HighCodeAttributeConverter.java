package com.diden.demo.domain.tour.converter;

import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.diden.demo.domain.tour.enums.ServiceHighCode;
import com.diden.demo.domain.tour.vo.response.HighCodeMapperValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class HighCodeAttributeConverter implements AttributeConverter<ServiceHighCode, String> {

    @Override
    public String convertToDatabaseColumn(ServiceHighCode attribute) {
        return attribute.getCode();
    }

    @Override
    public ServiceHighCode convertToEntityAttribute(String dbData) {
        return ServiceHighCode.codeToEnum(dbData);
    }
}
