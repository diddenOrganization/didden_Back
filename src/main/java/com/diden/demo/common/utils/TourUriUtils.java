package com.diden.demo.common.utils;

import com.diden.demo.domain.tour.enums.MobileOSType;
import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.diden.demo.domain.tour.enums.ServiceHighCode;
import com.diden.demo.domain.tour.enums.ServiceMiddleCode;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public final class TourUriUtils {
  public static String tourServiceContentTypeUri(
      final ServiceContentTypeCode serviceContentTypeCode) {

    final StringBuilder sb = new StringBuilder().append("&contentTypeId=");

    if (serviceContentTypeCode.getCode() == null) {
      return sb.toString();
    }

    return sb.append(serviceContentTypeCode.getCode()).toString();
  }

  public static String tourInitUri(
      final MobileOSType mobileOS, final String mobileApp, int numOfRows) {

    if (numOfRows < -1) {
      numOfRows = 1;
    }

    return String.format(
        "&mobileOS=%s" + "&mobileApp=%s" + "&numOfRows=%s",
        mobileOS.name(), Optional.ofNullable(mobileApp).orElse("AppTest"), numOfRows);
  }

  public static String tourServiceCategoryUri(
      final ServiceHighCode serviceHighCode,
      final ServiceMiddleCode serviceMiddleCode,
      String serviceLowCode) {

    if (StringUtils.isBlank(serviceLowCode)) {
      serviceLowCode = "";
    }

    return String.format(
        "&cat1=%s" + "&cat2=%s" + "&cat3=%s",
        serviceHighCode.getCode(), serviceMiddleCode.getCode(), serviceLowCode);
  }
}
