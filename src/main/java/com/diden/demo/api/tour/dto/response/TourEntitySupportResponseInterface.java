package com.diden.demo.api.tour.dto.response;

import com.diden.demo.domain.tour.entity.TourEntitySupportInterface;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(hidden = true)
public interface TourEntitySupportResponseInterface<T extends TourEntitySupportInterface> {
    TourEntitySupportResponseInterface getTourResponse(TourEntitySupportInterface<?> entity);
}
