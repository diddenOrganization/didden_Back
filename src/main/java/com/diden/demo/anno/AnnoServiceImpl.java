package com.diden.demo.anno;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnoServiceImpl implements AnnoService {
  private final AnnoMapper annoMapper;

  public List<AnnoVo> findAll() {
    return annoMapper.findAll();
  }

  public AnnoVo findOne(final AnnoVo annoVo) {
    return annoMapper.findOne(annoVo);
  }

  public int save(final AnnoVo annoVo) {
    return annoMapper.save(annoVo);
  }

  public int update(final AnnoVo annoVo) {
    return annoMapper.update(annoVo);
  }

  public int delete(final String annoId) {
    return annoMapper.delete(annoId);
  }
}
