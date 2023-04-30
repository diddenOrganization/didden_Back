package com.diden.demo.domain.tour.service;

import com.diden.demo.domain.tour.vo.response.TourCommonV4ResponseVo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface TourCultureFacilityV4Service {
    Slice<TourCommonV4ResponseVo> selectCommonData(PageRequest pageRequest);
}
