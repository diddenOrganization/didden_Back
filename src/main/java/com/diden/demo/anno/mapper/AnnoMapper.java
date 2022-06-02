package com.diden.demo.anno.mapper;

import com.diden.demo.anno.AnnoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnnoMapper {
    List<AnnoVo> findAll();
    AnnoVo findOne(AnnoVo annoVo);
    void save(AnnoVo annoVo);
    void update(AnnoVo annoVo);
    void delete(AnnoVo annoVo);
}
