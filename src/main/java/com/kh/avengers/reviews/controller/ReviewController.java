package com.kh.avengers.reviews.controller;

import java.util.List;
import java.util.Map;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.reviews.model.dto.ReviewDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.reviews.model.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
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

  /**
   * 리뷰 작성
   */
  @PostMapping
  public ResponseEntity<RequestData> createReview(
          @RequestPart("review") @Valid ReviewDTO reviewDTO,
          @RequestPart(value = "images", required = false) List<MultipartFile> images,
          @AuthenticationPrincipal CustomUserDetails userDetails) {

    log.info("리뷰 작성 요청 >> 사용자: {}, 여행지번호: {}, 별점: {}",
            userDetails.getUsername(), reviewDTO.getTravelNo(), reviewDTO.getReviewRating());

    reviewDTO.setMemberNo(userDetails.getMemberNo());

    RequestData result = reviewService.createReview(reviewDTO, images);

    log.info("리뷰 작성 완료 >> 리뷰번호: {}", result.getData());

    return ResponseEntity.status(201).body(result);
  }

}
