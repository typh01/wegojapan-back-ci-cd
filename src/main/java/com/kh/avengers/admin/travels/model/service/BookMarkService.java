package com.kh.avengers.admin.travels.model.service;

import java.util.Map;

import com.kh.avengers.common.dto.RequestData;

public interface BookMarkService {

  RequestData insertBookMark(Map<String, String> book);

  RequestData deleteBookMark(Map<String, String> book);

}
