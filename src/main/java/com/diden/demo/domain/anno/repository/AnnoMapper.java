package com.diden.demo.domain.anno.repository;

import com.diden.demo.domain.anno.vo.request.AnnoVoRequest;
import com.diden.demo.domain.anno.vo.response.AnnoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayDeque;

@Mapper
public interface AnnoMapper {
  ArrayDeque<AnnoVo> findAll(@Param("findId") final Integer findId, @Param("limit") final Integer limit);

  AnnoVo findOne(AnnoVoRequest annoVoRequest);

  int save(AnnoVoRequest annoVoRequest);

  int update(AnnoVoRequest annoVoRequest);

  int delete(@Param("annoId") String annoId);
}
