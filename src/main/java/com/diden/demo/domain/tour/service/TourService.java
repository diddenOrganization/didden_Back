package com.diden.demo.domain.tour.service;

import com.diden.demo.domain.tour._dto.CommonTourResponseDto;
import com.diden.demo.domain.tour._vo.CommonTourInfoVo;
import com.diden.demo.domain.tour.definition.AreaCode;
import com.diden.demo.domain.tour.definition.ServiceContentTypeCode;
import com.diden.demo.domain.tour.vo.TourAreaCodeVo;
import com.diden.demo.domain.tour.vo.TourAreaInfoResponseDto;
import com.diden.demo.domain.tour.vo.TourSigunguCodeVo;

import java.util.List;
import java.util.Map;

public interface TourService {
  List<TourAreaInfoResponseDto> tourInfoList(Map<String, Object> tourInfoParam);
  void tourInfoInsert(Map<String, Object> tourInfoParam);
  void tourInfoUpdate(Map<String, Object> tourInfoParam);
  void tourInfoDelete();
  List<TourAreaCodeVo> tourAreaCodeList();
  List<TourSigunguCodeVo> tourSigunguCodeList(AreaCode areaCode);


  List<CommonTourResponseDto> tourInfoList(CommonTourInfoVo commonTourInfoVo);
  Map<ServiceContentTypeCode, List<CommonTourResponseDto>> newTourInfoListTest();
  Map<AreaCode, List<TourSigunguCodeVo>> newSigunguCodeList();
  void tourInfoOverViewUpdate(Map<String, Object> tourInfoParam);

  void tourInfoAreaNameUpdate(Map<String, Object> tourInfoParam);
}
