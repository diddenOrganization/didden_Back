package com.diden.demo.domain.tour.service;

import com.diden.demo.domain.tour.entity.TourEntitySupportInterface;
import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.diden.demo.domain.tour.enums.ServiceHighCode;
import com.diden.demo.domain.tour.enums.ServiceMiddleCode;
import com.diden.demo.domain.tour.vo.response.TourCommonV4ResponseVo;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface TourCommonV4Service {
  Slice<TourCommonV4ResponseVo> selectCommonData(
      Pageable pageable,
      List<ServiceContentTypeCode> serviceContentTypeCodes,
      List<ServiceHighCode> serviceHighCodes,
      List<ServiceMiddleCode> serviceMiddleCodes,
      String keyword);

  TourEntitySupportInterface selectTourByContentTypeAndContentId(final ServiceContentTypeCode serviceContentTypeCode, final Long contentId) throws Throwable;
}
