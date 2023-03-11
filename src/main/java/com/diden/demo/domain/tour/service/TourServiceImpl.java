package com.diden.demo.domain.tour.service;

import com.diden.demo.api.tour.dto.response.CommonTourResponseDto;
import com.diden.demo.api.tour.dto.request.CommonTourInfoVo;
import com.diden.demo.domain.tour.enums.AreaCode;
import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.diden.demo.domain.tour.repository.TourMapper;
import com.diden.demo.domain.tour.vo.TourAreaCodeVo;
import com.diden.demo.domain.tour.vo.TourAreaInfoResponseDto;
import com.diden.demo.domain.tour.vo.TourSigunguCodeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {
  private final TourMapper tourMapper;

  @Override
  public List<TourAreaInfoResponseDto> tourInfoList(Map<String, Object> tourInfoParam) {
    final List<TourAreaInfoResponseDto> tourAreaInfoResponseDtos = tourMapper.tourInfoList(tourInfoParam);
    final List<TourAreaInfoResponseDto> collectResult = new ArrayList<>();

    for(TourAreaInfoResponseDto dto : tourAreaInfoResponseDtos) {
      dto.convertServiceTypeCodeByEnumTypeCodeAndTitleSetting(dto, dto.getContentTypeId(), dto.getCat1(), dto.getCat2());
      dto.convertServiceTypeCodeTitle(dto, dto.getServiceContentTypeCode(), dto.getServiceHighCode(), dto.getServiceMiddleCode());
      dto.convertSigunuAndAreaCodeByTitle(dto, dto.getAreaCode(), dto.getSigunuCode(), this.newSigunguCodeList());
      collectResult.add(dto);
    }

    return collectResult;
  }

  @Override
  public void tourInfoInsert(Map<String, Object> tourInfoParam) {
    tourMapper.tourInfoInsert(tourInfoParam);
  }

  @Override
  public void tourInfoUpdate(Map<String, Object> tourInfoParam) {
    tourMapper.tourInfoUpdate(tourInfoParam);
  }

  @Override
  public void tourInfoDelete() {
    tourMapper.tourInfoDelete();
  }

  @Override
  public List<TourAreaCodeVo> tourAreaCodeList() {
    return tourMapper.findAreaCodeList();
  }

  @Override
  public List<TourSigunguCodeVo> tourSigunguCodeList(AreaCode areaCode) {
    return tourMapper.findSigunguCodeList(areaCode.getCode());
  }

  @Override
  public List<CommonTourResponseDto> tourInfoList(CommonTourInfoVo commonTourInfoVo) {
    return tourMapper.newTourInfoList(commonTourInfoVo);
  }

  @Override
  public Map<ServiceContentTypeCode, List<CommonTourResponseDto>> newTourInfoListTest() {
    final ServiceContentTypeCode[] serviceContentTypeCodes = ServiceContentTypeCode.values();
    final Map<ServiceContentTypeCode, List<CommonTourResponseDto>> tourDataList = new HashMap<>();
    for (ServiceContentTypeCode serviceContentTypeCode : serviceContentTypeCodes) {
      final Integer code = serviceContentTypeCode.getCode();
      tourDataList.put(serviceContentTypeCode, tourMapper.newTourInfoListTest(code, null));
    }
    return tourDataList;
  }

  @Override
  public Map<AreaCode, List<TourSigunguCodeVo>> newSigunguCodeList() {
    final AreaCode[] areaCodes = AreaCode.values();
    final Map<AreaCode, List<TourSigunguCodeVo>> tourDataList = new HashMap<>();
    for (AreaCode areaCode : areaCodes) {
      final Integer code = areaCode.getCode();
      tourDataList.put(areaCode, tourMapper.newSigunguCodeList(code));
    }
    return tourDataList;
  }


  @Override
  public void tourInfoOverViewUpdate(Map<String, Object> tourInfoParam) {
    tourMapper.tourInfoOverViewUpdate(tourInfoParam);
  }

  @Override
  public void tourInfoAreaNameUpdate(Map<String, Object> tourInfoParam) {
    tourMapper.tourInfoOverViewUpdate(tourInfoParam);
  }
}
