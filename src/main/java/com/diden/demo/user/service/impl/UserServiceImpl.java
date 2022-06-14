package com.diden.demo.user.service.impl;

import com.diden.demo.user.mapper.UserMapper;
import com.diden.demo.user.service.UserService;
import com.diden.demo.user.vo.UserVo;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final List<SocialAdepter> socialAdepterList = new ArrayList<>();

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;

        this.socialAdepterList.add(new AppleSocialServiceImpl());
        this.socialAdepterList.add(new KakaoSocialServiceImpl());
        this.socialAdepterList.add(new NaverSocialServiceImpl());
    }

    @Override
    public int userCount(UserVo userVo) {
        return userMapper.userCount(userVo);
    }

    @Override
    public List<UserVo> userList() {
        return userMapper.userList();
    }

    @Override
    public UserVo userInfo(UserVo userVo) {
        return userMapper.userInfo(userVo);
    }

    @Override
    public void userInsert(UserVo userVo) {
        userMapper.userInsert(userVo);
    }

    @Override
    public void userUpdate(UserVo userVo) {
        userMapper.userUpdate(userVo);
    }

    @Override
    public void userRefTokenUpdate(UserVo userVo) {
        userMapper.userRefTokenUpdate(userVo);
    }

    @Override
    public void userDelete(UserVo userVo) {
        userMapper.userDelete(userVo);
    }

    @Override
    public UserVo userRefreshTokenInfo(UserVo userVo) {
        return userMapper.userRefreshTokenInfo(userVo);
    }

    @Override
    public boolean socialSignup(JsonObject param) {
        final String loginType = param.get("login_type").getAsString();
        final JsonObject loginParam = param.getAsJsonObject("loginParam");
        final SocialAdepter adepter;

        for (SocialAdepter socialAdepter : this.socialAdepterList) {
            if(socialAdepter.supports(loginType)){
                adepter = socialAdepter;
                final UserVo userVo = adepter.process(loginParam);

                if (userMapper.userCheck(userVo) > 0) userMapper.userUpdate(userVo);
                else userMapper.userInsert(userVo);
                return true;
            }
        }

        log.info("소셜 로그인 실패\n로그인 타입에 맞는 어댑터가 존재하지 않습니다.");
        return false;
    }
}
