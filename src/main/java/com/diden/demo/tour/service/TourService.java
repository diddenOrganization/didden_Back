package com.diden.demo.tour.service;

import com.diden.demo.tour._dto.CommonTourResponseDto;
import com.diden.demo.tour._vo.CommonTourInfoVo;
import com.diden.demo.tour._vo.CommonTourInitVo;
import com.diden.demo.tour.definition.AreaCode;
import com.diden.demo.tour.definition.ServiceContentTypeCode;
import com.diden.demo.tour.vo.TourAreaCodeVo;
import com.diden.demo.tour.vo.TourAreaInfoResponseDto;
import com.diden.demo.tour.vo.TourSigunguCodeVo;

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
