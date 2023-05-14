package com.diden.demo.domain.tour.service;

import com.diden.demo.domain.tour.entity.TourCommonEntityV4;
import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.diden.demo.domain.tour.enums.ServiceHighCode;
import com.diden.demo.domain.tour.enums.ServiceMiddleCode;
import com.diden.demo.domain.tour.vo.response.TourCommonV4ResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public Slice<TourCommonV4ResponseVo> selectCommonData(PageRequest pageRequest, List<ServiceContentTypeCode> serviceContentTypeCodes, List<ServiceHighCode> serviceHighCodes, List<ServiceMiddleCode> serviceMiddleCodes) {
        final List<TourCommonEntityV4> contentTypeCodeList = new ArrayList<>();
        final Map<Long, TourCommonEntityV4> findAllTourCommonEntityV4Map = new HashMap<>();
        final Map<Long, TourCommonEntityV4> tourCommonEntityV4Map = new HashMap<>();

        findAllCodeData(contentTypeCodeList, findAllTourCommonEntityV4Map); // 전체 조회

        if (contentTypeCodeList.isEmpty()) {
            return new TourCommonV4ResponseVo().selectSliceInit();
        }

        if (ServiceContentTypeCode.isNullOrEmpty(serviceContentTypeCodes) && ServiceHighCode.isNullOrEmpty(serviceHighCodes) && ServiceMiddleCode.isNullOrEmpty(serviceMiddleCodes)) {
            log.info(":: Select Map<Long, TourCommonEntityV4> Size => {} ::", findAllTourCommonEntityV4Map.size());
            return new TourCommonV4ResponseVo().selectSliceInit(pageRequest, findAllTourCommonEntityV4Map);
        }

        contentTypeCodeFilter(serviceContentTypeCodes, contentTypeCodeList, tourCommonEntityV4Map);
        highCodeFilter(serviceHighCodes, contentTypeCodeList, tourCommonEntityV4Map);
        middleCodeFilter(serviceMiddleCodes, contentTypeCodeList, tourCommonEntityV4Map);

        log.info(":: Select Map<Long, TourCommonEntityV4> Size => {} ::", tourCommonEntityV4Map.isEmpty() ? findAllTourCommonEntityV4Map.size() : tourCommonEntityV4Map.size());

        return new TourCommonV4ResponseVo().selectSliceInit(pageRequest, tourCommonEntityV4Map.isEmpty() ? findAllTourCommonEntityV4Map : tourCommonEntityV4Map);
    }

    private void findAllCodeData(List<TourCommonEntityV4> contentTypeCodeList, Map<Long, TourCommonEntityV4> findAllTourCommonEntityV4Map) {
        for (ServiceContentTypeCode contentTypeCode : ServiceContentTypeCode.values()) { // 여행 데이터 전체 조회
            if(contentTypeCode.isPresentA02()) {
                continue;
            }

            final JpaRepository jpaRepository = contentTypeAdepterMap.get(contentTypeCode);
            contentTypeCodeList.addAll(jpaRepository.findAll());
        }
        contentTypeCodeList.forEach(o -> findAllTourCommonEntityV4Map.put(o.getContentId(), o));
    }

    private static void contentTypeCodeFilter(List<ServiceContentTypeCode> serviceContentTypeCodes, List<TourCommonEntityV4> contentTypeCodeList, Map<Long, TourCommonEntityV4> tourCommonEntityV4Map) {
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

    private static void highCodeFilter(List<ServiceHighCode> serviceHighCodes, List<TourCommonEntityV4> contentTypeCodeList, Map<Long, TourCommonEntityV4> tourCommonEntityV4Map) {
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

    private static void middleCodeFilter(List<ServiceMiddleCode> serviceMiddleCodes, List<TourCommonEntityV4> contentTypeCodeList, Map<Long, TourCommonEntityV4> tourCommonEntityV4Map) {
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




    @Override
    public Map<Long, TourCommonEntityV4> findToursByContentType(List<ServiceContentTypeCode> serviceContentTypeCodes) {
        final List<TourCommonEntityV4> tourList = new ArrayList<>();
        final Map<Long, TourCommonEntityV4> byList = new HashMap<>();

        for (ServiceContentTypeCode contentTypeCode : serviceContentTypeCodes) {
            if(contentTypeCode.isPresentA02()) continue;

            JpaRepository jpaRepository = contentTypeAdepterMap.get(contentTypeCode);
            tourList.addAll(jpaRepository.findAll());
        }

        tourList.forEach(o -> byList.put(o.getContentId(), o));
        return byList;
    }

    @Override
    public Map<Long, TourCommonEntityV4> tourListFindByHighCode(Map<Long, TourCommonEntityV4> findTours, List<ServiceHighCode> serviceHighCodes) {
        for (Map.Entry<Long, TourCommonEntityV4> entry : findTours.entrySet()) {
            //serviceHighCodes.stream().filter(o -> entry.getValue().getHighCode().equals(o.getCode())).collect(Collectors.toMap(, ));
        }

        return null;
    }

    @Override
    public Map<Long, TourCommonEntityV4> tourListFindByMiddleCode(Map<Long, TourCommonEntityV4> findTours, List<ServiceMiddleCode> serviceMiddleCodes) {
        return null;
    }
}
