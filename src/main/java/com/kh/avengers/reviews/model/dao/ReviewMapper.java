package com.kh.avengers.reviews.model.dao;

import java.util.List;
import java.util.Map;

import com.kh.avengers.reviews.model.dto.ReviewDTO;
import com.kh.avengers.reviews.model.dto.ReviewImageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

  // 특정 여행지의 모든 리뷰목록 조회
  List<ReviewDTO> selectTravelReviews(@Param("travelNo") Long travelNo, @Param("offset") int offset, @Param("limit") int limit, @Param("currentMemberNo") Long currentMemberNo);

  // 리뷰번호로 이미지 목록 조회
  List<ReviewImageDTO> selectReviewImages(@Param("reviewNo") Long reviewNo);

  // 특정 여행지의 리뷰 개수 조회
  long countTravelReviews(@Param("travelNo") Long travelNo);

  // 특정 여행지의 평균 별점 조회
  Double selectAverageRating(@Param("travelNo") Long travelNo);

  // 특정 회원의 모든 리뷰 목록 조회
  List<ReviewDTO> selectMemberReviews(@Param("memberNo") Long memberNo);

  // 리뷰 번호로 리뷰 상세 조회
  ReviewDTO selectReviewByNo(@Param("reviewNo") Long reviewNo);

  // 리뷰 수정
  int updateReview(ReviewDTO reviewDTO);

  // 특정 이미지 삭제
  int deleteReviewImage(@Param("imageNo") Long imageNo);

  // 리뷰의 모든 이미지 삭제
  int deleteReviewImagesByReviewNo(@Param("reviewNo") Long reviewNo);

  // 리뷰 삭제
  int deleteReview(@Param("reviewNo") Long reviewNo);

}