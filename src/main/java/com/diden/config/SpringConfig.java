package com.diden.config;

import com.diden.announcement.mapper.Announcement2Mapper;
import com.diden.announcement.service.impl.AnnouncementServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final Announcement2Mapper announcementMapper;
//    private final AnnouncementService announcementService;


    public SpringConfig(Announcement2Mapper announcementMapper) {
        this.announcementMapper = announcementMapper;
    }

    @Bean
    public AnnouncementServiceImpl announcementService(){
        return new AnnouncementServiceImpl(announcementMapper);
    }
}
