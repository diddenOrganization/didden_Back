package com.diden.anno.mapper;

import com.diden.anno.AnnoVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AnnoMapper {
    List<AnnoVo> findAll();
    AnnoVo findOne(AnnoVo annoVo);
    void save(AnnoVo annoVo);
    void update(AnnoVo annoVo);
    void delete(AnnoVo annoVo);
}
