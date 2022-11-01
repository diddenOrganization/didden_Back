package com.diden.demo.anno;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

@Mapper
public interface AnnoMapper {
  //List<AnnoVo> findAll(@Param("findId") final Integer findId, @Param("limit") final Integer limit);
  ArrayDeque<AnnoVo> findAll(@Param("findId") final Integer findId, @Param("limit") final Integer limit);

  AnnoVo findOne(AnnoVo annoVo);

  int save(AnnoVo annoVo);

  int update(AnnoVo annoVo);

  int delete(@Param("annoId") String annoId);
}
