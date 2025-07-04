package com.kh.avengers.reviews.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.reviews.model.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

  private final ReviewService reviewService;

  @PostMapping("/insert-like")
  public ResponseEntity<RequestData> insertLike(@RequestBody Map<String, String> like){
    RequestData result = reviewService.insertLike(like);

    return ResponseEntity.ok(result);
  }

  @DeleteMapping("/delete-like")
  public ResponseEntity<RequestData> deleteLike(@RequestBody Map<String, String> like){
    RequestData result = reviewService.deleteLike(like);

    return ResponseEntity.ok(result);
  }

}
