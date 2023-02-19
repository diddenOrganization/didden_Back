package com.diden.demo.tour.repository;

import com.diden.demo.tour.domain.TourAreaInfo;
import com.diden.demo.tour.vo.TourAreaInfoResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TourRepositoryTest {

    @Autowired TourRepository tourRepository;

    @Test
    @Transactional
    public void testTour() throws Exception {
        String contentId = "2945608";

        TourAreaInfo tourAreaInfo = tourRepository.find(contentId);

        //List<TourAreaInfo> tourAreaInfoList = tourRepository.find(title);

        System.out.println("TourAreaInfo = "+tourAreaInfo.getContentid());

     }

}