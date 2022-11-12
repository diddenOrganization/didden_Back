package com.diden.demo.tour.service.impl;

import com.diden.demo.tour.definition.AreaCode;
import com.diden.demo.tour.mapper.TourMapper;
import com.diden.demo.tour.service.TourService;
import com.diden.demo.tour.vo.TourAreaCodeVo;
import com.diden.demo.tour.vo.TourAreaInfoResponseDto;
import com.diden.demo.tour.vo.TourSigunguCodeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {
  private final TourMapper tourMapper;

  @Override
  public List<TourAreaInfoResponseDto> tourInfoList(Map<String, Object> tourInfoParam) {
    System.out.println("tourInfoParam=" + tourInfoParam);
    return tourMapper.tourInfoList(tourInfoParam);
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
}
