package com.diden.demo.domain.anno.repository;

import com.diden.demo.domain.anno.vo.response.AnnoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayDeque;

@Mapper
public interface AnnoMapper {
  //List<AnnoVo> findAll(@Param("findId") final Integer findId, @Param("limit") final Integer limit);
  ArrayDeque<AnnoVo> findAll(@Param("findId") final Integer findId, @Param("limit") final Integer limit);

  AnnoVo findOne(AnnoVo annoVo);

  int save(AnnoVo annoVo);

  int update(AnnoVo annoVo);

  int delete(@Param("annoId") String annoId);
}
