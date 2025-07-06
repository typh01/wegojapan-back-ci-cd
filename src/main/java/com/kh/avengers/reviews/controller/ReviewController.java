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

  /**
   * 특정 여행지의 리뷰 목록 조회
   */
  @GetMapping("/travel/{travelNo}")
  public ResponseEntity<RequestData> getTravelReviews(
          @PathVariable Long travelNo,
          @RequestParam(defaultValue = "0") int offset,
          @RequestParam(defaultValue = "3") int limit) {

    try {
      RequestData result = reviewService.getTravelReviews(travelNo, offset, limit);

      log.info("여행지 리뷰 목록 조회 완료 >> 여행지번호: {}", travelNo);
      return ResponseEntity.ok(result);

    } catch (Exception e) {
      log.error("여행지 리뷰 목록 조회 중 예상치 못한 오류 발생 >> 여행지번호: {}", travelNo, e);
      return ResponseEntity.status(500)
              .body(RequestData.builder()
                      .code("500")
                      .message("서버 내부 오류가 발생했습니다. 잠시 후 다시 시도해주세요.")
                      .build());
    }
  }

  /**
   * 내가 작성한 리뷰 목록 조회
   */
  @GetMapping("/my")
  public ResponseEntity<RequestData> getMyReviews(
          @AuthenticationPrincipal CustomUserDetails userDetails) {

    log.info("내 리뷰 목록 조회 요청 >> 사용자 : {}", userDetails.getUsername());

    RequestData result = reviewService.getMemberReviews(userDetails.getMemberNo());

    log.info("내 리뷰 목록 조회 완료!! >> 사용자 : {}", userDetails.getUsername());

    return ResponseEntity.ok(result);
  }


}
