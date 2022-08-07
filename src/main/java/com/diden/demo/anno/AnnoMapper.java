package com.diden.demo.anno;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnnoMapper {
  List<AnnoVo> findAll();

  AnnoVo findOne(AnnoVo annoVo);

  void save(AnnoVo annoVo);

  void update(AnnoVo annoVo);

  void delete(@Param("annoId") String annoId);
}
