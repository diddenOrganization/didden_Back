package com.diden.demo.api.tour.service;

import com.diden.demo.api.tour.dto.response.TourCommonV4ResponseDto;
import com.diden.demo.domain.tour.service.TourCultureFacilityV4Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourApiServiceImpl implements TourApiService {
    private final TourCultureFacilityV4Service tourCultureFacilityV4Service;

    @Override
    public Slice<TourCommonV4ResponseDto> pageSlice(PageRequest pageRequest) {
        return new TourCommonV4ResponseDto().receiveSliceByVo(tourCultureFacilityV4Service.selectCommonData(pageRequest));
    }
}
