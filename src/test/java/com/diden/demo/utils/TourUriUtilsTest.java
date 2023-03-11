package com.diden.demo.utils;

import com.diden.demo.domain.tour.enums.MobileOSType;
import com.diden.demo.domain.tour.enums.ServiceHighCode;
import com.diden.demo.domain.tour.enums.ServiceMiddleCode;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class TourUriUtilsTest {

  @Test
  void tourServiceCategoryUriMethodTest() {
    String uri = tourServiceCategoryUri(ServiceHighCode.NATURE, null, null);
    System.out.println(uri);

    String uri2 =
        tourServiceCategoryUri(ServiceHighCode.NATURE, ServiceMiddleCode.NATURAL_TOURIST, null);
    System.out.println(uri2);

    System.out.println(tourInitUri(MobileOSType.ETC, null, 2));
  }

  public String tourServiceCategoryUri(
      ServiceHighCode serviceHighCode, ServiceMiddleCode serviceMiddleCode, String serviceLowCode) {

    if (serviceHighCode == null) {
      serviceHighCode = ServiceHighCode.NONE;
    }

    if (serviceMiddleCode == null) {
      serviceMiddleCode = ServiceMiddleCode.NONE;
    }

    if (StringUtils.isBlank(serviceLowCode)) {
      serviceLowCode = "";
    }

    return String.format(
        "cat1=%s" + "&cat2=%s" + "&cat3=%s",
        serviceHighCode.getCode(), serviceMiddleCode.getCode(), serviceLowCode);
  }

  public String tourInitUri(final MobileOSType mobileOS, final String mobileApp, int numOfRows) {
    if (numOfRows < -1) {
      numOfRows = 1;
    }

    return String.format(
        "mobileOS=%s" + "&mobileApp=%s" + "&numOfRows=%s",
        mobileOS.name(), Optional.ofNullable(mobileApp).orElse("AppTest"), numOfRows);
  }
}
