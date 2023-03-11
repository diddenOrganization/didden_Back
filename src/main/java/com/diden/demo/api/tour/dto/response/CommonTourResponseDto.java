package com.diden.demo.api.tour.dto.response;

import com.diden.demo.domain.tour.enums.AreaCode;
import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.diden.demo.domain.tour.enums.ServiceHighCode;
import com.diden.demo.domain.tour.enums.ServiceMiddleCode;
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
