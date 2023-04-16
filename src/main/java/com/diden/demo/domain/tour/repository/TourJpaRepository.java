package com.diden.demo.domain.tour.repository;

import com.diden.demo.domain.tour.entity.TourV4;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourJpaRepository extends JpaRepository<TourV4, String> {

}
