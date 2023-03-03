package com.diden.demo.domain.anno.service;

import com.diden.demo.domain.anno.vo.response.AnnoVo;

import java.util.Deque;

public interface AnnoService {
  Deque<AnnoVo> findAll(final Integer findId, final Integer limit);

  AnnoVo findOne(final AnnoVo annoVo);

  int save(final AnnoVo annoVo);

  int update(final AnnoVo annoVo);

  int delete(final String annoId);
}
