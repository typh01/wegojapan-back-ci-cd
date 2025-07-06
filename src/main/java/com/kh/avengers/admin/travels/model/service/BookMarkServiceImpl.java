package com.kh.avengers.admin.travels.model.service;

import java.util.HashMap;
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
  public RequestData checkBookMark(Long travelNo, Long memberNo) {

    Map<String, Long> book = new HashMap<>();
    book.put("travelNo", travelNo);
    book.put("memberNo", memberNo);
    Long checkBook = travelMapper.checkedBook(book);

    return responseUtil.rd("200",checkBook,"즐겨찾기 여부 확인 완료");
  }

  @Override
  public RequestData selectBookMark(Map<String, Long> book) {
    Long bookCount = travelMapper.checkedBook(book);
    if (bookCount == 0) {
        travelMapper.insertBookCount(book);
    } else{
        travelMapper.deleteBookCount(book);
    }
    return responseUtil.rd("200", book, "즐겨찾기 되었습니다.");
}

 




  

}
