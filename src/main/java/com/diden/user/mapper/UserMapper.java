package com.diden.user.mapper;

import java.util.List;

import com.diden.user.vo.UserVo;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    public List<UserVo> userList();
}
