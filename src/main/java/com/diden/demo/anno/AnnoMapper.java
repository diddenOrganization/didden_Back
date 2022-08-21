package com.diden.demo.anno;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnnoMapper {
  List<AnnoVo> findAll();

  AnnoVo findOne(AnnoVo annoVo);

  int save(AnnoVo annoVo);

  int update(AnnoVo annoVo);

  int delete(@Param("annoId") String annoId);
}
