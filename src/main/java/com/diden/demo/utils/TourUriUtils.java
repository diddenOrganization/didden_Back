package com.diden.demo.utils;

import com.diden.demo.tour.MobileOSType;
import com.diden.demo.tour.ServiceHighCode;
import com.diden.demo.tour.ServiceMiddleCode;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public class TourUriUtils {
  public static String tourInitUri(final MobileOSType mobileOS, final String mobileApp, int numOfRows) {
    if (numOfRows < -1) {
      numOfRows = 1;
    }

    return String.format(
            "&mobileOS=%s" + "&mobileApp=%s" + "&numOfRows=%s",
            mobileOS.name(), Optional.ofNullable(mobileApp).orElse("AppTest"), numOfRows);
  }

  public static String tourServiceCategoryUri(
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
        "&cat1=%s" + "&cat2=%s" + "&cat3=%s",
        serviceHighCode.getCode(), serviceMiddleCode.getCode(), serviceLowCode);
  }
}
