package com.diden.demo;

import com.diden.anno.AnnoService;
import com.diden.anno.AnnoVo;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class AnnoControllerTest {
    @Autowired
    AnnoService annoService;

    @Test
    void findAll() {
        //given
        AnnoVo annoVo = new AnnoVo();
        Gson gson = new Gson();

        //when
        List<AnnoVo> findAll = annoService.findAll();

        //then
        System.out.println(findAll);
    }

    @Test
    void findOne() {
        //given
        AnnoVo annoVo = new AnnoVo();
        annoVo.setAnnoId("1");

        //when
        AnnoVo findOne = annoService.findOne(annoVo);

        //then
        System.out.println(findOne);
                
    }

    @Test
    @Rollback
    void save() {
        //given
        AnnoVo annoVo = new AnnoVo();
        annoVo.setAnnoTitle("TEST");
        annoVo.setAnnoContent("데이터 저장 테스트");

        //when
        annoService.save(annoVo);

        //then
        System.out.println("annoVo = " + annoVo);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}