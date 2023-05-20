package com.diden.demo.domain.tour.service;

import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.diden.demo.domain.tour.enums.ServiceHighCode;
import com.diden.demo.domain.tour.enums.ServiceMiddleCode;
import com.diden.demo.domain.tour.vo.response.TourCommonV4ResponseVo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface TourCommonV4Service {
    Slice<TourCommonV4ResponseVo> selectCommonData(Pageable pageable, List<ServiceContentTypeCode> serviceContentTypeCodes, List<ServiceHighCode> serviceHighCodes, List<ServiceMiddleCode> serviceMiddleCodes, String keyword);
}
