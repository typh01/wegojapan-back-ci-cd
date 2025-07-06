package com.kh.avengers.admin.travels.model.service;

import java.util.Map;

import com.kh.avengers.common.dto.RequestData;

public interface BookMarkService {

  RequestData selectBookMark(Map<String, Long> book);

  RequestData checkBookMark(Long travelNo, Long memberNo);

}
