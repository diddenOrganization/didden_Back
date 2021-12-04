package com.diden.user.mapper;

import java.util.List;

import com.diden.user.vo.UserVo;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    public List<UserVo> userList();

    public UserVo userInfo(UserVo userVo);

    public void userInsert(UserVo userVo);

    public void userUpdate(UserVo userVo);

    public void userRefTokenUpdate(UserVo userVo);

    public void userDelete(UserVo userVo);
}
