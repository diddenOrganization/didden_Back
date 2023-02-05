package com.diden.demo.tour.vo;

import com.diden.demo.tour.definition.AreaCode;
import com.diden.demo.tour.definition.ServiceContentTypeCode;
import com.diden.demo.tour.definition.ServiceHighCode;
import com.diden.demo.tour.definition.ServiceMiddleCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class TourAreaInfoResponseDto {
  private String contentId;
  private String contentTypeId;
  private String title;
  private String addr1;
  private String addr2;
  private String cat1;
  private String cat2;
  private String cat3;
  private String overview;
  private String firstImage;
  private String firstImage2;
  private String mapx;
  private String mapy;
  private String readCount;
  private String sigunuCode;
  private String tel;
  private String zipCode;
  private String createdTime;
  private String modifiedTime;
  private String areaCode;
  private String mLevel;

  private ServiceContentTypeCode serviceContentTypeCode;
  private ServiceHighCode serviceHighCode;
  private ServiceMiddleCode serviceMiddleCode;

  private String contentTypeName;
  private String highCodeName;
  private String middleCodeName;

  private AreaCode areaTypeCode;
  private TourSigunguCodeVo tourSigunguCodeVo;

  private String sigunuName;
  private String areaName;

  @Builder
  public TourAreaInfoResponseDto(
      String contentId,
      String contentTypeId,
      String title,
      String addr1,
      String addr2,
      String cat1,
      String cat2,
      String cat3,
      String firstImage,
      String firstImage2,
      String mapx,
      String mapy,
      String readCount,
      String sigunuCode,
      String tel,
      String zipCode,
      String createdTime,
      String modifiedTime,
      String areaCode,
      String mLevel) {
    this.contentId = contentId;
    this.contentTypeId = contentTypeId;
    this.title = title;
    this.addr1 = addr1;
    this.addr2 = addr2;
    this.cat1 = cat1;
    this.cat2 = cat2;
    this.cat3 = cat3;
    this.firstImage = firstImage;
    this.firstImage2 = firstImage2;
    this.mapx = mapx;
    this.mapy = mapy;
    this.readCount = readCount;
    this.sigunuCode = sigunuCode;
    this.tel = tel;
    this.zipCode = zipCode;
    this.createdTime = createdTime;
    this.modifiedTime = modifiedTime;
    this.areaCode = areaCode;
    this.mLevel = mLevel;
  }

  public void convertServiceTypeCodeTitle(
      final TourAreaInfoResponseDto tourAreaInfoResponseDto,
      final ServiceContentTypeCode serviceContentTypeCode,
      final ServiceHighCode serviceHighCode,
      final ServiceMiddleCode serviceMiddleCode) {

    tourAreaInfoResponseDto.setContentTypeName(serviceContentTypeCode.getTitle());
    tourAreaInfoResponseDto.setHighCodeName(serviceHighCode.getTitle());
    tourAreaInfoResponseDto.setMiddleCodeName(serviceMiddleCode.getTitle());
  }

  public void convertServiceTypeCodeByEnumTypeCodeAndTitleSetting(
      final TourAreaInfoResponseDto tourAreaInfoResponseDto,
      final String serviceContentTypeCode,
      final String serviceHighCode,
      final String serviceMiddleCode) {

    final ServiceContentTypeCode contentTypeCodeConvert =
        ServiceContentTypeCode.codeToEnum(Integer.parseInt(serviceContentTypeCode));
    final ServiceHighCode highCodeConvert = ServiceHighCode.codeToEnum(serviceHighCode);
    final ServiceMiddleCode middleCodeConvert = ServiceMiddleCode.codeToEnum(serviceMiddleCode);

    tourAreaInfoResponseDto.setServiceContentTypeCode(contentTypeCodeConvert);
    tourAreaInfoResponseDto.setServiceHighCode(highCodeConvert);
    tourAreaInfoResponseDto.setServiceMiddleCode(middleCodeConvert);
  }

  public void convertSigunuAndAreaCodeByTitle(
      final TourAreaInfoResponseDto tourAreaInfoResponseDto,
      final String areaCode,
      final String sigunuCode,
      final Map<AreaCode, List<TourSigunguCodeVo>> areaCodeListMap) {

    final AreaCode areaTypeCode = AreaCode.findArea(Integer.parseInt(areaCode));
    final List<TourSigunguCodeVo> tourSigunguCodeVos = areaCodeListMap.get(areaTypeCode);

    final TourSigunguCodeVo tourSigunguCode =
        new TourSigunguCodeVo()
            .findNameByTourSigunguCodeVoList(tourSigunguCodeVos, Integer.parseInt(sigunuCode));

    tourAreaInfoResponseDto.setAreaName(areaTypeCode.getTitle());
    tourAreaInfoResponseDto.setSigunuName(tourSigunguCode.getName());
  }
}
