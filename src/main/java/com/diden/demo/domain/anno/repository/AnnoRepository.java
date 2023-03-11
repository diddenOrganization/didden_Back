package com.diden.demo.domain.anno.repository;

import com.diden.demo.domain.anno.entity.Anno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnoRepository extends JpaRepository<Anno, Long>, AnnoRepositoryCustom {
}
