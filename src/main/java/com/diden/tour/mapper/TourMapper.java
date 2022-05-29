package com.diden.tour.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface TourMapper {
    List<Map<String, Object>> tourInfoList(Map<String, Object> tourInfoParam);
}
