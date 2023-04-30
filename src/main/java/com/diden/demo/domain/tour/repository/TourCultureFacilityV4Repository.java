package com.diden.demo.domain.tour.repository;

import com.diden.demo.domain.tour.entity.TourAccommodationV4;
import com.diden.demo.domain.tour.entity.TourCultureFacilityV4;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourCultureFacilityV4Repository extends JpaRepository<TourCultureFacilityV4, Long> {
    Slice<TourCultureFacilityV4> findBy(Pageable pageable);
}