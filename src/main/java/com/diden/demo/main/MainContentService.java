package com.diden.demo.main;

import java.util.List;

public interface MainContentService {
  /** findMainContentImageAll 메인화면 이미지 목록 전체 출력 */
  List<MainContentVo> findMainContentImageAll();

  /** findMainContentImageOne 메인화면 이미지 목록 한개 출력 */
  MainContentVo findMainContentImageOne(MainContentVo mainContentVo);

  /** findMainContentImageCount 메인화면 이미지 목록 갯수 */
  Long findMainContentImageCount();

  /** */
  void saveMainContentImages(MainContentVo mainContentVo);
}
