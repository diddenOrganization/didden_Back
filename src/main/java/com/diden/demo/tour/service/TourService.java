package com.diden.demo.tour.service;

import java.util.List;
import java.util.Map;

public interface TourService {
  List<Map<String, Object>> tourInfoList(Map<String, Object> tourInfoParam);

  void tourInfoInsert(Map<String, Object> tourInfoParam);

  void tourInfoUpdate(Map<String, Object> tourInfoParam);

  void tourInfoDelete();
}
