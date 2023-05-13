package com.diden.demo.common.config;

import com.diden.demo.common.adepter.*;
import com.diden.demo.domain.tour.entity.TourCommonEntityV4;
import com.diden.demo.domain.tour.entity.TourEntitySupportInterface;
import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.diden.demo.domain.tour.repository.*;
import com.diden.demo.domain.user.adepter.AppleSocialAdepterImpl;
import com.diden.demo.domain.user.adepter.KakaoSocialAdepterImpl;
import com.diden.demo.domain.user.adepter.NaverSocialAdepterImpl;
import com.diden.demo.domain.user.adepter.SocialAdepter;
import com.diden.demo.domain.user.enums.AccountTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class BeanInitConfig {
  private final Map<AccountTypeEnum, SocialAdepter> socialAdepterMap = new HashMap<>();
  private final Map<AccountTypeEnum, LoginLogoutAdepter> loginLogoutAdepterMap = new HashMap<>();
  private final Map<ServiceContentTypeCode, JpaRepository> contentTypeAdepterMap = new HashMap<>();
  private final TourRestaurantV4Repository tourRestaurantV4Repository;
  private final TourShoppingV4Repository tourShoppingV4Repository;
  private final TourTourismV4Repository tourTourismV4Repository;
  private final TourTravelCourseV4Repository tourTravelCourseV4Repository;
  private final TourRecreationV4Repository tourRecreationV4Repository;
  private final TourEventV4Repository tourEventV4Repository;
  private final TourCultureFacilityV4Repository tourCultureFacilityV4Repository;
  private final TourAccommodationV4Repository tourAccommodationV4Repository;

  @Bean
  public Map<AccountTypeEnum, LoginLogoutAdepter> loginLogoutAdepterMap() {
    this.loginLogoutAdepterMap.put(AccountTypeEnum.NAVER, new LoginLogoutNaver());
    this.loginLogoutAdepterMap.put(AccountTypeEnum.KAKAO, new LoginLogoutKakao());
    this.loginLogoutAdepterMap.put(AccountTypeEnum.APPLE, new LoginLogoutApple());
    this.loginLogoutAdepterMap.put(AccountTypeEnum.DEFAULT, new LoginLogoutDefault());
    return loginLogoutAdepterMap;
  }

  @Bean
  public Map<AccountTypeEnum, SocialAdepter> socialAdepterMap() {
    this.socialAdepterMap.put(AccountTypeEnum.NAVER, new NaverSocialAdepterImpl());
    this.socialAdepterMap.put(AccountTypeEnum.KAKAO, new KakaoSocialAdepterImpl());
    this.socialAdepterMap.put(AccountTypeEnum.APPLE, new AppleSocialAdepterImpl());
    return socialAdepterMap;
  }

  @Bean
  public Map<ServiceContentTypeCode, JpaRepository> contentTypeAdepterMap() {
    contentTypeAdepterMap.put(ServiceContentTypeCode.TOURISM_TYPE_A01, tourTourismV4Repository);
    contentTypeAdepterMap.put(ServiceContentTypeCode.CULTURAL_FACILITIES_TYPE, tourCultureFacilityV4Repository);
    contentTypeAdepterMap.put(ServiceContentTypeCode.FESTIVAL_TYPE, tourEventV4Repository);
    contentTypeAdepterMap.put(ServiceContentTypeCode.TRAVEL_TYPE, tourTravelCourseV4Repository);
    contentTypeAdepterMap.put(ServiceContentTypeCode.LEPORTS_TYPE, tourRecreationV4Repository);
    contentTypeAdepterMap.put(ServiceContentTypeCode.HOTEL_TYPE, tourAccommodationV4Repository);
    contentTypeAdepterMap.put(ServiceContentTypeCode.SHOPPING_TYPE, tourShoppingV4Repository);
    contentTypeAdepterMap.put(ServiceContentTypeCode.FOOD_TYPE, tourRestaurantV4Repository);

    return contentTypeAdepterMap;
  }
}
