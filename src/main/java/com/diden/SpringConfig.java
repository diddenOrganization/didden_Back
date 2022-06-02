package com.diden;

import com.diden.anno.AnnoController;
import com.diden.anno.AnnoServiceImpl;
import com.diden.anno.mapper.AnnoMapper;
import com.diden.main.MainContentController;
import com.diden.main.MainContentServiceImpl;
import com.diden.main.mapper.MainContentMapper;
import com.diden.token.TokenApiController;
import com.diden.user.controller.UserApiController;
import com.diden.user.mapper.UserMapper;
import com.diden.user.service.impl.UserServiceImpl;
import com.diden.utils.JwtTokenUtil;
import org.springframework.context.annotation.Bean;

public class SpringConfig {
    private final UserServiceImpl userServiceImpl;
    private final UserMapper userMapper;
    private final JwtTokenUtil jwtTokenUtil;
    private final MainContentServiceImpl mainContentService;
    private final MainContentMapper mainContentMapper;
    private final AnnoServiceImpl annoService;
    private final AnnoMapper annoMapper;

    public SpringConfig(UserServiceImpl userServiceImpl
            , UserMapper userMapper
            , JwtTokenUtil jwtTokenUtil
            , MainContentServiceImpl mainContentService
            , MainContentMapper mainContentMapper
            , AnnoServiceImpl annoService
            , AnnoMapper annoMapper
    ) {
        this.userServiceImpl = userServiceImpl;
        this.userMapper = userMapper;
        this.jwtTokenUtil = jwtTokenUtil;
        this.mainContentService = mainContentService;
        this.mainContentMapper = mainContentMapper;
        this.annoService = annoService;
        this.annoMapper = annoMapper;
    }

    //////////////////////////////////////////////////////////////////////////// 토큰 유틸
    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }

    //////////////////////////////////////////////////////////////////////////// 사용자
    @Bean
    public UserApiController userService() {
        return new UserApiController(userServiceImpl, jwtTokenUtil);
    }

    @Bean
    public UserServiceImpl userMapper() {
        return new UserServiceImpl(userMapper);
    }

//    @Bean
//    public UserApiControllerUpdate userApiControllerUpdate() {
//        return new UserApiControllerUpdate(userServiceImpl, jwtTokenUtil);
//    }

    //////////////////////////////////////////////////////////////////////////// 토큰
    @Bean
    public TokenApiController tokenApiController() {
        return new TokenApiController(userServiceImpl, jwtTokenUtil);
    }

    //////////////////////////////////////////////////////////////////////////// 메인페이지
    @Bean
    public MainContentController mainContentController() {
        return new MainContentController(mainContentService);
    }

    @Bean
    public MainContentServiceImpl mainMapper() {
        return new MainContentServiceImpl(mainContentMapper);
    }

    //////////////////////////////////////////////////////////////////////////// 공지사항
    @Bean
    public AnnoController annoController() {
        return new AnnoController(annoService);
    }

    @Bean
    public AnnoServiceImpl annoMapper() {
        return new AnnoServiceImpl(annoMapper);
    }

}
