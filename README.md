# didden_Back

This is didden projects for Back-End

# [목차](#목차)

- [2022-01-03(월) 현재 진행상황](#2022-01-03월-현재-진행상황)
  - [국문 관광정보 서비스.](#국문-관광정보-서비스)
  * [000. 공통 부분](#000-공통-부분)
  * [001. 지역코드조회](#001-지역코드조회)
  * [002. 서비스 분류코드 조회](#002-서비스-분류코드-조회)
  * [003. 지역기반 관광정보 조회](#003-지역기반-관광정보-조회)
  * [004. 위치기반 관광정보 조회](#004-위치기반-관광정보-조회)
  * [005. 키워드 검색 조회](#005-키워드-검색-조회)
  * [007. 행사정보 조회](#007-행사정보-조회)
  * [008. 공통정보 조회](#008-공통정보-조회)
  * [009. 반복정보 조회](#009-반복정보-조회)
  * [010. 이미지정보 조회](#010-이미지정보-조회)
- [2021-12-04(토) 현재 진행상황](#2021-12-04토-현재-진행상황)
  - [1. 관광 데이터](#1-관광-데이터)
  - [2. 로그인/로그아웃 토큰 기능 추가](#2-로그인로그아웃-토큰-기능-추가)
  - [3. 토큰.](#3-토큰)
  - [4. 파싱.](#4-파싱)
- [2021-11-04(목) 현재 진행상황](#2021-11-04목-현재-진행상황)
  - [1. 관광 데이터 테스트 URL](#1-관광-데이터-테스트-url)
- [2021-11-02(화) 현재 진행상황](#2021-11-02화-현재-진행상황)
  - [1. 사용자 정보 VO](#1-사용자-정보-vo)
  - [2. 사용자 정보 리스트](#2-사용자-정보-리스트)
  - [3. 사용자 로그인](#3-사용자-로그인)
  - [4. 사용자 등록 및 수정](#4-사용자-등록-및-수정)
  - [5. 사용자 정보 삭제](#5-사용자-정보-삭제)
- [2021-10-30(토) 현재 진행상황](#2021-10-30토-현재-진행상황)

<small><i><a href='http://ecotrust-canada.github.io/markdown-toc/'>Table of contents generated with markdown-toc</a></i></small>

# 2022-01-03(월) 현재 진행상황

### 국문 관광정보 서비스.

PATH 클릭하면 상세정보를 확인할 수 있습니다.

| 번호 | 서비스 명                                              | PATH                             |
| ---- | ------------------------------------------------------ | -------------------------------- |
| 1    | [지역코드조회 ](#001-지역코드조회)                     | /tour/api/info/areacode          |
| 2    | [서비스 분류코드 조회 ](#002-서비스-분류코드-조회)     | /tour/api/info/categorycode      |
| 3    | [지역기반 관광정보 조회 ](#003-지역기반-관광정보-조회) | /tour/api/info/areabasedlist     |
| 4    | [위치기반 관광정보 조회 ](#004-위치기반-관광정보-조회) | /tour/api/info/locationbasedlist |
| 5    | [키워드 검색 조회 ](#005-키워드-검색-조회)             | /tour/api/info/searchkeyword     |
| 6    | [행사정보 조회 ](#007-행사정보-조회)                   | /tour/api/info/searchfestival    |
| 7    | 숙박정보 조회                                          | /tour/api/info/searchstay        |
| 8    | [공통정보 조회 (상세정보1) ](#008-공통정보-조회)       | /tour/api/info/detailcommon      |
| 9    | 소개정보 조회 (상세정보2)                              | /tour/api/info/detailintro       |
| 10   | [반복정보 조회 (상세정보3) ](##009-반복정보-조회)      | /tour/api/info/detailinfo        |
| 11   | [이미지정보 조회 (상세정보4)](#010-이미지정보-조회)    | /tour/api/info/detailimage       |

---

## 000. 공통 부분

관광 API에서 공통적으로 요구하는 파라미터는 생성자를 통해 초기값을 설정해 놓은 상태.  
값을 다르게 세팅하고 싶다면 언제든 파라미터 값을 다르게 세팅하면 됨.

```java
public class 클래스Vo {
    private String numOfRows = "";
    private String pageNo = "";
    private String mobileOS = "";
    private String mobileApp = "";

    public 생성자() {
        this.mobileApp = "AppTest";
        this.mobileOS = "ETC";
        this.numOfRows = "999";
    }
}

```

해당 파라미터는 필수로 설정해야 함으로 참고.
| 번호 | 컬럼 | 컬럽타입 | 크기 | 컬럼 명 | 컬럼정보 |
| ---- | ------------------ | -------- | ---- | -------- | ----------------- |
| 1 | numOfRows<b style="color: red; font-weight: 500">\*</b> | String | 4 | 한 페이지 결과 수 |한 페이지 결과 수 |
| 2 | pageNo <b style="color: red; font-weight: 500">\*</b> | String | 4 | 페이지 번호 | 현재 페이지 번호|
| 3 | MobileOS <b style="color: red; font-weight: 500">\*</b> | String | 30 | OS 구분 | IOS (아이폰), AND (안드로이드),WIN (윈도우폰), ETC|
| 4 | MobileApp<b style="color: red; font-weight: 500">\*</b> | String | 60 | 서비스명 | 서비스명=어플명|

---

## 001. 지역코드조회

```java
/**
 * 지역코드조회
 *
 * @param "/tour/api/info/areacode"
 * @param tourAreaCodeVo
 */
@GetMapping(value = "/tour/api/info/areacode", produces = "application/json;application/xml; charset=UTF-8")
public String tourAreaCode(@RequestBody(required = false) TourAreaCodeVo tourAreaCodeVo) {
    String tourAreaCodeUrl = new String(
            KOR_SERVICE_URL + "areaCode"
                    + "?serviceKey=" + SERVICE_DEV_KEY
                    + "&numOfRows=" + tourAreaCodeVo.getNumOfRows()
                    + "&pageNo=" + tourAreaCodeVo.getPageNo()
                    + "&MobileOS=" + tourAreaCodeVo.getMobileOS()
                    + "&MobileApp=" + tourAreaCodeVo.getMobileApp()
                    + "&areaCode=" + tourAreaCodeVo.getAreaCode()
                    + "&_type=json"

    );
    ParsingFromURL parsingFromURL = new ParsingFromURL();
    System.out.println("URL => " + tourAreaCodeUrl);
    return parsingFromURL.getParsingURL(tourAreaCodeUrl);
}
```

_serviceKey 파라미터는 static String SERVICE_DEV_KEY = **<하드코딩>** 처리되어있음을 알림._

| 번호 | 컬럼                      | 컬럽타입 | 크기 | 컬럼 명  | 컬럼정보             |
| ---- | ------------------------- | -------- | ---- | -------- | -------------------- |
| 1    | [areaCode](#지역코드조회) | String   | 10   | 지역코드 | 지역코드, 시군구코드 |

_컬럼을 선택하면 샘플데이터를 확인 할 수 있음._

<br>
<br>

---

## 002. 서비스 분류코드 조회

```java
/**
 * 서비스 분류코드 조회
 *
 * @param "/tour/api/info/categorycode"
 * @param tourCategoryCodeVo
 */
@GetMapping(value = "/tour/api/info/categorycode", produces = "application/json;application/xml; charset=UTF-8")
public String tourCategoryCode(@RequestBody(required = false) TourCategoryCodeVo tourCategoryCodeVo) {
    String tourCategoryCodeUrl = new String(
            KOR_SERVICE_URL + "categoryCode"
                    + "?serviceKey=" + SERVICE_DEV_KEY
                    + "&numOfRows=" + tourCategoryCodeVo.getNumOfRows()
                    + "&pageNo=" + tourCaategoryCodeVo.getPageNo()
                    + "&MobileOS=" + tourCategoryCodeVo.getMobileOS()
                    + "&MobileApp=" + tourCategoryCodeVo.getMobileApp()
                    + "&contentTypeId=" + tourCategoryCodeVo.getContentTypeId()
                    + "&cat1=" + tourCategoryCodeVo.getCat1()
                    + "&cat2=" + tourCategoryCodeVo.getCat2()
                    + "&cat3=" + tourCategoryCodeVo.getCat3()
                    + "&_type=json"

    );
    ParsingFromURL parsingFromURL = new ParsingFromURL();
    System.out.println("URL => " + tourCategoryCodeUrl);
    return parsingFromURL.getParsingURL(tourCategoryCodeUrl);
}
```

_serviceKey 파라미터는 static String SERVICE_DEV_KEY = **<하드코딩>** 처리되어있음을 알림._
| 번호 | 컬럼 | 컬럽타입 | 컬럼 명 | 컬럼정보 |
| ---- | ----------------------------- | -------- | ----------- | ---------------------------------- |
| 1 | [contentTypeId](#관광타입) | String | 관광타입 ID | 관광타입(관광지, 숙박 등) ID |
| 2 | [cat1](#대분류) | String | 대분류 | 대분류 코드 |
| 3 | [cat2](#중분류) | String | 중분류 | 중분류 코드(<b style="color: red; font-weight: 500">cat1</b> 필수) |
| 4 | [cat3](#소분류) | String | 소분류 | 소분류 코드(<b style="color: red; font-weight: 500">cat1,cat2</b> 필수) |

_컬럼을 선택하면 샘플데이터를 확인 할 수 있음._

<br>
<br>
---

## 003. 지역기반 관광정보 조회

```java
/**
 * 지역기반 관광정보 조회
 *
 * @param "/tour/api/info/areabasedlist"
 * @param tourAreaBasedListVo
 */
@GetMapping(value = "/tour/api/info/areabasedlist", produces = "application/json;application/xml; charset=UTF-8")
public String tourAreaBasedList(@RequestBody(required = false) TourAreaBasedListVo tourAreaBasedListVo) {
    String tourAreaBasedListUrl = new String(
            KOR_SERVICE_URL + "areaBasedList"
                    + "?serviceKey=" + SERVICE_DEV_KEY
                    + "&numOfRows=" + tourAreaBasedListVo.getNumOfRows()
                    + "&pageNo=" + tourAreaBasedListVo.getPageNo()
                    + "&MobileOS=" + tourAreaBasedListVo.getMobileOS()
                    + "&MobileApp=" + tourAreaBasedListVo.getMobileApp()
                    + "&listYN=" + tourAreaBasedListVo.getListYN()
                    + "&arrange=" + tourAreaBasedListVo.getArrange()
                    + "&contentTypeId=" + tourAreaBasedListVo.getContentTypeId()
                    + "&areaCode=" + tourAreaBasedListVo.getAreaCode()
                    + "&sigunguCode=" + tourAreaBasedListVo.getSigunguCode()
                    + "&cat1=" + tourAreaBasedListVo.getCat1()
                    + "&cat2=" + tourAreaBasedListVo.getCat2()
                    + "&cat3=" + tourAreaBasedListVo.getCat3()
                    + "&modifiedtime=" + tourAreaBasedListVo.getModifiedtime()
                    + "&_type=json"

    );
    ParsingFromURL parsingFromURL = new ParsingFromURL();
    System.out.println("URL => " + tourAreaBasedListUrl);
    return parsingFromURL.getParsingURL(tourAreaBasedListUrl);
}
```

_serviceKey 파라미터는 static String SERVICE_DEV_KEY = **<하드코딩>** 처리되어있음을 알림._

| 번호 | 컬럼                       | 컬럽타입 | 크기 | 컬럼 명     | 컬럼정보                                                                                                                |
| ---- | -------------------------- | -------- | ---- | ----------- | ----------------------------------------------------------------------------------------------------------------------- |
| 1    | listYN                     | String   | 1    | 목록 구분   | 목록 구분 (Y=목록, N=개수)                                                                                              |
| 2    | arrange                    | String   | 1    | 정렬 구분   | (A=제목순, B=조회순, C=수정일순, D=생성일순) 대표이미지가 반드시 있는 정렬 (O=제목순, P=조회순, Q=수정일순, R=생성일순) |
| 3    | [contentTypeId](#관광타입) | String   | 12   | 관광타입 ID | 관광타입(관광지, 숙박 등) ID                                                                                            |
| 4    | [areaCode](#지역코드조회)  | String   | 10   | 지역코드    | 지역코드                                                                                                                |
| 5    | sigunguCode                | String   | 10   | 시군구코드  | 시군구코드(areaCode 필수)                                                                                               |
| 6    | [cat1](#대분류)            | String   | 12   | 대분류      | 대분류 코드                                                                                                             |
| 7    | [cat2](#중분류)            | String   | 12   | 중분류      | 중분류 코드(cat1필수)                                                                                                   |
| 8    | [cat3](#소분류)            | String   | 12   | 소분류      | 소분류 코드(cat1,cat2필수)                                                                                              |
| 9    | modifiedtime               | String   | 8    | 수정일      | 콘텐츠 수정일                                                                                                           |

_컬럼을 선택하면 샘플데이터를 확인 할 수 있음._

<br>
<br>

---

## 004. 위치기반 관광정보 조회

```java
/**
 * 위치기반 관광정보 조회
 *
 * @param "/tour/api/info/locationbasedlist"
 * @param tourLocationBasedListVo
 */
@GetMapping(value = "/tour/api/info/locationbasedlist", produces = "application/json;application/xml; charset=UTF-8")
public String tourLocationBasedList(
        @RequestBody(required = false) TourLocationBasedListVo tourLocationBasedListVo) {
    String tourLocationBasedListUrl = new String(
            KOR_SERVICE_URL + "locationBasedList"
                    + "?serviceKey=" + SERVICE_DEV_KEY
                    + "&numOfRows=" + tourLocationBasedListVo.getNumOfRows()
                    + "&pageNo=" + tourLocationBasedListVo.getPageNo()
                    + "&MobileOS=" + tourLocationBasedListVo.getMobileOS()
                    + "&MobileApp=" + tourLocationBasedListVo.getMobileApp()
                    + "&listYN=" + tourLocationBasedListVo.getListYN()
                    + "&arrange=" + tourLocationBasedListVo.getArrange()
                    + "&contentTypeId=" + tourLocationBasedListVo.getContentTypeId()
                    + "&mapX=" + tourLocationBasedListVo.getMapX()
                    + "&mapY=" + tourLocationBasedListVo.getMapY()
                    + "&radius=" + tourLocationBasedListVo.getRadius()
                    + "&modifiedtime=" + tourLocationBasedListVo.getModifiedtime()
                    + "&_type=json"

    );
    ParsingFromURL parsingFromURL = new ParsingFromURL();
    System.out.println("URL => " + tourLocationBasedListUrl);
    return parsingFromURL.getParsingURL(tourLocationBasedListUrl);
}
```

_serviceKey 파라미터는 static String SERVICE_DEV_KEY = **<하드코딩>** 처리되어있음을 알림._

| 번호 | 컬럼                                                  | 컬럽타입 | 크기 | 컬럼 명     | 컬럼정보                             |
| ---- | ----------------------------------------------------- | -------- | ---- | ----------- | ------------------------------------ |
| 1    | listYN                                                | String   | 1    | 목록 구분   | 목록 구분 (Y=목록, N=개수)           |
| 2    | arrange                                               | String   | 1    | 정렬 구분   | 상세정보 참조                        |
| 3    | [contentTypeId](#관광타입)                            | String   | 12   | 관광타입 ID | 관광타입(관광지, 숙박 등) ID         |
| 4    | mapX <b style="color: red; font-weight: 500">\*</b>   | String   | 10   | X좌표       | GPS X좌표(WGS84 경도 좌표)           |
| 5    | mapY <b style="color: red; font-weight: 500">\*</b>   | String   | 10   | Y좌표       | GPS Y좌표(WGS84 위도 좌표)           |
| 6    | radius <b style="color: red; font-weight: 500">\*</b> | String   | 6    | 거리 반경   | 거리 반경(단위:m), Max값 20000m=20Km |
| 7    | modifiedtime                                          | String   | 8    | 수정일      | 콘텐츠 수정일                        |

_컬럼을 선택하면 샘플데이터를 확인 할 수 있음._

<br>
<br>

---

## 005. 키워드 검색 조회

```java
/**
 * 키워드 검색 조회
 *
 * @param "/tour/api/info/searchkeyword"
 * @param tourSearchKeywordVo
 */
@GetMapping(value = "/tour/api/info/searchkeyword", produces = "application/json;application/xml; charset=UTF-8")
public String tourSearchKeyword(@RequestBody(required = false) TourSearchKeywordVo tourSearchKeywordVo) {
    String tourSearchKeywordUrl = new String(
            KOR_SERVICE_URL + "searchKeyword"
                    + "?serviceKey=" + SERVICE_DEV_KEY
                    + "&numOfRows=" + tourSearchKeywordVo.getNumOfRows()
                    + "&pageNo=" + tourSearchKeywordVo.getPageNo()
                    + "&MobileOS=" + tourSearchKeywordVo.getMobileOS()
                    + "&MobileApp=" + tourSearchKeywordVo.getMobileApp()
                    + "&listYN=" + tourSearchKeywordVo.getListYN()
                    + "&arrange=" + tourSearchKeywordVo.getArrange()
                    + "&contentTypeId=" + tourSearchKeywordVo.getContentTypeId()
                    + "&areaCode=" + tourSearchKeywordVo.getAreaCode()
                    + "&sigunguCode=" + tourSearchKeywordVo.getSigunguCode()
                    + "&cat1=" + tourSearchKeywordVo.getCat1()
                    + "&cat2=" + tourSearchKeywordVo.getCat2()
                    + "&cat3=" + tourSearchKeywordVo.getCat3()
                    + "&keyword=" + tourSearchKeywordVo.getKeyword()
                    + "&_type=json"

    );
    ParsingFromURL parsingFromURL = new ParsingFromURL();
    System.out.println("URL => " + tourSearchKeywordUrl);
    return parsingFromURL.getParsingURL(tourSearchKeywordUrl);
}
```

_serviceKey 파라미터는 static String SERVICE_DEV_KEY = **<하드코딩>** 처리되어있음을 알림._

| 번호 | 컬럼                                                   | 컬럽타입 | 크기 | 컬럼 명     | 컬럼정보                              |
| ---- | ------------------------------------------------------ | -------- | ---- | ----------- | ------------------------------------- |
| 1    | listYN                                                 | String   | 1    | 목록 구분   | 목록 구분 (Y=목록, N=개수)            |
| 2    | arrange                                                | String   | 1    | 정렬 구분   | 상세내용 참조.                        |
| 3    | [contentTypeId](#관광타입)                             | String   | 12   | 관광타입 ID | 관광타입(관광지, 숙박 등) ID          |
| 4    | [areaCode](#지역코드조회)                              | String   | 10   | 지역코드    | 지역코드                              |
| 5    | sigunguCode                                            | String   | 10   | 시군구코드  | 시군구코드(areaCode 필수)             |
| 6    | [cat1](#대분류)                                        | String   | 12   | 대분류      | 대분류 코드                           |
| 7    | [cat2](#중분류)                                        | String   | 12   | 중분류      | 중분류 코드(cat1필수)                 |
| 8    | [cat3](#소분류)                                        | String   | 12   | 소분류      | 소분류 코드(cat1,cat2필수)            |
| 9    | keyword <b style="color: red; font-weight: 500">\*</b> | String   | 30   | 요청 키워드 | 검색 요청할 키워드 (국문=인코딩 필요) |

_컬럼을 선택하면 샘플데이터를 확인 할 수 있음._

<br>
<br>

---

## 007. 행사정보 조회

```java
/**
 * 행사정보 조회
 *
 * @param "/tour/api/info/searchfestival"
 * @param tourSearchFestivalVo
 */
@GetMapping(value = "/tour/api/info/searchfestival", produces = "application/json;application/xml; charset=UTF-8")
public String tourSearchFestival(@RequestBody(required = false) TourSearchFestivalVo tourSearchFestivalVo) {
    String tourSearchFestivalUrl = new String(
            KOR_SERVICE_URL + "searchFestival"
                    + "?serviceKey=" + SERVICE_DEV_KEY
                    + "&numOfRows=" + tourSearchFestivalVo.getNumOfRows()
                    + "&pageNo=" + tourSearchFestivalVo.getPageNo()
                    + "&MobileOS=" + tourSearchFestivalVo.getMobileOS()
                    + "&MobileApp=" + tourSearchFestivalVo.getMobileApp()
                    + "&listYN=" + tourSearchFestivalVo.getListYN()
                    + "&arrange=" + tourSearchFestivalVo.getArrange()
                    + "&areaCode=" + tourSearchFestivalVo.getAreaCode()
                    + "&sigunguCode=" + tourSearchFestivalVo.getSigunguCode()
                    + "&eventStartDate=" + tourSearchFestivalVo.getEventStartDate()
                    + "&eventEndDate=" + tourSearchFestivalVo.getEventEndDate()
                    + "&modifiedtime=" + tourSearchFestivalVo.getModifiedtime()
                    + "&_type=json"

    );
    ParsingFromURL parsingFromURL = new ParsingFromURL();
    System.out.println("URL => " + tourSearchFestivalUrl);
    return parsingFromURL.getParsingURL(tourSearchFestivalUrl);
}
```

_serviceKey 파라미터는 static String SERVICE_DEV_KEY = **<하드코딩>** 처리되어있음을 알림._

| 번호 | 컬럼                                                          | 컬럽타입 | 크기 | 컬럼 명     | 컬럼정보                      |
| ---- | ------------------------------------------------------------- | -------- | ---- | ----------- | ----------------------------- |
| 1    | listYN                                                        | String   | 1    | 목록 구분   | 목록 구분 (Y=목록, N=개수)    |
| 2    | arrange                                                       | String   | 1    | 정렬 구분   | 상세내용 참조.                |
| 3    | [areaCode](#지역코드조회)                                     | String   | 10   | 지역코드    | 지역코드                      |
| 4    | sigunguCode                                                   | String   | 10   | 시군구코드  | 시군구코드(areaCode 필수)     |
| 5    | eventStartDate <b style="color: red; font-weight: 500">\*</b> | String   | 8    | 행사 시작일 | 행사 시작일 (형식 : YYYYMMDD) |
| 6    | eventEndDate                                                  | String   | 8    | 행사 종료일 | 행사 종료일 (형식 : YYYYMMDD) |
| 7    | modifiedtime                                                  | String   | 8    | 수정일      | 콘텐츠 수정일                 |

_컬럼을 선택하면 샘플데이터를 확인 할 수 있음._

<br>
<br>

---

## 008. 공통정보 조회

```java

/**
 * 공통정보 조회
 *
 * @param "/tour/api/info/detailcommon"
 * @param tourDetailCommonVo
 */
@GetMapping(value = "/tour/api/info/detailcommon", produces = "application/json;application/xml; charset=UTF-8")
public String tourDetailCommon(@RequestBody(required = false) TourDetailCommonVo tourDetailCommonVo) {
    String tourDetailCommonUrl = new String(
            KOR_SERVICE_URL + "detailCommon"
                    + "?serviceKey=" + SERVICE_DEV_KEY
                    + "&numOfRows=" + tourDetailCommonVo.getNumOfRows()
                    + "&pageNo=" + tourDetailCommonVo.getPageNo()
                    + "&MobileOS=" + tourDetailCommonVo.getMobileOS()
                    + "&MobileApp=" + tourDetailCommonVo.getMobileApp()
                    + "&contentId=" + tourDetailCommonVo.getContentId()
                    + "&contentTypeId=" + tourDetailCommonVo.getContentTypeId()
                    + "&defaultYN=" + tourDetailCommonVo.getDefaultYN()
                    + "&firstImageYN=" + tourDetailCommonVo.getFirstImageYN()
                    + "&areacodeYN=" + tourDetailCommonVo.getAreacodeYN()
                    + "&catcodeYN=" + tourDetailCommonVo.getCatcodeYN()
                    + "&addrinfoYN=" + tourDetailCommonVo.getAddrinfoYN()
                    + "&mapinfoYN=" + tourDetailCommonVo.getMapinfoYN()
                    + "&overviewYN=" + tourDetailCommonVo.getOverviewYN()
                    + "&_type=json"

    );
    ParsingFromURL parsingFromURL = new ParsingFromURL();
    System.out.println("URL => " + tourDetailCommonUrl);
    return parsingFromURL.getParsingURL(tourDetailCommonUrl);
}
```

_serviceKey 파라미터는 static String SERVICE_DEV_KEY = **<하드코딩>** 처리되어있음을 알림._

| 번호 | 컬럼                                                     | 컬럽타입 | 크기 | 컬럼 명       | 컬럼정보                         |
| ---- | -------------------------------------------------------- | -------- | ---- | ------------- | -------------------------------- |
| 1    | contentId <b style="color: red; font-weight: 500">\*</b> | String   | 12   | 목록 구분     | 콘텐츠ID                         |
| 2    | [contentTypeId](#관광타입)                               | String   | 12   | 정렬 구분     | 관광타입(관광지, 숙박 등) ID     |
| 3    | defaultYN                                                | String   | 1    | 지역코드      | 기본정보 조회여부                |
| 4    | firstImageYN                                             | String   | 1    | 시군구코드    | 원본, 썸네일 대표이미지 조회여부 |
| 5    | areacodeYN                                               | String   | 1    | 한옥 여부     | 지역코드, 시군구코드 조회여부    |
| 6    | catcodeYN                                                | String   | 1    | 베니키아 여부 | 대,중,소분류코드 조회여부        |
| 7    | addrinfoYN                                               | String   | 1    | 굿스테이 여부 | 주소, 상세주소 조회여부          |
| 8    | mapinfoYN                                                | String   | 1    | 수정일        | 좌표X, Y 조회여부                |
| 9    | overviewYN                                               | String   | 1    | 수정일        | 콘텐츠 개요 조회여부             |

_컬럼을 선택하면 샘플데이터를 확인 할 수 있음._

<br>
<br>

---

## 009. 반복정보 조회

```java

/**
 * 소개정보 조회
 *
 * @param "/tour/api/info/detailinfo"
 * @param tourDetailInfoVo
 */
@GetMapping(value = "/tour/api/info/detailinfo", produces = "application/json;application/xml; charset=UTF-8")
public String tourDetailInfo(@RequestBody(required = false) TourDetailInfoVo tourDetailInfoVo) {
    String tourDetailInfoUrl = new String(
            KOR_SERVICE_URL + "detailInfo"
                    + "?serviceKey=" + SERVICE_DEV_KEY
                    + "&numOfRows=" + tourDetailInfoVo.getNumOfRows()
                    + "&pageNo=" + tourDetailInfoVo.getPageNo()
                    + "&MobileOS=" + tourDetailInfoVo.getMobileOS()
                    + "&MobileApp=" + tourDetailInfoVo.getMobileApp()
                    + "&contentId=" + tourDetailInfoVo.getContentId()
                    + "&contentTypeId=" + tourDetailInfoVo.getContentTypeId()
                    + "&_type=json"

    );
    ParsingFromURL parsingFromURL = new ParsingFromURL();
    System.out.println("URL => " + tourDetailInfoUrl);
    return parsingFromURL.getParsingURL(tourDetailInfoUrl);
}
```

_serviceKey 파라미터는 static String SERVICE_DEV_KEY = **<하드코딩>** 처리되어있음을 알림._

| 번호 | 컬럼                                                                      | 컬럽타입 | 크기 | 컬럼 명   | 컬럼정보                     |
| ---- | ------------------------------------------------------------------------- | -------- | ---- | --------- | ---------------------------- |
| 1    | contentId <b style="color: red; font-weight: 500">\*</b>                  | String   | 12   | 목록 구분 | 콘텐츠ID                     |
| 2    | [contentTypeId](#관광타입) <b style="color: red; font-weight: 500">\*</b> | String   | 12   | 정렬 구분 | 관광타입(관광지, 숙박 등) ID |

_컬럼을 선택하면 샘플데이터를 확인 할 수 있음._

<br>
<br>

---

## 010. 이미지정보 조회

```java
/**
 * 이미지정보 조회
 *
 * @param "/tour/api/info/detailimage"
 * @param tourDetailImageVo
 */
@GetMapping(value = "/tour/api/info/detailimage", produces = "application/json;application/xml; charset=UTF-8")
public String tourDetailImage(@RequestBody(required = false) TourDetailImageVo tourDetailImageVo) {
    String tourDetailImageUrl = new String(
            KOR_SERVICE_URL + "detailImage"
                    + "?serviceKey=" + SERVICE_DEV_KEY
                    + "&numOfRows=" + tourDetailImageVo.getNumOfRows()
                    + "&pageNo=" + tourDetailImageVo.getPageNo()
                    + "&MobileOS=" + tourDetailImageVo.getMobileOS()
                    + "&MobileApp=" + tourDetailImageVo.getMobileApp()
                    + "&contentId=" + tourDetailImageVo.getContentId()
                    + "&imageYN=" + tourDetailImageVo.getImageYN()
                    + "&subImageYN=" + tourDetailImageVo.getSubImageYN()
                    + "&_type=json"

    );
    ParsingFromURL parsingFromURL = new ParsingFromURL();
    System.out.println("URL => " + tourDetailImageUrl);
    return parsingFromURL.getParsingURL(tourDetailImageUrl);
}
```

_serviceKey 파라미터는 static String SERVICE_DEV_KEY = **<하드코딩>** 처리되어있음을 알림._

| 번호 | 컬럼                                                     | 컬럽타입 | 크기 | 컬럼 명   | 컬럼정보                                              |
| ---- | -------------------------------------------------------- | -------- | ---- | --------- | ----------------------------------------------------- |
| 1    | contentId <b style="color: red; font-weight: 500">\*</b> | String   | 12   | 목록 구분 | 콘텐츠ID                                              |
| 2    | imageYN                                                  | String   | 1    | 정렬 구분 | Y=콘텐츠 이미지 조회 N=”음식점”타입의 음식메뉴 이미지 |
| 3    | subImageYN                                               | String   | 1    | 정렬 구분 | Y=원본,썸네일 이미지 조회 N=Null                      |

_컬럼을 선택하면 샘플데이터를 확인 할 수 있음._

<br>
<br>

---

## 지역코드조회

| 번호 | NAME           | TYPE   | CODE | TYPE        |
| ---- | -------------- | ------ | ---- | ----------- |
| 1    | 서울           | String | 1    | int, String |
| 2    | 인천           | String | 2    | int, String |
| 3    | 대전           | String | 3    | int, String |
| 4    | 대구           | String | 4    | int, String |
| 5    | 광주           | String | 5    | int, String |
| 6    | 부산           | String | 6    | int, String |
| 7    | 울산           | String | 7    | int, String |
| 8    | 세종특별자치시 | String | 8    | int, String |
| 9    | 경기도         | String | 31   | int, String |
| 10   | 강원도         | String | 32   | int, String |
| 11   | 충청북도       | String | 33   | int, String |
| 12   | 충청남도       | String | 34   | int, String |
| 13   | 경상북도       | String | 35   | int, String |
| 14   | 경상남도       | String | 36   | int, String |
| 15   | 전라북도       | String | 37   | int, String |
| 16   | 전라남도       | String | 38   | int, String |
| 17   | 제주도         | String | 39   | int, String |

<br>
<br>

---

## 관광타입

| 번호 | NAME           | CODE | TYPE        |
| ---- | -------------- | ---- | ----------- |
| 1    | 관광지         | 12   | int, String |
| 2    | 문화시설       | 14   | int, String |
| 3    | 행사/공연/축제 | 15   | int, String |
| 4    | 여행코스       | 25   | int, String |
| 5    | 레포츠         | 28   | int, String |
| 6    | 숙박           | 32   | int, String |
| 7    | 쇼핑           | 38   | int, String |
| 8    | 음식점         | 39   | int, String |

<br>
<br>

---

## 대분류

첨부파일 참조.

<br>
<br>

---

## 중분류

첨부파일 참조.

<br>
<br>

---

## 소분류

첨부파일 참조.

<br>
<br>

---

## 시군구코드

| 번호 | NAME           | CODE | TYPE        |
| ---- | -------------- | ---- | ----------- |
| 1    | 관광지         | 12   | int, String |
| 2    | 문화시설       | 14   | int, String |
| 3    | 행사/공연/축제 | 15   | int, String |
| 4    | 여행코스       | 25   | int, String |
| 5    | 레포츠         | 28   | int, String |
| 6    | 숙박           | 32   | int, String |
| 7    | 쇼핑           | 38   | int, String |
| 8    | 음식점         | 39   | int, String |

<br>
<br>

---

# 2021-12-04(토) 현재 진행상황

## 1. 관광 데이터

```java
관광정보목록 URL 변경.
@GetMapping(value = "/user/test") ==> @GetMapping(value = "/tour/api/info")

관광이미지 목록 URL 변경.
@GetMapping(value = "/tour/api/image") ==> @GetMapping(value = "/tour/api/info/image")
```

## 2. 로그인/로그아웃 토큰 기능 추가

```java
2.1. 로그인시 파라미터 전달.
@PostMapping(value = "/user/login")

userResult.addProperty("result", true);
userResult.addProperty("token_acc", token.getAccessJwsToken());   // 억세스 토큰 발급.
userResult.addProperty("token_ref", token.getRefreshJwsToken());  // 리프레쉬 토큰 발급.

userVo.setUserRefreshToken(token.getRefreshJwsToken());
userService.userRefTokenUpdate(userVo);                           // 계정별 리프레쉬 토큰 저장.

2.2. 로그아웃시 리프레쉬 토큰 삭제.
// 아이디와 패스워드 값을 전달받아 해당 계정의 리프레쉬 토큰 값을 가져옴.
@PostMapping(value = "/user/logout")

if (!Objects.toString(userVo.getUserId(), "").equals("") && !Objects.toString(userVo.getUserPassword(), "").equals("")) {

UserVo getUserVo = userService.userInfo(userVo);
getUserVo.setUserRefreshToken("");                                // 리프레쉬 토큰 공백처리.
userService.userUpdate(getUserVo);

```

## 3. 토큰.

## 4. 파싱.

# 2021-11-04(목) 현재 진행상황

## 1. 관광 데이터 테스트 URL

```java
@GetMapping(value = "/user/test")
```

> 관광 데이터 10건 호출.

```java
rootobj = root.getAsJsonObject(); // 관광데이터
rootobj.addProperty("result", true);
```

> URL 호출 성공시

```java
userResult.addProperty("result", false);
userResult.addProperty("error", e.getMessage());
```

> URL 호출 실패시

# 2021-11-02(화) 현재 진행상황

## 1. 사용자 정보 VO

UserVo.java

```java
package com.diden.user.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserVo {
    private String userId = null;
    private String userName = null;
    private String userPassword = null;
    private String userNickname = null;
    private String userBirthday = null;
    private String userGender = null;
    private String userEmail = null;
    private String userPhoneNumber = null;
    private String userCreateDate = null;
    private String userUpdateDate = null;
    private String userPrivacyConsent = null;
}
```

> 파라미터 Key 값

```json
{
  "userId": "user1",
  "userName": "test",
  "userPassword": "test",
  "userNickname": "test",
  "userBirthday": "test",
  "userGender": "test",
  "userEmail": "test",
  "userPhoneNumber": "test",
  "userCreateDate": "test",
  "userUpdateDate": "test",
  "userPrivacyConsent": "1"
}
```

> Json 형식으로 데이터를 전달해줘야 합니다.

---

## 2. 사용자 정보 리스트

/user-list.json -> /user/list URL 변경.

```JAVA
@GetMapping(value = "/user/list", produces = "application/json; charset=UTF-8")
```

> 사용자 리스트 정보

---

## 3. 사용자 로그인

```java
@PostMapping(value = "/user/login", produces = "application/json; charset=UTF-8")
```

> 로그인 URL

```JAVA
userResult.addProperty("result", true);
```

> 로그인 성공시

```JAVA
userResult.addProperty("result", false);
userResult.addProperty("error", e.getMessage());
```

> 로그인 실패시

---

## 4. 사용자 등록 및 수정

```JAVA
@PutMapping(value = "/user", produces = "application/json; charset=UTF-8")
```

> 회원정보 Insert/Update URL

```JAVA
userResult.addProperty("result", true);
userResult.addProperty("put", "insert");
```

> 회원정보 Insert 성공시

```JAVA
userResult.addProperty("result", true);
userResult.addProperty("put", "update");
```

> 회원정보 Update 성공시

```JAVA
userResult.addProperty("result", false);
userResult.addProperty("error", e.getMessage());
```

> 회원정보 Insert/Update 실패시

---

## 5. 사용자 정보 삭제

```JAVA
@DeleteMapping(value = "/user", produces = "application/json; charset=UTF-8")
```

> 사용자 정보 삭제 URL

```JAVA
userResult.addProperty("result", true);
```

> 사용자 정보 삭제 성공시

```JAVA
userResult.addProperty("result", false);
userResult.addProperty("error", e.getMessage());
```

> 사용자 정보 삭제 실패시

---

# 2021-10-30(토) 현재 진행상황

```java
RequestMapping(/user/list)
```

```sql
SELECT USER_ID
     , USER_NAME
     , USER_PASSWORD
     , USER_NICKNAME
     , USER_BIRTHDAY
     , USER_GENDER
     , USER_EMAIL
     , USER_PHONE_NUMBER
     , USER_CREATE_DATE
     , USER_UPDATE_DATE
     , USER_PRIVACY_CONSENT
  FROM TB_USER
```

> 데이터가 정상 출력되는지만 작업해놓은 상태.  
> API(Json) 형식으로 데이터를 전달하느 부분은 미완료.
