package com.diden.demo.tour._dto;

import com.diden.demo.tour.definition.AreaCode;
import com.diden.demo.tour.definition.ServiceContentTypeCode;
import com.diden.demo.tour.definition.ServiceHighCode;
import com.diden.demo.tour.definition.ServiceMiddleCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonTourResponseDto {
  private String addr1;
  private String addr2;
  private AreaCode areaCode;
  private String booktour;
  private ServiceHighCode cat1;
  private ServiceMiddleCode cat2;
  private String cat3;
  private String contentId;
  private ServiceContentTypeCode contentTypeId;
  private String createdTime;
  private String firstImage;
  private String firstImage2;
  private String mapx;
  private String mapy;
  private String mlevel;
  private String modifiedTime;
  private String readcount;
  private String sigungucode;
  private String tel;
  private String title;
  private String zipcode;
  private String dist;
  private String eventstartdate;
  private String eventenddate;
  private String beniYN;
  private String gdstYN;
  private String trditHanokYN;
  private String hmpg;
  private String telname;
  private String overview;
}
