package com.diden.demo;

import com.diden.demo.tour.controller.TourApiController;
import com.diden.demo.tour.vo.korservicevo.*;
import org.junit.jupiter.api.Test;

class TourApiControllerTest {

    TourApiController tourApiController = new TourApiController();

    @Test
    void tourAreaCode() {
        TourAreaCodeVo tourAreaCodeVo = new TourAreaCodeVo();
        tourAreaCodeVo.setAreaCode("1");
        System.out.println("tourAreaCodeVo = " + tourAreaCodeVo);
        System.out.println(tourApiController.tourAreaCode(tourAreaCodeVo));
    }

    @Test
    void tourCategoryCode() {
        TourCategoryCodeVo tourCategoryCodeVo = new TourCategoryCodeVo();
        tourCategoryCodeVo.setContentTypeId("12");
        tourCategoryCodeVo.setCat1("A01");
        tourCategoryCodeVo.setCat2("A0101");
        tourCategoryCodeVo.setCat3("A01010100");
        System.out.println("tourCategoryCodeVo = " + tourCategoryCodeVo);
        System.out.println(tourApiController.tourCategoryCode(tourCategoryCodeVo));
    }

    @Test
    void tourAreaBasedList() {
        TourAreaBasedListVo tourAreaBasedListVo = new TourAreaBasedListVo();
        tourAreaBasedListVo.setListYN("Y");
        tourAreaBasedListVo.setArrange("");
        tourAreaBasedListVo.setContentTypeId("32");
        tourAreaBasedListVo.setAreaCode("4");
        tourAreaBasedListVo.setSigunguCode("4");
        tourAreaBasedListVo.setCat1("");
        tourAreaBasedListVo.setCat2("");
        tourAreaBasedListVo.setCat3("");
        tourAreaBasedListVo.setModifiedtime("");
        System.out.println("tourAreaBasedListVo = " + tourAreaBasedListVo);
        System.out.println(tourApiController.tourAreaBasedList(tourAreaBasedListVo));
    }

    @Test
    void tourLocationBasedList() {
        TourLocationBasedListVo tourLocationBasedListVo = new TourLocationBasedListVo();
        tourLocationBasedListVo.setListYN("Y");
        tourLocationBasedListVo.setArrange("A");
        tourLocationBasedListVo.setContentTypeId("15");
        tourLocationBasedListVo.setMapX("126.981611");
        tourLocationBasedListVo.setMapY("37.568477");
        tourLocationBasedListVo.setRadius("1000");
        tourLocationBasedListVo.setModifiedtime("");
        System.out.println("tourLocationBasedListVo = " + tourLocationBasedListVo);
        System.out.println(tourApiController.tourLocationBasedList(tourLocationBasedListVo));
    }

    @Test
    void tourSearchKeyword() {
        TourSearchKeywordVo tourSearchKeywordVo = new TourSearchKeywordVo();
        tourSearchKeywordVo.setListYN("Y");
        tourSearchKeywordVo.setArrange("A");
        tourSearchKeywordVo.setContentTypeId("12");
        tourSearchKeywordVo.setAreaCode("");
        tourSearchKeywordVo.setSigunguCode("");
        tourSearchKeywordVo.setCat1("");
        tourSearchKeywordVo.setCat2("");
        tourSearchKeywordVo.setCat3("");
        tourSearchKeywordVo.setPageNo("1");
        tourSearchKeywordVo.setKeyword("강원");
        System.out.println("tourSearchKeywordVo = " + tourSearchKeywordVo);
        System.out.println(tourApiController.tourSearchKeyword(tourSearchKeywordVo));
    }

    @Test
    void tourSearchFestival() {
        TourSearchFestivalVo tourSearchFestivalVo = new TourSearchFestivalVo();
        tourSearchFestivalVo.setListYN("Y");
        tourSearchFestivalVo.setArrange("A");
        tourSearchFestivalVo.setAreaCode("");
        tourSearchFestivalVo.setSigunguCode("");
        tourSearchFestivalVo.setEventStartDate("20220201");
        tourSearchFestivalVo.setEventEndDate("");
        tourSearchFestivalVo.setModifiedtime("");
        System.out.println("tourSearchFestivalVo = " + tourSearchFestivalVo);
        System.out.println(tourApiController.tourSearchFestival(tourSearchFestivalVo));
    }

    @Test
    void tourSearchStay() {
        TourSearchStayVo tourSearchStayVo = new TourSearchStayVo();
        tourSearchStayVo.setListYN("Y");
        tourSearchStayVo.setArrange("A");
        tourSearchStayVo.setAreaCode("");
        tourSearchStayVo.setSigunguCode("");
        tourSearchStayVo.setHanOk("");
        tourSearchStayVo.setBenikia("");
        tourSearchStayVo.setGoodStay("");
        tourSearchStayVo.setModifiedtime("");
        System.out.println("tourSearchFestivalVo = " + tourSearchStayVo);
        System.out.println(tourApiController.tourSearchStay(tourSearchStayVo));
    }

    @Test
    void tourDetailCommon() {
        TourDetailCommonVo tourDetailCommonVo = new TourDetailCommonVo();
        tourDetailCommonVo.setContentId("126508");
        tourDetailCommonVo.setContentTypeId("");
        tourDetailCommonVo.setDefaultYN("Y");
        tourDetailCommonVo.setFirstImageYN("Y");
        tourDetailCommonVo.setAreacodeYN("Y");
        tourDetailCommonVo.setCatcodeYN("Y");
        tourDetailCommonVo.setAddrinfoYN("Y");
        tourDetailCommonVo.setMapinfoYN("Y");
        tourDetailCommonVo.setOverviewYN("Y");
        System.out.println("tourDetailCommonVo = " + tourDetailCommonVo);
        System.out.println(tourApiController.tourDetailCommon(tourDetailCommonVo));
    }

    @Test
    void tourDetailIntro() {
        TourDetailIntroVo tourDetailIntroVo = new TourDetailIntroVo();
        tourDetailIntroVo.setContentId("2674675");
        tourDetailIntroVo.setContentTypeId("15");
        System.out.println("tourDetailIntroVo = " + tourDetailIntroVo);
        System.out.println(tourApiController.tourDetailIntro(tourDetailIntroVo));
    }

    @Test
    void tourDetailInfo() {
        TourDetailInfoVo tourDetailInfoVo = new TourDetailInfoVo();
        tourDetailInfoVo.setContentId("2674675");
        tourDetailInfoVo.setContentTypeId("15");
        System.out.println("tourDetailInfoVo = " + tourDetailInfoVo);
        System.out.println(tourApiController.tourDetailInfo(tourDetailInfoVo));
    }

    @Test
    void tourDetailImage() {
        TourDetailImageVo tourDetailImageVo = new TourDetailImageVo();
        tourDetailImageVo.setContentId("1095732");
        tourDetailImageVo.setImageYN("Y");
        tourDetailImageVo.setSubImageYN("Y");
        System.out.println("tourDetailImageVo = " + tourDetailImageVo);
        System.out.println(tourApiController.tourDetailImage(tourDetailImageVo));
    }
}