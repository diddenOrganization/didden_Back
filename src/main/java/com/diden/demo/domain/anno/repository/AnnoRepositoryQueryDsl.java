package com.diden.demo.domain.anno.repository;

import com.diden.demo.domain.anno.entity.Anno;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class AnnoRepositoryQueryDsl extends QuerydslRepositorySupport implements AnnoRepositoryCustom {

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     */
    public AnnoRepositoryQueryDsl() {
        super(Anno.class);
    }
}
