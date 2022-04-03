package com.diden.anno;

import java.util.List;

public interface AnnoService {
    String findAll();
    String findOne(AnnoVo annoVo);
    void save(AnnoVo annoVo);
    void update(AnnoVo annoVo);
    void delete(AnnoVo annoVo);
}
