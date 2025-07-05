package com.kh.avengers.admin.travels.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.avengers.admin.travels.model.service.BookMarkService;
import com.kh.avengers.common.dto.RequestData;

import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookMark")
public class BookMarkController {

  private final BookMarkService bookMarkService;

  @GetMapping("/check-book")
  public ResponseEntity<RequestData> checkBookMark(@RequestParam(name= "travelNo") Long travelNo, @RequestParam(name="memberNo") Long memberNo){
    RequestData result = bookMarkService.checkBookMark(travelNo,memberNo);

    return ResponseEntity.ok(result);
  }


  @PostMapping("/bookmark")
  public ResponseEntity<RequestData> selectBookMark(@RequestBody Map<String, Long> book){
    RequestData result = bookMarkService.selectBookMark(book);
    return ResponseEntity.ok(result);
  }
  
}
