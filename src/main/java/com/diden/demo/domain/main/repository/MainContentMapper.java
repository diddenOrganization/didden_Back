package com.diden.demo.domain.main.repository;

import com.diden.demo.domain.main.vo.request.MainContentVoRequest;
import com.diden.demo.domain.main.vo.response.MainContentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainContentMapper {
  /** findMainContentImageAll 메인화면 이미지 목록 전체 출력 */
  List<MainContentVo> findMainContentImageAll();

  /** findMainContentImageOne 메인화면 이미지 목록 한개 출력 */
  MainContentVo findMainContentImageOne(MainContentVoRequest mainContentVoRequest);

  /** findMainContentImageCount 메인화면 이미지 목록 갯수 */
  Long findMainContentImageCount();

  /** */
  void saveMainContentImages(MainContentVoRequest mainContentVoRequest);
}
