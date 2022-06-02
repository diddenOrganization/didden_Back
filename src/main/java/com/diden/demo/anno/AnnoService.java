package com.diden.demo.anno;

import java.util.List;

public interface AnnoService {
    List<AnnoVo> findAll();
    AnnoVo findOne(AnnoVo annoVo);
    void save(AnnoVo annoVo);
    void update(AnnoVo annoVo);
    void delete(AnnoVo annoVo);
}
