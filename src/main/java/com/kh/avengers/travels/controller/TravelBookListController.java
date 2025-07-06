package com.kh.avengers.travels.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.travels.model.service.TravelBookListService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class TravelBookListController {

  private final TravelBookListService travelBookListService;
  @GetMapping("/bookmarks")
  public ResponseEntity<RequestData> getBookMarks(@RequestParam(name="memberNo") Long memberNo){

    RequestData result = travelBookListService.getBookMarks(memberNo);
    return ResponseEntity.ok(result);

  }

}
