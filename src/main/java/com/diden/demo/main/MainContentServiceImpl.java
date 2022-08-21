package com.diden.demo.main;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MainContentServiceImpl implements MainContentService {
  static List<MainContentVo> cache = new ArrayList<>();
  private final MainContentMapper mainContentMapper;

  @Override
  public List<MainContentVo> findMainContentImageAll() {
    return mainContentMapper.findMainContentImageAll();
  }

  @Override
  public MainContentVo findMainContentImageOne(MainContentVo mainContentVo) {
    return null;
  }

  @Override
  public Long findMainContentImageCount() {
    return mainContentMapper.findMainContentImageCount();
  }

  @Override
  public void saveMainContentImages(MainContentVo mainContentVo) {
    mainContentMapper.saveMainContentImages(mainContentVo);
  }
}
