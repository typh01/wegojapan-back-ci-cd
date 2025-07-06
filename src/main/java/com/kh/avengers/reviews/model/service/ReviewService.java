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

}
