package com.diden.demo.anno;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public interface AnnoService {
  Deque<AnnoVo> findAll(final Integer findId, final Integer limit);

  AnnoVo findOne(final AnnoVo annoVo);

  int save(final AnnoVo annoVo);

  int update(final AnnoVo annoVo);

  int delete(final String annoId);
}
