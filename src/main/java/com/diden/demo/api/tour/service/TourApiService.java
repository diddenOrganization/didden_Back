package com.diden.demo.api.tour.service;

import com.diden.demo.api.tour.dto.response.TourCommonV4ResponseDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

public interface TourApiService {
    Slice<TourCommonV4ResponseDto> pageSlice(PageRequest pageRequest);
}
