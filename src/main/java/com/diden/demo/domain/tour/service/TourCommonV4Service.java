package com.diden.demo.domain.tour.service;

import com.diden.demo.domain.tour.entity.TourCommonEntityV4;
import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.diden.demo.domain.tour.enums.ServiceHighCode;
import com.diden.demo.domain.tour.enums.ServiceMiddleCode;
import com.diden.demo.domain.tour.vo.response.TourCommonV4ResponseVo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Map;

public interface TourCommonV4Service {

    Slice<TourCommonV4ResponseVo> selectCommonData(PageRequest pageRequest, List<ServiceContentTypeCode> serviceContentTypeCodes, List<ServiceHighCode> serviceHighCodes, List<ServiceMiddleCode> serviceMiddleCodes);
    Map<Long, TourCommonEntityV4> findToursByContentType(List<ServiceContentTypeCode> serviceContentTypeCodes);
    Map<Long, TourCommonEntityV4> tourListFindByHighCode(Map<Long, TourCommonEntityV4> findTours, List<ServiceHighCode> serviceHighCodes);
    Map<Long, TourCommonEntityV4> tourListFindByMiddleCode(Map<Long, TourCommonEntityV4> findTours, List<ServiceMiddleCode> serviceMiddleCodes);
}
