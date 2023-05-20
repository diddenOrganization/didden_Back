package com.diden.demo.domain.tour.service;

import com.diden.demo.domain.tour.entity.TourCommonEntityV4;
import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.diden.demo.domain.tour.enums.ServiceHighCode;
import com.diden.demo.domain.tour.enums.ServiceMiddleCode;
import com.diden.demo.domain.tour.vo.response.TourCommonV4ResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourCommonV4ServiceImpl implements TourCommonV4Service {

    private final Map<ServiceContentTypeCode, JpaRepository> contentTypeAdepterMap;

    @Override
    public Slice<TourCommonV4ResponseVo> selectCommonData(Pageable pageable, List<ServiceContentTypeCode> serviceContentTypeCodes, List<ServiceHighCode> serviceHighCodes, List<ServiceMiddleCode> serviceMiddleCodes, String keyword) {
        final List<TourCommonEntityV4> contentTypeCodeList = new ArrayList<>();
        final Map<Long, TourCommonEntityV4> findAllTourCommonEntityV4Map = new HashMap<>();
        final Map<Long, TourCommonEntityV4> tourCommonEntityV4Map = new HashMap<>();

        // 여행 데이터 전체 조회
        this.findAllCodeData(contentTypeCodeList, findAllTourCommonEntityV4Map);

        // 여행 데이터가 아예 없을 경우
        if (contentTypeCodeList.isEmpty()) {
            return new TourCommonV4ResponseVo().selectSliceInit();
        }

        // 필터를 선택하지 않았을 경우
        if (ServiceContentTypeCode.isNullOrEmpty(serviceContentTypeCodes) && ServiceHighCode.isNullOrEmpty(serviceHighCodes) && ServiceMiddleCode.isNullOrEmpty(serviceMiddleCodes)) {
            final Map<Long, TourCommonEntityV4> defaultMapOrSearchMap = this.getDefaultMapOrSearchMap(findAllTourCommonEntityV4Map, keyword);

            log.info(":: Select Map<Long, TourCommonEntityV4> Size => {} ::", defaultMapOrSearchMap.size());
            return new TourCommonV4ResponseVo().selectSliceInit(pageable, defaultMapOrSearchMap);
        }

        this.contentTypeCodeFilter(serviceContentTypeCodes, contentTypeCodeList, tourCommonEntityV4Map); // 컨텐츠 타입 코드로 여행 데이터 조회
        this.highCodeFilter(serviceHighCodes, contentTypeCodeList, tourCommonEntityV4Map); // 대분류 코드로 여행 데이터 조회
        this.middleCodeFilter(serviceMiddleCodes, contentTypeCodeList, tourCommonEntityV4Map); // 중분류 코드로 여행 데이터 조회

        final Map<Long, TourCommonEntityV4> defaultMapOrSearchMap = this.getDefaultMapOrSearchMap(tourCommonEntityV4Map, keyword);

        log.info(":: Select Map<Long, TourCommonEntityV4> Size => {} ::", defaultMapOrSearchMap.size());
        return new TourCommonV4ResponseVo().selectSliceInit(pageable, defaultMapOrSearchMap);
    }

    private Map<Long, TourCommonEntityV4> getDefaultMapOrSearchMap(final Map<Long, TourCommonEntityV4> tourCommonEntityV4Map, String keyword) {
        if (StringUtils.isBlank(keyword)) {
            return tourCommonEntityV4Map;
        }

        return tourCommonEntityV4Map.values().stream().filter(o -> o.getTitle().contains(keyword)).collect(Collectors.toMap(key -> key.getContentId(), value -> value));
    }

    private void findAllCodeData(List<TourCommonEntityV4> contentTypeCodeList, final Map<Long, TourCommonEntityV4> findAllTourCommonEntityV4Map) {
        for (final ServiceContentTypeCode contentTypeCode : ServiceContentTypeCode.values()) {
            if(contentTypeCode.isPresentA02()) {
                continue;
            }

            final JpaRepository jpaRepository = contentTypeAdepterMap.get(contentTypeCode);
            contentTypeCodeList.addAll(jpaRepository.findAll());
        }

        contentTypeCodeList.forEach(o -> findAllTourCommonEntityV4Map.put(o.getContentId(), o));
    }

    private void contentTypeCodeFilter(List<ServiceContentTypeCode> serviceContentTypeCodes, List<TourCommonEntityV4> contentTypeCodeList, Map<Long, TourCommonEntityV4> tourCommonEntityV4Map) {
        if (ServiceContentTypeCode.isNullOrEmpty(serviceContentTypeCodes)) {
            return;
        }
        final List<TourCommonEntityV4> findContentTypeCodeList = new ArrayList<>();


        for (ServiceContentTypeCode contentTypeCode : serviceContentTypeCodes) { // 전체 조회 중에서 대분류 조회
            findContentTypeCodeList.addAll(contentTypeCodeList.stream()
                    .filter(o -> contentTypeCode.getCode().equals(o.getServiceCode()))
                    .collect(Collectors.toList())
            );
        }

        findContentTypeCodeList.forEach(o -> tourCommonEntityV4Map.put(o.getContentId(), o));
    }

    private void highCodeFilter(List<ServiceHighCode> serviceHighCodes, List<TourCommonEntityV4> contentTypeCodeList, Map<Long, TourCommonEntityV4> tourCommonEntityV4Map) {
        if (ServiceHighCode.isNullOrEmpty(serviceHighCodes)) {
            return;
        }
        final List<TourCommonEntityV4> highCodeList = new ArrayList<>();

        for (ServiceHighCode highCode : serviceHighCodes) { // 전체 조회 중에서 대분류 조회
            highCodeList.addAll(contentTypeCodeList.stream()
                    .filter(o -> highCode.getCode().equals(o.getHighCode()))
                    .collect(Collectors.toList())
            );
        }

        highCodeList.forEach(o -> tourCommonEntityV4Map.put(o.getContentId(), o));
    }

    private void middleCodeFilter(List<ServiceMiddleCode> serviceMiddleCodes, List<TourCommonEntityV4> contentTypeCodeList, Map<Long, TourCommonEntityV4> tourCommonEntityV4Map) {
        if (ServiceMiddleCode.isNullOrEmpty(serviceMiddleCodes)) {
            return;
        }
        final List<TourCommonEntityV4> middleCodeList = new ArrayList<>();

        for (ServiceMiddleCode middleCode : serviceMiddleCodes) { // 전체 조회 중에서 중분류 조회
            middleCodeList.addAll(contentTypeCodeList.stream()
                    .filter(o -> middleCode.getCode().equals(o.getMiddleCode()))
                    .collect(Collectors.toList())
            );
        }
        middleCodeList.forEach(o -> tourCommonEntityV4Map.put(o.getContentId(), o));
    }
}
