package com.diden.demo.domain.anno.service;


import com.diden.demo.domain.anno.entity.Anno;
import com.diden.demo.domain.anno.repository.AnnoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AnnoServiceImplTest {

    @Autowired
    private AnnoRepository annoRepository;

    @Test
    void findAll() {
        List<Anno> all = annoRepository.findAll();

        all.forEach(System.out::println);
    }

}