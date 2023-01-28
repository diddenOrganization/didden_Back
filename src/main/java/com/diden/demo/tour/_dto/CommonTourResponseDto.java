package com.diden.demo.tour._dto;

import com.diden.demo.tour.definition.AreaCode;
import com.diden.demo.tour.definition.ServiceContentTypeCode;
import com.diden.demo.tour.definition.ServiceHighCode;
import com.diden.demo.tour.definition.ServiceMiddleCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommonTourResponseDto {
  private String contentId;
  private ServiceContentTypeCode serviceContentTypeCode;
  private String title;
  private String addr1;
  private String addr2;
  private ServiceHighCode serviceHighCode;
  private ServiceMiddleCode serviceMiddleCode;
  private String serviceLowCode;
  private String firstImage;
  private String firstImage2;
  private String mapx;
  private String mapy;
  private String readcount;
  private String sigungucode;
  private String tel;
  private String zipcode;
  private String createdTime;
  private String modifiedTime;
  private AreaCode areaCode;
  private String mlevel;
  private String overview;
  private String dist;
  private String eventstartdate;
  private String eventenddate;
  private String beniYN;
  private String gdstYN;
  private String trditHanokYN;
  private String hmpg;
  private String booktour;
  private String telname;
}
