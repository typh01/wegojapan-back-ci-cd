package com.kh.avengers.reviews.model.service;

import java.util.List;
import java.util.Map;

import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.reviews.model.dto.ReviewDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ReviewService {

  RequestData insertLike(Map<String, String> like);

  RequestData deleteLike(Map<String,String> like);

  /**
   * 리뷰 작성
   * @param reviewDTO 리뷰 정보
   * @param images 첨부하는 이미지 목록
   * @return 작성된 리뷰 고유 식별번호
   */
  RequestData createReview(ReviewDTO reviewDTO, List<MultipartFile> images);

  /**
   * 특정 여행지의 리뷰목록 조회
   * @param travelNo 여행지 번호
   * @param offset 시작위치
   * @param limit 조회할 개수
   * @return 여행지의 리뷰 목록
   */
  RequestData getTravelReviews(Long travelNo, int offset, int limit);

  /**
   * 특정 회원의 리뷰 목록 조회
   * @param memberNo 회원 번호
   * @return 리뷰 목록
   */
  RequestData getMemberReviews(Long memberNo);

}
