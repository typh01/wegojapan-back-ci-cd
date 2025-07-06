package com.kh.avengers.reviews.model.dao;

import java.util.Map;

import com.kh.avengers.reviews.model.dto.ReviewDTO;
import com.kh.avengers.reviews.model.dto.ReviewImageDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {

  Long checkedLike(Map<String, String> like);

  Long insertLikeCount(Map<String, String> like);

  Long deleteLikeCount(Map<String,String> like);

  Long getLikeCount(Map<String,String> like);

  // 리뷰 등록
  int insertReview(ReviewDTO reviewDTO);

  // 리뷰 이미지 등록
  int insertReviewImage(ReviewImageDTO imageDTO);

}
