package com.diden.demo.anno;

import java.util.List;

public interface AnnoService {
  List<AnnoVo> findAll();

  AnnoVo findOne(final AnnoVo annoVo);

  int save(final AnnoVo annoVo);

  int update(final AnnoVo annoVo);

  int delete(final String annoId);
}
