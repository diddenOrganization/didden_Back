package com.diden.demo.domain.tour.service;

import com.diden.demo.domain.tour.repository.TourCultureFacilityV4Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourCultureFacilityV4ServiceImpl implements TourCultureFacilityV4Service {
    private final TourCultureFacilityV4Repository tourCultureFacilityV4Repository;

}
