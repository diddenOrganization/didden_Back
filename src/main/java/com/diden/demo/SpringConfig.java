package com.diden.demo;

import com.diden.demo.anno.AnnoService;
import com.diden.demo.anno.AnnoServiceImpl;
import com.diden.demo.anno.mapper.AnnoMapper;
import com.diden.demo.file.mapper.FileMapper;
import com.diden.demo.file.service.FileService;
import com.diden.demo.file.service.impl.FileServiceImpl;
import com.diden.demo.main.MainContentService;
import com.diden.demo.main.MainContentServiceImpl;
import com.diden.demo.main.mapper.MainContentMapper;
import com.diden.demo.tour.mapper.TourMapper;
import com.diden.demo.tour.service.TourService;
import com.diden.demo.tour.service.impl.TourServiceImpl;
import com.diden.demo.user.mapper.UserMapper;
import com.diden.demo.user.service.UserService;
import com.diden.demo.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final AnnoMapper annoMapper;
    private final FileMapper fileMapper;
    private final MainContentMapper mainContentMapper;
    private final TourMapper tourMapper;
    private final UserMapper userMapper;

    @Autowired
    public SpringConfig(AnnoMapper annoMapper, FileMapper fileMapper, MainContentMapper mainContentMapper, TourMapper tourMapper, UserMapper userMapper) {
        this.annoMapper = annoMapper;
        this.fileMapper = fileMapper;
        this.mainContentMapper = mainContentMapper;
        this.tourMapper = tourMapper;
        this.userMapper = userMapper;
    }


    @Bean
    public AnnoService annoService(){
        return new AnnoServiceImpl(annoMapper);
    }

    @Bean
    public FileService fileService(){
        return new FileServiceImpl(fileMapper);
    }

    @Bean
    public MainContentService mainContentService(){
        return new MainContentServiceImpl(mainContentMapper);
    }

    @Bean
    public TourService tourService(){
        return new TourServiceImpl(tourMapper);
    }

    @Bean
    public UserService userService(){
        return new UserServiceImpl(userMapper);
    }
}
