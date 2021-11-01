package com.diden.user.mapper;

import java.util.List;
import java.util.Map;

import com.diden.user.vo.UserVo;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    public List<UserVo> userList();

    public UserVo userInfo(Map<String, Object> userData);
}
