package com.kh.avengers.admin.travels.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;


import com.kh.avengers.admin.travels.model.dao.TravelMapper;
import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.exception.commonexception.InvalidException;
import com.kh.avengers.exception.util.InvalidAccessException;
import com.kh.avengers.util.ResponseUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookMarkServiceImpl implements BookMarkService{

  private final ResponseUtil responseUtil;


  private final TravelMapper travelMapper;

  @Override
  public RequestData insertBookMark(Map<String, String> book) {
    Long bookCheck = travelMapper.checkedBook(book);
    if (bookCheck == 1) {
        throw new InvalidException("즐겨찾기를 이미 눌렀습니다.");
    }
    Long bookCount = travelMapper.insertBookCount(book);
    if (bookCount == 0) {
        throw new InvalidException("즐겨찾기 등록에 실패했습니다.");
    }


    return responseUtil.rd("200", book, "즐겨찾기 되었습니다.");
}

  @Override
  public RequestData deleteBookMark(Map<String, String> book) {
    Long bookCheck = travelMapper.checkedBook(book);
    if (bookCheck == 0) {
        throw new InvalidException("즐겨찾기를 이미 취소했습니다.");
    }

    Long bookCount = travelMapper.deleteBookCount(book);
    if (bookCount == 0) {
        throw new InvalidException("즐겨찾기 등록에 실패했습니다.");
    }

    return responseUtil.rd("200", null, "즐겨찾기를 취소했습니다.");
}



  

}
