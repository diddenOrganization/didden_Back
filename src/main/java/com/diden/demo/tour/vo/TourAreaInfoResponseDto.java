package com.diden.demo.tour.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TourAreaInfoResponseDto {
  private String contentId;
  private String contentTypeId;
  private String title;
  private String addr1;
  private String addr2;
  private String cat1;
  private String cat2;
  private String cat3;
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
}
