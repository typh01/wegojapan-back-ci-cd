package com.kh.avengers.admin.travels.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.avengers.admin.travels.model.service.BookMarkService;
import com.kh.avengers.common.dto.RequestData;

import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookMark")
public class BookMarkController {

  private final BookMarkService bookMarkService;

  @PostMapping("/insert-book")
  public ResponseEntity<RequestData> insertBookMark(@RequestBody Map<String, String> book){
    RequestData result = bookMarkService.insertBookMark(book);
    return ResponseEntity.ok(result);
  }
  
  @DeleteMapping("/delete-book")
  public ResponseEntity<RequestData> deleteBookMark(@RequestBody Map<String, String> book){

    RequestData result = bookMarkService.deleteBookMark(book);

    return ResponseEntity.ok(result);
  }


}
