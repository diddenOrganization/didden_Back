package com.diden.demo.tour.mapper;

import com.diden.demo.tour.vo.TourAreaCodeVo;
import com.diden.demo.tour.vo.TourAreaInfoResponseDto;
import com.diden.demo.tour.vo.TourSigunguCodeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Map;

@Mapper
public interface TourMapper {
  List<TourAreaInfoResponseDto> tourInfoList(Map<String, Object> tourInfoParam);

  void tourInfoInsert(Map<String, Object> tourInfoParam);

  void tourInfoUpdate(Map<String, Object> tourInfoParam);

  void tourInfoDelete();

  int insertSigunguCode(
      @Param("code") final Integer code,
      @Param("name") final String name,
      @Param("areaCode") final Integer areaCode);

  List<TourAreaCodeVo> findAreaCodeList();

  List<TourSigunguCodeVo> findSigunguCodeList(@Param("areaCode") final Integer areaCode);

}
