package com.diden.demo.domain.anno.service;

import com.diden.demo.api.anno.dto.request.AnnoDtoRequest;
import com.diden.demo.domain.anno.vo.request.AnnoVoRequest;
import com.diden.demo.domain.anno.vo.response.AnnoVo;

import java.util.Deque;

public interface AnnoService {
  Deque<AnnoVo> findAll(final Integer findId, final Integer limit);

  AnnoVo findOne(final AnnoVoRequest annoVoRequest);

  int save(final AnnoVoRequest annoVoRequest);

  int update(final AnnoVoRequest annoVoRequest);

  int delete(final String annoId);
}
