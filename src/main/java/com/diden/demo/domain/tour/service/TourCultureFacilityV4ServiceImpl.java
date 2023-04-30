package com.diden.demo.domain.tour.service;

import com.diden.demo.domain.tour.entity.TourAccommodationV4;
import com.diden.demo.domain.tour.entity.TourCultureFacilityV4;
import com.diden.demo.domain.tour.repository.TourCultureFacilityV4Repository;
import com.diden.demo.domain.tour.vo.response.TourCommonV4ResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourCultureFacilityV4ServiceImpl implements TourCultureFacilityV4Service {
    private final TourCultureFacilityV4Repository tourCultureFacilityV4Repository;

    @Override
    public Slice<TourCommonV4ResponseVo> selectCommonData(PageRequest pageRequest) {
        Slice<TourCultureFacilityV4> by = tourCultureFacilityV4Repository.findBy(pageRequest);
        return new TourCommonV4ResponseVo().selectSliceInit(by);
    }
}
