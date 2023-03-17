package com.diden.demo.api.anno.controller;

import com.diden.demo.domain.anno.entity.Anno;
import com.diden.demo.domain.anno.repository.AnnoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnnoControllerTest {
    @Autowired
    private AnnoRepository annoRepository;

    @Test
    void findAll() {
        PageRequest pageRequest = PageRequest.of(3, 10);
        Slice<Anno> slice = annoRepository.findAll(pageRequest);
        System.out.println("slice.getContent() = " + slice.getContent());
        System.out.println("slice = " + slice.nextPageable());
        System.out.println("slice = " + slice.hasNext());
    }

}